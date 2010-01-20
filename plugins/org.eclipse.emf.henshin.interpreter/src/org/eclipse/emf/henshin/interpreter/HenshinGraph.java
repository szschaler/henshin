package org.eclipse.emf.henshin.interpreter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.henshin.common.util.EmfGraph;
import org.eclipse.emf.henshin.model.Attribute;
import org.eclipse.emf.henshin.model.Edge;
import org.eclipse.emf.henshin.model.Graph;
import org.eclipse.emf.henshin.model.HenshinFactory;
import org.eclipse.emf.henshin.model.Node;

public class HenshinGraph extends EmfGraph implements Adapter {
	private Graph henshinGraph;

	private Map<Node, EObject> node2eObjectMap;
	private Map<EObject, Node> eObject2nodeMap;

	public HenshinGraph(Graph graph) {
		node2eObjectMap = new HashMap<Node, EObject>();
		eObject2nodeMap = new HashMap<EObject, Node>();

		henshinGraph = graph;

		henshin2emfGraph();
	}

	@SuppressWarnings("unchecked")
	private void henshin2emfGraph() {
		eObjects.clear();
		// henshinGraph.eAdapters().clear();
		eObject2nodeMap.clear();
		node2eObjectMap.clear();

		for (Node node : henshinGraph.getNodes()) {
			EObject eObject = node2eObjectMap.get(node);

			if (eObject == null) {
				EClass nodeType = node.getType();
				EFactory factory = nodeType.getEPackage().getEFactoryInstance();
				eObject = factory.create(nodeType);
				addEObjectToGraph(eObject);
				addSynchronizedPair(node, eObject);
			}

			for (Attribute attr : node.getAttributes()) {
				// don't notify me about changes that I made
				eObject.eAdapters().remove(this);
				EAttribute attrType = attr.getType();
				if (attrType.isMany()) {
					List attrValues = (List) eObject.eGet(attrType);
					attrValues.add(attr.getValue());
				} else {
					eObject.eSet(attrType, EcoreUtil.createFromString(attrType
							.getEAttributeType(), attr.getValue()));
				}
				eObject.eAdapters().add(this);
			}
		}

		for (Edge edge : henshinGraph.getEdges()) {
			EReference edgeType = edge.getType();
			EObject ownerObject = node2eObjectMap.get(edge.getSource());

			// don't notify me about changes that I made
			ownerObject.eAdapters().remove(this);
			EObject targetObject = node2eObjectMap.get(edge.getTarget());
			if (edgeType.isMany()) {
				List edgeValues = (List) ownerObject.eGet(edgeType);
				edgeValues.add(targetObject);
			} else {
				ownerObject.eSet(edgeType, targetObject);
			}

			ownerObject.eAdapters().add(this);
		}
	}

	@Override
	public boolean addEObject(EObject eObject) {
		boolean isNew = super.addEObject(eObject);

		if (isNew) {
			HenshinFactory factory = HenshinFactory.eINSTANCE;

			Node node = eObject2nodeMap.get(eObject);
			if (node == null) {
				node = factory.createNode();
				node.setType(eObject.eClass());
				henshinGraph.getNodes().add(node);

				addSynchronizedPair(node, eObject);
			}
		}

		return isNew;
	}

	@Override
	public boolean removeEObject(EObject eObject) {
		boolean wasRemoved = super.removeEObject(eObject);

		if (wasRemoved) {
			Node node = eObject2nodeMap.get(eObject);

			if (node != null) {
				henshinGraph.getNodes().remove(node);
				removeSynchronizedPair(node, eObject);
			}
		}

		return wasRemoved;

	}

	/**
	 * This methods will update the EMF representation of the Henshin-Graph.
	 */
	public void updateEmfGraph() {
		henshin2emfGraph();
	}

	private void addSynchronizedPair(Node node, EObject eObject) {
		node2eObjectMap.put(node, eObject);
		eObject2nodeMap.put(eObject, node);

		eObject.eAdapters().add(this);
	}

