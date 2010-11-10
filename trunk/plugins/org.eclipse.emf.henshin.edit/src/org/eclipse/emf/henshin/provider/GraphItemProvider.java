/*******************************************************************************
 * Copyright (c) 2010 CWI Amsterdam, Technical University Berlin, 
 * Philipps-University Marburg and others. All rights reserved. 
 * This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Technical University Berlin - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.henshin.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.henshin.commands.NodeRemoveCommand;
import org.eclipse.emf.henshin.model.Graph;
import org.eclipse.emf.henshin.model.HenshinFactory;
import org.eclipse.emf.henshin.model.HenshinPackage;
import org.eclipse.emf.henshin.model.Node;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.emf.henshin.model.Graph} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class GraphItemProvider extends NamedElementItemProvider implements
		IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
		IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public GraphItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);
			
		}
		return itemPropertyDescriptors;
	}
	
	/**
	 * This specifies how to implement {@link #getChildren} and is used to
	 * deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand},
	 * {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in
	 * {@link #createCommand}. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(HenshinPackage.Literals.GRAPH__NODES);
			childrenFeatures.add(HenshinPackage.Literals.GRAPH__EDGES);
			childrenFeatures.add(HenshinPackage.Literals.GRAPH__FORMULA);
		}
		return childrenFeatures;
	}
	
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper
		// feature to use for
		// adding (see {@link AddCommand}) it as a child.
		
		return super.getChildFeature(object, child);
	}
	
	/**
	 * This returns Graph.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		Graph graph = (Graph) object;
		if (graph.getContainerRule() != null) {
			if (graph == graph.getContainerRule().getLhs())
				return overlayImage(object, getResourceLocator().getImage("full/obj16/Graph_L.png"));
			else if (graph == graph.getContainerRule().getRhs())
				return overlayImage(object, getResourceLocator().getImage("full/obj16/Graph_R.png"));
			
		}// if
		
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Graph.png"));
	}// getImage
	
	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Graph) object).getName();
		return (label == null) || (label.length() == 0) ? getString("_UI_Graph_type")
				: getString("_UI_Graph_type") + " " + label;
	}
	
	/**
	 * This handles model notifications by calling {@link #updateChildren} to
	 * update any cached children and by creating a viewer notification, which
	 * it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);
		
		switch (notification.getFeatureID(Graph.class)) {
			case HenshinPackage.GRAPH__NODES:
			case HenshinPackage.GRAPH__EDGES:
			case HenshinPackage.GRAPH__FORMULA:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(),
						true, false));
				return;
		}
		super.notifyChanged(notification);
	}
	
	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
		
		newChildDescriptors.add(createChildParameter(HenshinPackage.Literals.GRAPH__NODES,
				HenshinFactory.eINSTANCE.createNode()));
		
		newChildDescriptors.add(createChildParameter(HenshinPackage.Literals.GRAPH__EDGES,
				HenshinFactory.eINSTANCE.createEdge()));
		
		newChildDescriptors.add(createChildParameter(HenshinPackage.Literals.GRAPH__FORMULA,
				HenshinFactory.eINSTANCE.createNestedCondition()));
		
		newChildDescriptors.add(createChildParameter(HenshinPackage.Literals.GRAPH__FORMULA,
				HenshinFactory.eINSTANCE.createAnd()));
		
		newChildDescriptors.add(createChildParameter(HenshinPackage.Literals.GRAPH__FORMULA,
				HenshinFactory.eINSTANCE.createOr()));
		
		newChildDescriptors.add(createChildParameter(HenshinPackage.Literals.GRAPH__FORMULA,
				HenshinFactory.eINSTANCE.createNot()));
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.emf.edit.provider.ItemProviderAdapter#createRemoveCommand
	 * (org.eclipse.emf.edit.domain.EditingDomain,
	 * org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature,
	 * java.util.Collection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Command createRemoveCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection<?> collection) {
		
		HenshinPackage pack = HenshinPackage.eINSTANCE;
		
		if (feature == pack.getGraph_Nodes()) {
			for (Object c : collection) {
				if (!(c instanceof Node)) return UnexecutableCommand.INSTANCE;
			}// for
			return new NodeRemoveCommand(domain, owner, (Collection<Node>) collection);
		}
		
		// Objects might have been removed as side effect of other remove
		// commands
		// if (feature == pack.getGraph_Edges())
		// return new NegligentRemoveCommand(domain, owner, feature,
		// collection);
		
		return super.createRemoveCommand(domain, owner, feature, collection);
	}
	
}
