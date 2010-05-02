/*******************************************************************************
 * Copyright (c) 2010 CWI Amsterdam, Technical University of Berlin, 
 * University of Marburg and others. All rights reserved. 
 * This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Technical University of Berlin - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.henshin.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.henshin.model.HenshinPackage;
import org.eclipse.emf.henshin.model.IndependentUnit;
import org.eclipse.emf.henshin.model.NamedElement;
import org.eclipse.emf.henshin.model.Port;
import org.eclipse.emf.henshin.model.PortMapping;
import org.eclipse.emf.henshin.model.TransformationUnit;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Independent Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.henshin.model.impl.IndependentUnitImpl#getSubUnits <em>Sub Units</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IndependentUnitImpl extends TransformationUnitImpl implements IndependentUnit {
	/**
	 * The cached value of the '{@link #getSubUnits() <em>Sub Units</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubUnits()
	 * @generated
	 * @ordered
	 */
	protected EList<TransformationUnit> subUnits;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IndependentUnitImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HenshinPackage.Literals.INDEPENDENT_UNIT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TransformationUnit> getSubUnits() {
		if (subUnits == null) {
			subUnits = new EObjectResolvingEList<TransformationUnit>(TransformationUnit.class, this, HenshinPackage.INDEPENDENT_UNIT__SUB_UNITS);
		}
		return subUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<TransformationUnit> getAllSubUnits() {
		List<TransformationUnit> allunits = new ArrayList<TransformationUnit>();

		for (TransformationUnit unit : this.subUnits) {
			allunits.addAll(unit.getAllSubUnits());
		}// for
		return new BasicEList<TransformationUnit>(allunits);
	}// getAllSubUnits

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HenshinPackage.INDEPENDENT_UNIT__SUB_UNITS:
				return getSubUnits();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case HenshinPackage.INDEPENDENT_UNIT__SUB_UNITS:
				getSubUnits().clear();
				getSubUnits().addAll((Collection<? extends TransformationUnit>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case HenshinPackage.INDEPENDENT_UNIT__SUB_UNITS:
				getSubUnits().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case HenshinPackage.INDEPENDENT_UNIT__SUB_UNITS:
				return subUnits != null && !subUnits.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //IndependentUnitImpl
