package org.eclipse.emf.henshin.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.henshin.model.Attribute;
import org.eclipse.emf.henshin.model.AttributeCondition;
import org.eclipse.emf.henshin.model.BinaryFormula;
import org.eclipse.emf.henshin.model.Edge;
import org.eclipse.emf.henshin.model.Formula;
import org.eclipse.emf.henshin.model.Graph;
import org.eclipse.emf.henshin.model.Mapping;
import org.eclipse.emf.henshin.model.NestedCondition;
import org.eclipse.emf.henshin.model.Node;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.TransformationSystem;
import org.eclipse.emf.henshin.model.UnaryFormula;
import org.eclipse.emf.henshin.model.Variable;

public class ModelHelper {
    public static boolean isVariable(Rule rule, Attribute attribute) {
        for (Variable variable : rule.getVariables()) {
            if (variable.getName().equals(attribute.getValue())) {
                return true;
            }
        }
        return false;
    }

    public static boolean compareObjects(EObject eObject1, EObject eObject2) {
        EClass eClass1 = eObject1.eClass();
        EClass eClass2 = eObject2.eClass();

        if (eClass1 != eClass2)
            return false;

        if (eObject1.eContents().size() != eObject2.eContents().size())
            return false;

        for (EAttribute attribute : eClass1.getEAttributes()) {
            Object value1 = eObject1.eGet(attribute);
            Object value2 = eObject2.eGet(attribute);
            if (value1 != null) {
                if (!value1.equals(value2))
                    return false;
            } else {
                if (value2 != null)
                    return false;
            }
        }

        boolean result = true;
        for (int i = 0; i < eObject1.eContents().size(); i++) {
            result = result
                    && compareObjects(eObject1.eContents().get(i), eObject2
                            .eContents().get(i));
        }

        return result;
    }

    /**
     * Checks whether the specified node is part of the mappings.
     * 
     * @param mappings
     *            A list of mappings.
     * @param node
     *            The node which should be checked for origin or image in one of
     *            the mappings.
     * @return true, if the node is mapped
     */
    public static boolean isNodeMapped(List<Mapping> mappings, Node node) {
        return getRemoteNode(mappings, node) != null;
    }

    public static boolean containsMapping(List<Mapping> mappings,
            Node sourceNode, Node targetNode) {
        for (Mapping mapping : mappings) {
            if (mapping.getOrigin() == sourceNode
                    && mapping.getImage() == targetNode)
                return true;
        }
        return false;
    }

    public static boolean hasEdge(EReference type, Node source, Node target) {
        return findEdge(type, source, target) != null;
    }

    public static Edge findEdge(EReference type, Node source, Node target) {
        for (Edge edge : source.getOutgoing()) {
            if (edge.getTarget() == target && edge.getType() == type) {
                return edge;
            }
        }

        return null;
    }

    /**
     * Checks whether the specified edge is part of the mappings.
     * 
     * @param mappings
     *            A list of mappings.
     * @param node
     *            The edge which should be checked for origin or image.
     * @return true, if the edge is mapped
     */
    public static boolean isEdgeMapped(List<Mapping> mappings, Edge edge) {
        Node sourceNode = edge.getSource();
        Node targetNode = edge.getTarget();

        Node remoteSourceNode = getRemoteNode(mappings, sourceNode);
        Node remoteTargetNode = getRemoteNode(mappings, targetNode);

        if (remoteSourceNode != null && remoteTargetNode != null) {
            for (Edge remoteEdge : remoteSourceNode.getOutgoing()) {
                if (remoteEdge.getTarget() == remoteTargetNode)
                    return true;
            }
        }

        return false;
    }

    /**
     * Returns the image or origin of the specified node. If the node is not
     * part of a mapping, null will be returned. If the node is part of multiple
     * mappings, only the first remote node is returned.
     * 
     * @param mappings
     * @param node
     * @return
     */
    public static Node getRemoteNode(List<Mapping> mappings, Node node) {
        for (Mapping mapping : mappings) {
            if (mapping.getOrigin() == node)
                return mapping.getImage();
            if (mapping.getImage() == node)
                return mapping.getOrigin();
        }

        return null;
    }

    public static List<Node> getSourceNodes(List<Mapping> mappings, Node node) {
        List<Node> result = new ArrayList<Node>();

        for (Mapping mapping : mappings) {
            if (mapping.getImage() == node)
                result.add(mapping.getOrigin());
        }

        return result;
    }

