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
package org.eclipse.emf.henshin.statespace.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.eclipse.emf.henshin.statespace.State;
import org.eclipse.emf.henshin.statespace.StateSpace;
import org.eclipse.emf.henshin.statespace.StateSpaceException;
import org.eclipse.emf.henshin.statespace.Transition;

/**
 * Multi-threaded version of the basic state space manager.
 * @author Christian Krause
 * @generated NOT
 */
public class MultiThreadedStateSpaceManager extends StateSpaceManagerImpl {
	
	/**
	 * Cached number of available processors.
	 */
	public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
	
	// Number of threads to be used.
	private int numThreads;
	
	// Executor service.
	private ExecutorService executor;
	
	// Future objects.
	private Future<StateSpaceException>[] futures;
	
	// Thread that prepares the exploration.
	private Thread preparer;
	
	// HashMap for prepared states.
	private Map<State,List<Transition>> preparedStates;
	
	/**
	 * Default constructor.
	 * @param stateSpace State space.
	 * @param numThreads Number of threads to be used.
	 */
	@SuppressWarnings("unchecked")
	public MultiThreadedStateSpaceManager(StateSpace stateSpace, int numThreads) {
		super(stateSpace);
		this.numThreads = numThreads = Math.max(numThreads, 1);
		this.executor = Executors.newFixedThreadPool(numThreads);
		this.futures = new Future[numThreads];
		this.preparedStates = Collections.synchronizedMap(new HashMap<State,List<Transition>>());
	}

	/**
	 * Default constructor.
	 * @param stateSpace State space.
	 */
	public MultiThreadedStateSpaceManager(StateSpace stateSpace) {
		this(stateSpace, CPU_COUNT);
	}

	/**
	 * Explore states concurrently.
	 * @param states States to be explored.
	 * @param generateLocations Whether to generate locations.
	 * @return Newly created transitions.
	 * @throws StateSpaceException On state space errors.
	 */
	public synchronized List<Transition> exploreStates(List<State> states, boolean generateLocations) throws StateSpaceException {
		
		// If we haven't start the preparer thread, we do it now. But not earlier.
		if (preparer==null) {
			preparer = new Thread(new PreparationWorker());
			preparer.start();
		}
		
		// We use a new list for the states:
		List<State> queue = new Vector<State>(states);
		List<Transition> result = new Vector<Transition>();
		
		try {
			// Launch the workers:
			for (int i=0; i<numThreads; i++) {
				futures[i] = executor.submit(new ExplorationWorker(queue, result, generateLocations));
			}
			// Evaluate the results:
			for (int i=0; i<numThreads; i++) {
				if (futures[i].get()!=null) {
					throw futures[i].get();
				}
			}
		} catch (Throwable t) {
			throw wrapException(t);
		}
		
		// Done:
		return result;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.statespace.impl.StateSpaceManagerImpl#doExplore(org.eclipse.emf.henshin.statespace.State)
	 */
	@Override
	protected List<Transition> doExplore(State state) throws StateSpaceException {
		
		// Get the cached result or compute it if necessary.
		List<Transition> result = preparedStates.get(state);
		if (result==null) {
			result = super.doExplore(state);
			preparedStates.put(state, result);
		}
		return result;	// That's all.
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.statespace.impl.StateSpaceManagerImpl#clearStateModelCache()
	 */
	@Override
	public void clearStateModelCache() {
		super.clearStateModelCache();
		preparedStates.clear();
	}
	
	/*
	 * Wrap an exception.
	 */
	private StateSpaceException wrapException(Throwable t) {
		return (t instanceof StateSpaceException) ? (StateSpaceException) t : new StateSpaceException(t);
	}
	
	/*
	 * Private explorer worker class. Delegates to exploreState().
	 */
	private class ExplorationWorker implements Callable<StateSpaceException> {
		
		// States to be explored:
		private List<State> states;
		
		// Result list:
		private List<Transition> result;
		
		// Whether to generate locations:
		private boolean generateLocations;
		
		/*
		 * Default constructor.
		 */
		ExplorationWorker(List<State> states, List<Transition> result, boolean generateLocations) {
			this.states = states;
			this.result = result;
			this.generateLocations = generateLocations;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.util.concurrent.Callable#call()
		 */
		@Override
		public StateSpaceException call() {
			
			while (true) {

				// Get the next state to be explored:
				State next;
				try {
					next = states.remove(0);
				}
				catch (IndexOutOfBoundsException e) {
					return null; // We are done.
				}

				// Now explore it:
				try {
					result.addAll(exploreState(next, generateLocations));
					preparedStates.remove(next);
				}
				catch (Throwable t) {
					return wrapException(t);
				}
			}
			
		}
	}
	
	/*
	 * Worker class that pre-computes exploration results.
	 */
	private class PreparationWorker implements Runnable {
		
		/*
		 * (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			while (true) {
				List<State> open = getStateSpace().getOpenStates();
				for (int i=0; i<open.size(); i++) {
					try {
						doExplore(open.get(i));
						if (i % 1000==0) Thread.sleep(100);
					} catch (Throwable t) {}
				}
			}
		}
	}
	
}
