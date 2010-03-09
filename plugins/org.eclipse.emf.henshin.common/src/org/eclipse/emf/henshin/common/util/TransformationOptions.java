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
package org.eclipse.emf.henshin.common.util;

public class TransformationOptions {
	private boolean injective;
	private boolean deterministic;
	
	public TransformationOptions() {
		injective = true;
		deterministic = true;
	}

	public boolean isInjective() {
		return injective;
	}

	public void setInjective(boolean injective) {
		this.injective = injective;
	}

	public boolean isDeterministic() {
		return deterministic;
	}

	public void setDeterministic(boolean deterministic) {
		this.deterministic = deterministic;
	}
	
	
}
