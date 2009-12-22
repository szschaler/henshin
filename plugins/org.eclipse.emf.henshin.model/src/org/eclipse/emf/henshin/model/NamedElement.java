/**
 * <copyright>
 * </copyright>
 *
 * $Id: NamedElement.java,v 1.1 2009/10/28 10:38:09 enrico Exp $
 */
package org.eclipse.emf.henshin.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.henshin.model.NamedElement#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.henshin.model.HenshinPackage#getNamedElement()
 * @model
 * @generated
 */
public interface NamedElement extends EObject {
        /**
         * Returns the value of the '<em><b>Name</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Name</em>' attribute isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Name</em>' attribute.
         * @see #setName(String)
         * @see org.eclipse.emf.henshin.model.HenshinPackage#getNamedElement_Name()
         * @model
         * @generated
         */
        String getName();

        /**
         * Sets the value of the '{@link org.eclipse.emf.henshin.model.NamedElement#getName <em>Name</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @param value the new value of the '<em>Name</em>' attribute.
         * @see #getName()
         * @generated
         */
        void setName(String value);

} // NamedElement
