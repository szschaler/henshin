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
package org.eclipse.emf.henshin.editor.commands;

/**
 * Utility class for quantifying common conditions.
 * 
 * @author Gregor Bonifer
 * @author Stefan Jurack (sjurack)
 */
public class QuantUtil {
	
	public static boolean noneNull(Object... args) {
		for (Object arg : args)
			if (arg == null) return false;
		return true;
	}
	
	public static boolean allInstancesOf(Class<?> clazz, Object... args) {
		for (Object arg : args)
			if (!clazz.isInstance(arg)) return false;
		return true;
	}
	
	public static boolean allIdentical(Object... args) {
		boolean initialized = false;
		Object cmp = null;
		for (Object arg : args) {
			if (!initialized) {
				cmp = arg;
				initialized = true;
			} else if (arg != cmp) return false;
		}
		return true;
	}
	
	public static boolean allIdenticalAndNotNull(Object... args) {
		return noneNull(args) && allIdentical(args);
	}
	
	public static boolean anyNull(Object... args) {
		return !noneNull(args);
	}
	
}
