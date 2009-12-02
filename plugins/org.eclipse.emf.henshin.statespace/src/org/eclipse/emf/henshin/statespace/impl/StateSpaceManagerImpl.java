package org.eclipse.emf.henshin.statespace.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.henshin.statespace.State;
import org.eclipse.emf.henshin.statespace.StateSpace;
import org.eclipse.emf.henshin.statespace.StateSpaceFactory;
import org.eclipse.emf.henshin.statespace.StateSpaceManager;
import org.eclipse.emf.henshin.statespace.Transition;

/**
 * Default state space manager implementation.
 * 
 * @author Christian Krause
 * @generated NOT
 */
public class StateSpaceManagerImpl extends StateSpaceManagerWithIndex {
	
	/**
	 * Default memory usage: 10%
	 */
	public static final double DEFAULT_MEMORY_USAGE = 0.1;
	
	/**
	 * Default cache size: 64
	 */
	public static final int DEFAULT_CACHE_SIZE = 64;
	
	// Percentage of models that are kept in memory:
	private double memoryUsage;
	
	// Cache size:
	private int cacheSize;
	
	// Cache for the last accessed states:
	private Map<State,Resource> cache = new LinkedHashMap<State,Resource>() {	
		private static final long serialVersionUID = 1L;
		@Override @SuppressWarnings("unchecked")
		protected boolean removeEldestEntry(Map.Entry eldest) {
			return size() > cacheSize;
		}
	};
	
	/**
	 * Private constructor.
	 */
	private StateSpaceManagerImpl(StateSpace stateSpace, double memoryUsage, int cacheSize) {
		super(stateSpace);
		this.memoryUsage = Math.max(Math.min(memoryUsage,1),0);
		this.cacheSize = cacheSize;
	}
	
	/**
	 * Create a new state space manager instance with default memory usage and cache size.
	 * @param stateSpace State space to be managed.
	 * @param monitor Progress monitor.
	 * @return The created state space manager.
	 */
	public static StateSpaceManager create(StateSpace stateSpace, IProgressMonitor monitor) {
		return create(stateSpace, DEFAULT_MEMORY_USAGE, DEFAULT_CACHE_SIZE, monitor);
	}
	
	/**
	 * Create a new manager for a given state space. This builds up the 
	 * index and computes temporary models.
	 * @param stateSpace State space to be managed.
	 * @param memoryUsage Memory usage: between 0 and 1 (inclusive).
	 * @param cacheSize Number of cached models.
	 * @param monitor Progress monitor.
	 * @return The created state space manager.
	 */
	public static StateSpaceManager create(StateSpace stateSpace, double memoryUsage, int cacheSize, IProgressMonitor monitor) {
		StateSpaceManagerImpl manager = new StateSpaceManagerImpl(stateSpace, memoryUsage, cacheSize);
		manager.reload(monitor);
		return manager;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.statespace.StateSpaceManager#getModel(org.eclipse.emf.henshin.statespace.State)
	 */
	public Resource getModel(State state) {
		
		// Model already set?
		if (state.getModel()!=null) {
			return state.getModel();
		}
		
		// Cached?
		if (cache.containsKey(state)) {
			return cache.get(state);
		}
		
		// Otherwise derive the model:
		Resource model = null;

		

		// Decide whether the current model should be kept in memory:
		int states = getStateSpace().getStates().size();
		int cached = (int) (states * memoryUsage);			
		boolean cacheCurrent = (cached>0) && (states % cached)==0;
		
		// Associated the model with the state (or not):
		state.setModel(cacheCurrent ? model : null);
		
		// Always add it to the cache (is maintained automatically):
		cache.put(state, model);
		
		// Done.
		return model;
		
	}
	

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.statespace.StateSpaceManager#explore(org.eclipse.emf.henshin.statespace.State)
	 */
	public List<State> exploreState(State state) {
		
		List<State> newStates = new ArrayList<State>();
		Resource model = getModel(state);
		
		// Find all matches:
		
		// Create a copy of the model.
		Resource transformed = new ResourceImpl();
		transformed.getContents().addAll(EcoreUtil.copyAll(model.getContents()));
		
		// Transform it:
		
		
		// Check if a corresponding state exists already:
		int hash = hashCode(transformed);
		if (getState(transformed, hash)!=null) {
			// Add an outgoing transition if not existent:
			
		} else {
			State newState = createState(transformed, hash);
			
			// MOVE THIS CODE TO STATE:
			Transition transition = StateSpaceFactory.INSTANCE.createTransition();
			//transition.setRule(...);
			//transition.setMatch(...);
			transition.setTarget(newState);
			transition.setSource(state);
			
			// Remember the new state:
			newStates.add(newState);
			
		}
		
		// Mark the state as closed:
		if (state.isOpen()) {
			state.setOpen(false);
		}
		
		// Done.
		return newStates;
		
	}

}