    public static List<Node> getTargetNodes(List<Mapping> mappings, Node node) {
        List<Node> result = new ArrayList<Node>();

        for (Mapping mapping : mappings) {
            if (mapping.getOrigin() == node)
                result.add(mapping.getImage());
        }

        return result;
    }

    public static void renameVariableInRule(Rule rule, String oldName,
            String newName) {
        for (Variable currentVar : rule.getVariables()) {
            if (oldName.equals(currentVar.getName())) {
                currentVar.setName(newName);
                break;
            }
        }

        for (AttributeCondition condition : rule.getAttributeConditions()) {
            condition.setConditionText(renameInString(condition
                    .getConditionText(), oldName, newName));
        }

        for (Node node : rule.getLhs().getNodes()) {
            for (Attribute attribute : node.getAttributes()) {
                attribute.setValue(renameInString(attribute.getValue(),
                        oldName, newName));
            }
        }

        for (Node node : rule.getRhs().getNodes()) {
            for (Attribute attribute : node.getAttributes()) {
                attribute.setValue(renameInString(attribute.getValue(),
                        oldName, newName));
            }
        }

        renameInFormula(rule.getLhs().getFormula(), oldName, newName);
    }

    public static void renameInFormula(Formula formula, String oldName,
            String newName) {
        if (formula instanceof NestedCondition) {
            NestedCondition nestedCondition = (NestedCondition) formula;
            for (Node node : nestedCondition.getConclusion().getNodes()) {
                for (Attribute attribute : node.getAttributes()) {
                    attribute.setValue(renameInString(attribute.getValue(),
                            oldName, newName));
                }
            }
        } else if (formula instanceof BinaryFormula) {
            renameInFormula(((BinaryFormula) formula).getLeft(), oldName,
                    newName);
            renameInFormula(
                    ((org.eclipse.emf.henshin.model.BinaryFormula) formula)
                            .getRight(), oldName, newName);
        } else if (formula instanceof UnaryFormula) {
            renameInFormula(((UnaryFormula) formula).getChild(), oldName,
                    newName);
        }
    }

    /**
     * Returns the nested application condition the conclusion graph belongs to.
     * 
     * @param condition
     *            The topmost formula which shall be checked.
     * @param graph
     *            The conclusion graph.
     * @return The direct nested application condition this graph belongs to.
     */
    public static NestedCondition getParentCondition(Formula formula,
            Graph graph) {
        if (formula instanceof NestedCondition) {
            NestedCondition nestedCondition = (NestedCondition) formula;
            if (nestedCondition.getConclusion() == graph)
                return nestedCondition;
        } else if (formula instanceof BinaryFormula) {
            getParentCondition(((BinaryFormula) formula).getLeft(), graph);
            getParentCondition(((BinaryFormula) formula).getRight(), graph);
        } else if (formula instanceof UnaryFormula) {
            getParentCondition(((UnaryFormula) formula).getChild(), graph);
        }

        return null;
    }

    public static String renameInString(String testString, String oldString,
            String newString) {
        // if (testString.equals(oldString)) {
        // return newString;
        // }

        return testString.replaceAll("\\b" + oldString + "\\b", newString);
    }

    public static Object castDown(Object complexValue, String type) {
        if (complexValue instanceof Double) {
            if (type.equals("int")) {
                return ((Double) complexValue).intValue();
            } else if (type.equals("float")) {
                return ((Double) complexValue).floatValue();
            }
        }

        return complexValue;
    }

    public static Rule getRuleByName(TransformationSystem ts, String name) {
        for (Rule rule : ts.getRules()) {
            if (name.equals(rule.getName()))
                return rule;
        }
        return null;
    }

    public static void registerFileExtension(String extension) {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
                extension, new XMIResourceFactoryImpl());
    }

    public static void saveFile(String filename, EObject root) {
        Resource resource = new XMLResourceImpl(URI.createFileURI(filename));
        resource.getContents().add(root);
        try {
            resource.save(null);
        } catch (IOException e) {
        }
    }

    public static EObject loadFile(String filename) {
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.getResource(
                URI.createFileURI(filename), true);
        return resource.getContents().get(0);
    }

    public static List<Node> getNodesByType(Graph graph, String name) {
        List<Node> result = new ArrayList<Node>();
        for (Node node : graph.getNodes()) {
            if (name.equals(node.getType().getName()))
                result.add(node);
        }

        return result;
    }
}
