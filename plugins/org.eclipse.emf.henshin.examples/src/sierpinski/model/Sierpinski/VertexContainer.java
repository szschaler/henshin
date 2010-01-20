/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package sierpinski.model.Sierpinski;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vertex Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sierpinski.model.Sierpinski.VertexContainer#getVertices <em>Vertices</em>}</li>
 * </ul>
 * </p>
 *
 * @see sierpinski.model.Sierpinski.SierpinskiPackage#getVertexContainer()
 * @model
 * @generated
 */
public interface VertexContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Vertices</b></em>' containment reference list.
	 * The list contents are of type {@link sierpinski.model.Sierpinski.Vertex}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vertices</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vertices</em>' containment reference list.
	 * @see sierpinski.model.Sierpinski.SierpinskiPackage#getVertexContainer_Vertices()
	 * @model containment="true"
	 * @generated
	 */
	EList<Vertex> getVertices();

} // VertexContainer