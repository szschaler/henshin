package org.eclipse.emf.henshin.statespace.external.prism;

import java.io.File;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.henshin.statespace.StateSpace;
import org.eclipse.emf.henshin.statespace.StateSpaceIndex;
import org.eclipse.emf.henshin.statespace.StateSpacePlugin;
import org.eclipse.emf.henshin.statespace.external.AbstractFileBasedValidator;
import org.eclipse.emf.henshin.statespace.validation.ValidationResult;

/**
 * MDP state space validator for Henshin. Uses PRISM as back-end.
 * @author Christian Krause
 */
public class MDPStateSpaceValidator extends AbstractFileBasedValidator {
	
	/**
	 * ID of this validator.
	 */
	public static final String VALIDATOR_ID = "org.eclipse.emf.henshin.statespace.validator.prism.mdp";
	
	/**
	 * Register this validator in the global validator registry in the state space plug-in.
	 */
	public static void register() {
		StateSpacePlugin.INSTANCE.getValidators().put(VALIDATOR_ID, new MDPStateSpaceValidator());
	}
	
	/**
	 * Default constructor.
	 */
	public MDPStateSpaceValidator() {
		// no initialization
	}

	/**
	 * Constructor.
	 * @param stateSpaceIndex State space index to be used.
	 */
	public MDPStateSpaceValidator(StateSpaceIndex stateSpaceIndex) {
		setStateSpaceIndex(stateSpaceIndex);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.statespace.validation.StateSpaceValidator#validate(org.eclipse.emf.henshin.statespace.StateSpace, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public ValidationResult validate(StateSpace stateSpace, IProgressMonitor monitor) throws Exception {
		
		if (monitor==null) {
			monitor = new NullProgressMonitor();
		}
		monitor.beginTask("Checking PCTL property...", 4);
		
		// Generate the model file.
		MDPStateSpaceExporter exporter = new MDPStateSpaceExporter();
		File traFile = export(stateSpace, exporter, null, "nm",  new SubProgressMonitor(monitor, 1));

		// Generate the CSL file.
		String expanded = PRISMUtil.expandLabels(property, index, exporter.getTuples(), new SubProgressMonitor(monitor, 1));
		File pctlFile = createTempFile("property", ".pctl", expanded);
				
		// Invoke the PRISM tool:
		monitor.subTask("Running PRISM...");
		Map<String, String> constants = PRISMUtil.getAllProbs(stateSpace, true);
		Process process = PRISMUtil.invokePRISM(stateSpace, traFile, pctlFile, new String[] { "-fixdl" , "-gaussseidel" }, constants, true,  new SubProgressMonitor(monitor, 1));
		
		// Parse the experiments:
		ValidationResult result = PRISMExperiment.parseValidationResult(stateSpace, process, new SubProgressMonitor(monitor, 1));
		process.waitFor();
		
		return result;
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.statespace.validation.Validator#usesProperty()
	 */
	@Override
	public boolean usesProperty() {
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.statespace.validation.Validator#getName()
	 */
	@Override
	public String getName() {
		return "PRISM MDP";
	}

}