	private void removeSynchronizedPair(Node node, EObject eObject) {
		// node2eObjectMap.remove(node);
		// eObject2nodeMap.remove(eObject);

		// eObject.eAdapters().remove(this);
	}

	@Override
	public Notifier getTarget() {
		return null;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return false;
	}

	@Override
	public void notifyChanged(Notification notification) {
		EObject owner = (EObject) notification.getNotifier();
		Node ownerNode = eObject2nodeMap.get(owner);
		Object feature = notification.getFeature();

		Object oldValue = notification.getOldValue();
		Object newValue = notification.getNewValue();

		if (feature instanceof EStructuralFeature && ownerNode != null) {
			// remove all deleted structures from the henshin graph
			if (oldValue != null) {
				removeFromHenshinGraph(owner, (EStructuralFeature) feature,
						oldValue);
			}

			// add new structures to henshin graph
			if (newValue != null) {
				addToHenshinGraph(owner, (EStructuralFeature) feature, newValue);
			}
		}
	}

	@Override
	public void setTarget(Notifier newTarget) {
	}

	private void removeFromHenshinGraph(EObject owner,
			EStructuralFeature feature, Object value) {
		Node node = eObject2nodeMap.get(owner);

		if (node != null && value != null) {
			if (feature instanceof EAttribute) {
				Attribute attribute = null;
				for (Attribute nodeAttribute : node.getAttributes()) {
					if (nodeAttribute.getType() == feature) {
						attribute = nodeAttribute;
						break;
					}
				}

				if (attribute != null) {
					attribute.setNode(null);
				}
			} else if (feature instanceof EReference) {
				Edge edge = null;

				if (value instanceof EObject) {
					Node targetNode = eObject2nodeMap.get(value);
					for (Edge outgoingEdge : node.getOutgoing()) {
						if (outgoingEdge.getTarget() == targetNode) {
							edge = outgoingEdge;
							break;
						}
					}

					if (edge != null) {
						edge.setSource(null);
						edge.setTarget(null);
						edge.setGraph(null);
					}
				}
			}
		}
	}

	private void addToHenshinGraph(EObject owner, EStructuralFeature feature,
			Object value) {
		Node node = eObject2nodeMap.get(owner);

		if (node != null && value != null) {
			if (feature instanceof EAttribute) {
				Attribute attribute = null;
				for (Attribute nodeAttribute : node.getAttributes()) {
					if (nodeAttribute.getType() == feature) {
						attribute = nodeAttribute;
						break;
					}
				}

				if (attribute == null) {
					attribute = HenshinFactory.eINSTANCE.createAttribute();
					attribute.setType((EAttribute) feature);
					attribute.setNode(node);
				}
				attribute.setValue(value.toString());

			} else if (feature instanceof EReference) {
				Edge edge = null;

				if (value instanceof EObject) {
					Node targetNode = eObject2nodeMap.get(value);
					for (Edge outgoingEdge : node.getOutgoing()) {
						if (outgoingEdge.getTarget() == targetNode) {
							edge = outgoingEdge;
							break;
						}
					}

					if (edge == null) {
						edge = HenshinFactory.eINSTANCE.createEdge();
						edge.setSource(node);
						edge.setTarget(targetNode);
						edge.setGraph(henshinGraph);
						edge.setType((EReference) feature);
					}
				}
			}
		}
	}

	/**
	 * @return the node2eObjectMap
	 */
	public Map<Node, EObject> getNode2eObjectMap() {
		return node2eObjectMap;
	}

	/**
	 * @param node2eObjectMap
	 *            the node2eObjectMap to set
	 */
	public void setNode2eObjectMap(Map<Node, EObject> node2eObjectMap) {
		this.node2eObjectMap = node2eObjectMap;
	}

	/**
	 * @return the eObject2nodeMap
	 */
	public Map<EObject, Node> geteObject2nodeMap() {
		return eObject2nodeMap;
	}

	/**
	 * @param eObject2nodeMap
	 *            the eObject2nodeMap to set
	 */
	public void seteObject2nodeMap(Map<EObject, Node> eObject2nodeMap) {
		this.eObject2nodeMap = eObject2nodeMap;
	}
}