/*******************************************************************************
 * Copyright (c) 2010 CWI Amsterdam, Technical University Berlin, 
 * Philipps-University Marburg and others. All rights reserved. 
 * This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Philipps-University Marburg - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.henshin.testframework;

import org.eclipse.emf.henshin.interpreter.util.Match;
import org.eclipse.emf.henshin.model.Parameter;

/**
 * Assertions for Parameters
 * 
 * @author Felix Rieger
 * @author Stefan Jurack (sjurack)
 * 
 */

public class Parameters {
	
	/**
	 * Asserts that the {@link Match} contains a parameter mapping which
	 * contains the specified object.
	 * 
	 * @param ma
	 * @param obj
	 * @throws AssertionError
	 */
	public static void assertParameterMappingContainsObject(Match ma, Object obj)
			throws AssertionError {
		if (!(ma.getParameterValues().containsValue(obj))) {
			throw new AssertionError("expected: Parameter Mapping contains " + obj);
		}
	}
	
	/**
	 * Asserts that the {@link Match} contains a parameter mapping between the
	 * Parameter "parameterName" and the specified Object.
	 * 
	 * @param ma
	 * @param parameterName
	 * @param obj
	 * @throws AssertionError
	 */
	public static void assertParameterMappingEquals(Match ma, String parameterName, Object obj)
			throws AssertionError {
		for (Parameter p : ma.getParameterValues().keySet()) {
			if (p.getName().equals(parameterName)) {
				if (ma.getParameterValues().get(p).equals(obj)) {
					return;
				} else {
					throw new AssertionError("expected: Parameter \"" + parameterName
							+ "\" equals " + obj + "; (actual value: "
							+ ma.getParameterValues().get(p) + ")");
				}
			}
		}
		
		throw new AssertionError("expected: Parameter \"" + parameterName
				+ "\" exists in parameter mapping.");
		
	}
	
}
