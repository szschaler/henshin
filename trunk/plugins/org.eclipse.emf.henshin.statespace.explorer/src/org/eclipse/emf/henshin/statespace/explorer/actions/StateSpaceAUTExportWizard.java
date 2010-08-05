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
package org.eclipse.emf.henshin.statespace.explorer.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.henshin.statespace.StateSpace;
import org.eclipse.emf.henshin.statespace.resource.StateSpaceResource;

/**
 * Export wizard that converts state spaces into the AUT format.
 * @author Christian Krause
 */
public class StateSpaceAUTExportWizard extends AbstractStateSpaceExportWizard {
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.statespace.explorer.actions.AbstractStateSpaceExportWizard#doExport(org.eclipse.emf.henshin.statespace.StateSpace, org.eclipse.core.resources.IFile, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void doExport(StateSpace stateSpace, IFile file, IProgressMonitor monitor) throws Exception {
		
		monitor.beginTask("Export as AUT", 2);
		
		// Paste into a buffer:
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		((StateSpaceResource) stateSpace.eResource()).exportAsAUT(buffer, new SubProgressMonitor(monitor,1));
		
		// And write buffer contents to the file:
		file.create(new ByteArrayInputStream(buffer.toByteArray()), true, new SubProgressMonitor(monitor,1));
		monitor.done();
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.statespace.explorer.actions.AbstractStateSpaceExportWizard#getDescription()
	 */
	@Override
	protected String getDescription() {
		return "Export State Space as AUT file.";
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.statespace.explorer.actions.AbstractStateSpaceExportWizard#getFileExtension()
	 */
	@Override
	protected String getFileExtension() {
		return "aut";
	}

}
