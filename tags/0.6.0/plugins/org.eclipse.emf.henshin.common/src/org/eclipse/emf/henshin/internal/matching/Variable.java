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
package org.eclipse.emf.henshin.internal.matching;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.henshin.internal.constraints.AttributeConstraint;
import org.eclipse.emf.henshin.internal.constraints.DanglingConstraint;
import org.eclipse.emf.henshin.internal.constraints.ParameterConstraint;
import org.eclipse.emf.henshin.internal.constraints.ReferenceConstraint;
import org.eclipse.emf.henshin.internal.constraints.TypeConstraint;

public class Variable {// implements Comparable<Variable> {
	private TypeConstraint typeConstraint;
	private List<AttributeConstraint> attributeConstraints;
	private List<ParameterConstraint> parameterConstraints;
	private List<ReferenceConstraint> referenceConstraints;
	private Collection<DanglingConstraint> danglingConstraints;

	public Variable(EClass type) {
		typeConstraint = new TypeConstraint(type);
		
		attributeConstraints = new ArrayList<AttributeConstraint>();
		parameterConstraints = new ArrayList<ParameterConstraint>();
		referenceConstraints = new ArrayList<ReferenceConstraint>();
		danglingConstraints = new ArrayList<DanglingConstraint>();
	}

	public TypeConstraint getTypeConstraint() {
		return typeConstraint;
	}

	public List<AttributeConstraint> getAttributeConstraints() {
		return attributeConstraints;
	}

	public List<ParameterConstraint> getParameterConstraints() {
		return parameterConstraints;
	}

	public List<ReferenceConstraint> getReferenceConstraints() {
		return referenceConstraints;
	}

	public Collection<DanglingConstraint> getDanglingConstraints() {
		return danglingConstraints;
	}
}