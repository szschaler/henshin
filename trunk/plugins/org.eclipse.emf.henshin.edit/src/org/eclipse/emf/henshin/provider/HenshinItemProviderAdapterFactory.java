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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.eclipse.emf.henshin.model.util.HenshinAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class HenshinItemProviderAdapterFactory extends HenshinAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HenshinItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.NamedElement} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NamedElementItemProvider namedElementItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.NamedElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNamedElementAdapter() {
		if (namedElementItemProvider == null) {
			namedElementItemProvider = new NamedElementItemProvider(this);
		}

		return namedElementItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.TransformationSystem} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransformationSystemItemProvider transformationSystemItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.TransformationSystem}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTransformationSystemAdapter() {
		if (transformationSystemItemProvider == null) {
			transformationSystemItemProvider = new TransformationSystemItemProvider(this);
		}

		return transformationSystemItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Rule} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RuleItemProvider ruleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Rule}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRuleAdapter() {
		if (ruleItemProvider == null) {
			ruleItemProvider = new RuleItemProvider(this);
		}

		return ruleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.AttributeCondition} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeConditionItemProvider attributeConditionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.AttributeCondition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAttributeConditionAdapter() {
		if (attributeConditionItemProvider == null) {
			attributeConditionItemProvider = new AttributeConditionItemProvider(this);
		}

		return attributeConditionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Parameter} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParameterItemProvider parameterItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Parameter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createParameterAdapter() {
		if (parameterItemProvider == null) {
			parameterItemProvider = new ParameterItemProvider(this);
		}

		return parameterItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Graph} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GraphItemProvider graphItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Graph}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createGraphAdapter() {
		if (graphItemProvider == null) {
			graphItemProvider = new GraphItemProvider(this);
		}

		return graphItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Mapping} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MappingItemProvider mappingItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Mapping}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createMappingAdapter() {
		if (mappingItemProvider == null) {
			mappingItemProvider = new MappingItemProvider(this);
		}

		return mappingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Node} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeItemProvider nodeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Node}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNodeAdapter() {
		if (nodeItemProvider == null) {
			nodeItemProvider = new NodeItemProvider(this);
		}

		return nodeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Attribute} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeItemProvider attributeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Attribute}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAttributeAdapter() {
		if (attributeItemProvider == null) {
			attributeItemProvider = new AttributeItemProvider(this);
		}

		return attributeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Edge} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EdgeItemProvider edgeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Edge}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEdgeAdapter() {
		if (edgeItemProvider == null) {
			edgeItemProvider = new EdgeItemProvider(this);
		}

		return edgeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.IndependentUnit} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IndependentUnitItemProvider independentUnitItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.IndependentUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIndependentUnitAdapter() {
		if (independentUnitItemProvider == null) {
			independentUnitItemProvider = new IndependentUnitItemProvider(this);
		}

		return independentUnitItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.SequentialUnit} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SequentialUnitItemProvider sequentialUnitItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.SequentialUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSequentialUnitAdapter() {
		if (sequentialUnitItemProvider == null) {
			sequentialUnitItemProvider = new SequentialUnitItemProvider(this);
		}

		return sequentialUnitItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.ConditionalUnit} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConditionalUnitItemProvider conditionalUnitItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.ConditionalUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createConditionalUnitAdapter() {
		if (conditionalUnitItemProvider == null) {
			conditionalUnitItemProvider = new ConditionalUnitItemProvider(this);
		}

		return conditionalUnitItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.PriorityUnit} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PriorityUnitItemProvider priorityUnitItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.PriorityUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPriorityUnitAdapter() {
		if (priorityUnitItemProvider == null) {
			priorityUnitItemProvider = new PriorityUnitItemProvider(this);
		}

		return priorityUnitItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.AmalgamationUnit} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AmalgamationUnitItemProvider amalgamationUnitItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.AmalgamationUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAmalgamationUnitAdapter() {
		if (amalgamationUnitItemProvider == null) {
			amalgamationUnitItemProvider = new AmalgamationUnitItemProvider(this);
		}

		return amalgamationUnitItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.CountedUnit} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CountedUnitItemProvider countedUnitItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.CountedUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCountedUnitAdapter() {
		if (countedUnitItemProvider == null) {
			countedUnitItemProvider = new CountedUnitItemProvider(this);
		}

		return countedUnitItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.NestedCondition} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NestedConditionItemProvider nestedConditionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.NestedCondition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNestedConditionAdapter() {
		if (nestedConditionItemProvider == null) {
			nestedConditionItemProvider = new NestedConditionItemProvider(this);
		}

		return nestedConditionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.UnaryFormula} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnaryFormulaItemProvider unaryFormulaItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.UnaryFormula}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUnaryFormulaAdapter() {
		if (unaryFormulaItemProvider == null) {
			unaryFormulaItemProvider = new UnaryFormulaItemProvider(this);
		}

		return unaryFormulaItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.BinaryFormula} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BinaryFormulaItemProvider binaryFormulaItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.BinaryFormula}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBinaryFormulaAdapter() {
		if (binaryFormulaItemProvider == null) {
			binaryFormulaItemProvider = new BinaryFormulaItemProvider(this);
		}

		return binaryFormulaItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.And} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AndItemProvider andItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.And}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAndAdapter() {
		if (andItemProvider == null) {
			andItemProvider = new AndItemProvider(this);
		}

		return andItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Or} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OrItemProvider orItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Or}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOrAdapter() {
		if (orItemProvider == null) {
			orItemProvider = new OrItemProvider(this);
		}

		return orItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Not} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NotItemProvider notItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Not}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNotAdapter() {
		if (notItemProvider == null) {
			notItemProvider = new NotItemProvider(this);
		}

		return notItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.ParameterMapping} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParameterMappingItemProvider parameterMappingItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.ParameterMapping}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createParameterMappingAdapter() {
		if (parameterMappingItemProvider == null) {
			parameterMappingItemProvider = new ParameterMappingItemProvider(this);
		}

		return parameterMappingItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (namedElementItemProvider != null) namedElementItemProvider.dispose();
		if (transformationSystemItemProvider != null) transformationSystemItemProvider.dispose();
		if (ruleItemProvider != null) ruleItemProvider.dispose();
		if (attributeConditionItemProvider != null) attributeConditionItemProvider.dispose();
		if (parameterItemProvider != null) parameterItemProvider.dispose();
		if (graphItemProvider != null) graphItemProvider.dispose();
		if (mappingItemProvider != null) mappingItemProvider.dispose();
		if (nodeItemProvider != null) nodeItemProvider.dispose();
		if (attributeItemProvider != null) attributeItemProvider.dispose();
		if (edgeItemProvider != null) edgeItemProvider.dispose();
		if (independentUnitItemProvider != null) independentUnitItemProvider.dispose();
		if (sequentialUnitItemProvider != null) sequentialUnitItemProvider.dispose();
		if (conditionalUnitItemProvider != null) conditionalUnitItemProvider.dispose();
		if (priorityUnitItemProvider != null) priorityUnitItemProvider.dispose();
		if (amalgamationUnitItemProvider != null) amalgamationUnitItemProvider.dispose();
		if (countedUnitItemProvider != null) countedUnitItemProvider.dispose();
		if (nestedConditionItemProvider != null) nestedConditionItemProvider.dispose();
		if (unaryFormulaItemProvider != null) unaryFormulaItemProvider.dispose();
		if (binaryFormulaItemProvider != null) binaryFormulaItemProvider.dispose();
		if (andItemProvider != null) andItemProvider.dispose();
		if (orItemProvider != null) orItemProvider.dispose();
		if (notItemProvider != null) notItemProvider.dispose();
		if (parameterMappingItemProvider != null) parameterMappingItemProvider.dispose();
	}

}
