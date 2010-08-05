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
package org.eclipse.emf.henshin.statespace.explorer.commands;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.henshin.statespace.State;
import org.eclipse.emf.henshin.statespace.StateSpaceManager;
import org.eclipse.emf.henshin.statespace.StateSpaceException;

/**
 * Command for creating an initial state.
 * @author Christian Krause
 */
public class CreateInitialStateCommand extends AbstractStateSpaceCommand {
	
	// State to be added.
	private State state;
	
	// State model:
	private Resource model;
		
	// State coordinates:
	private int[] location;
	
	/**
	 * Default constructor.
	 * @param state State to be added.
	 * @param stateSpace State space.
	 */
	public CreateInitialStateCommand(Resource model, StateSpaceManager manager) {
		super("create initial state", manager);
		this.model = model;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	@Override
	public boolean canExecute() {
		return model!=null && getStateSpaceManager()!=null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.statespace.explorer.commands.AbstractStateSpaceCommand#doRedo()
	 */
	@Override
	public void doRedo() throws StateSpaceException {
		state = getStateSpaceManager().createInitialState(model);
		state.setLocation(location);	
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.statespace.explorer.commands.AbstractStateSpaceCommand#doUndo()
	 */
	@Override
	public void doUndo() throws StateSpaceException {
		getStateSpaceManager().removeState(state);
	}
	
	/**
	 * Set the state coordinates.
	 */
	public void setLocation(int... location) {
		this.location = location;
	}
	
}