/*******************************************************************************
 * Copyright (c) 2010 CWI Amsterdam, Technical University Berlin, 
 * Philipps-University Marburg and others. All rights reserved.
 * This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CWI Amsterdam - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.henshin.statespace.external.prism;

import java.io.File;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.henshin.statespace.StateSpace;
import org.eclipse.emf.henshin.statespace.external.AbstractFileBasedValidator;
import org.eclipse.emf.henshin.statespace.validation.ValidationResult;

/**
 * PRISM CTMC state space validator.
 * @author Christian Krause
 */
public class CTMCStateSpaceValidator extends AbstractFileBasedValidator {
			
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.statespace.validation.StateSpaceValidator#validate(org.eclipse.emf.henshin.statespace.StateSpace, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public ValidationResult validate(StateSpace stateSpace, IProgressMonitor monitor) throws Exception {
		
		monitor.beginTask("Checking CSL property...", 4);

		// Generate the model file.
		CTMCStateSpaceExporter exporter = new CTMCStateSpaceExporter();
		File modelFile = export(stateSpace, exporter, null, "sm", new SubProgressMonitor(monitor, 1));

		// Generate the CSL file.
		String expanded = PRISMUtil.expandLabels(property, index, exporter.getTuples(), new SubProgressMonitor(monitor, 1));
		File cslFile = createTempFile("property", ".csl", expanded);


		// Invoke the PRISM tool:
		monitor.subTask("Running PRISM...");
		Map<String, String> constants = PRISMUtil.getAllRates(stateSpace, true);
		Process process = PRISMUtil.invokePRISM(stateSpace, modelFile, cslFile, null, constants, true, new SubProgressMonitor(monitor, 1));
		
		// Parse the experiments:
		return PRISMExperiment.parseValidationResult(stateSpace, process, new SubProgressMonitor(monitor, 1));
		
	}	
			
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.statespace.validation.Validator#getName()
	 */
	@Override
	public String getName() {
		return "PRISM CTMC";
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.statespace.validation.Validator#usesProperty()
	 */
	@Override
	public boolean usesProperty() {
		return true;
	}

}