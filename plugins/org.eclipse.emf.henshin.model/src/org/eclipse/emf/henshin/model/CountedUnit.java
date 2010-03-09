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
package org.eclipse.emf.henshin.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Counted Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.henshin.model.CountedUnit#getSubUnit <em>Sub Unit</em>}</li>
 *   <li>{@link org.eclipse.emf.henshin.model.CountedUnit#getCount <em>Count</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.henshin.model.HenshinPackage#getCountedUnit()
 * @model
 * @generated
 */
public interface CountedUnit extends TransformationUnit {
	/**
	 * Returns the value of the '<em><b>Sub Unit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Unit</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Unit</em>' containment reference.
	 * @see #setSubUnit(TransformationUnit)
	 * @see org.eclipse.emf.henshin.model.HenshinPackage#getCountedUnit_SubUnit()
	 * @model containment="true" required="true"
	 * @generated
	 */
	TransformationUnit getSubUnit();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.henshin.model.CountedUnit#getSubUnit <em>Sub Unit</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Unit</em>' containment reference.
	 * @see #getSubUnit()
	 * @generated
	 */
	void setSubUnit(TransformationUnit value);

	/**
	 * Returns the value of the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Count</em>' attribute.
	 * @see #setCount(int)
	 * @see org.eclipse.emf.henshin.model.HenshinPackage#getCountedUnit_Count()
	 * @model
	 * @generated
	 */
	int getCount();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.henshin.model.CountedUnit#getCount <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Count</em>' attribute.
	 * @see #getCount()
	 * @generated
	 */
	void setCount(int value);

} // CountedUnit
