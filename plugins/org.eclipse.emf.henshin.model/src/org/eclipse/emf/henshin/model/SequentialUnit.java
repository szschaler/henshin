/**
 * <copyright>
 * </copyright>
 *
 * $Id: SequentialUnit.java,v 1.1 2009/10/28 10:38:07 enrico Exp $
 */
package org.eclipse.emf.henshin.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sequential Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.henshin.model.SequentialUnit#getSubUnits <em>Sub Units</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.henshin.model.HenshinPackage#getSequentialUnit()
 * @model
 * @generated
 */
public interface SequentialUnit extends TransformationUnit {
        /**
         * Returns the value of the '<em><b>Sub Units</b></em>' containment reference list.
         * The list contents are of type {@link org.eclipse.emf.henshin.model.TransformationUnit}.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Sub Units</em>' containment reference list isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Sub Units</em>' containment reference list.
         * @see org.eclipse.emf.henshin.model.HenshinPackage#getSequentialUnit_SubUnits()
         * @model containment="true"
         * @generated
         */
        EList<TransformationUnit> getSubUnits();

} // SequentialUnit
