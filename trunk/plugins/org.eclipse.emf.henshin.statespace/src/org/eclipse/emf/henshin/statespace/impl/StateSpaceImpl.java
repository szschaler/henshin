/*******************************************************************************
 * Copyright (c) 2010 CWI Amsterdam, Technical University of Berlin, 
 * University of Marburg and others. All rights reserved. 
 * This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CWI Amsterdam - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.henshin.statespace.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.statespace.State;
import org.eclipse.emf.henshin.statespace.StateEqualityHelper;
import org.eclipse.emf.henshin.statespace.StateSpace;
import org.eclipse.emf.henshin.statespace.StateSpaceFactory;
import org.eclipse.emf.henshin.statespace.StateSpacePackage;
import org.eclipse.emf.henshin.statespace.Transition;

/**
 * Concrete implementation of the {@link State} interface.
 * @generated
 */
public class StateSpaceImpl extends StorageImpl implements StateSpace {

	/**
	 * Default constructor.
	 * @generated NOT
	 */
	protected StateSpaceImpl() {
		super();
		// Create a default equality helper:
		setEqualityHelper(StateSpaceFactory.eINSTANCE.createStateEqualityHelper());
	}

	/**
	 * Get the list of open states in this state space.
	 * @generated NOT
	 */
	public EList<State> getOpenStates() {
		if (openStates == null) {
			openStates = new EObjectResolvingEList<State>(State.class, this, StateSpacePackage.STATE_SPACE__OPEN_STATES) {
				private static final long serialVersionUID = 1L;
				@Override
				protected boolean isUnique() {
					// For performance we omit the uniqueness check.
					return false;
				}
			};
		}
		return openStates;
	}
	
	/**
	 * Remove a state and detach its transitions from the other states.
	 * The transitions are still connected to the removed node afterwards.
	 * All predecessor states are automatically marked as open.
	 * @generated NOT
	 */
	public boolean removeState(State state) {
		
		// Try to remove the state:
		if (getStates().remove(state)) {
			
			// Detach incoming transitions:
			for (Transition transition : state.getIncoming()) {
				
				// Mark the predecessor state as open!
				State source = transition.getSource();
				if (source!=null) {
					source.setOpen(true);
					if (!getOpenStates().contains(source)) {
						getOpenStates().add(source);
					}
				}
				
				// Detach...
				transition.setSource(null);
				
			}
			
			// Detach outgoing transitions:
			for (Transition transition : state.getOutgoing()) {
				transition.setTarget(null);
			}
			
			// Done.
			return true;
			
		} else {
			return false;
		}
		
	}

	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * The cached value of the '{@link #getRules() <em>Rules</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRules()
	 * @generated
	 * @ordered
	 */
	protected EList<Rule> rules;

	
	/**
	 * The cached value of the '{@link #getStates() <em>States</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStates()
	 * @generated
	 * @ordered
	 */
	protected EList<State> states;

	/**
	 * The cached value of the '{@link #getInitialStates() <em>Initial States</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialStates()
	 * @generated
	 * @ordered
	 */
	protected EList<State> initialStates;

	/**
	 * The cached value of the '{@link #getOpenStates() <em>Open States</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOpenStates()
	 * @generated
	 * @ordered
	 */
	protected EList<State> openStates;

	/**
	 * The default value of the '{@link #getTransitionCount() <em>Transition Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitionCount()
	 * @generated
	 * @ordered
	 */
	protected static final int TRANSITION_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTransitionCount() <em>Transition Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitionCount()
	 * @generated
	 * @ordered
	 */
	protected int transitionCount = TRANSITION_COUNT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEqualityHelper() <em>Equality Helper</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEqualityHelper()
	 * @generated
	 * @ordered
	 */
	protected StateEqualityHelper equalityHelper;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StateSpacePackage.Literals.STATE_SPACE;
	}
	
	/**
	 * Get the list of states in this state space.
	 * @generated
	 */
	public EList<State> getStates() {
		if (states == null) {
			states = new EObjectContainmentWithInverseEList<State>(State.class, this, StateSpacePackage.STATE_SPACE__STATES, StateSpacePackage.STATE__STATE_SPACE);
		}
		return states;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<State> getInitialStates() {
		if (initialStates == null) {
			initialStates = new EObjectResolvingEList<State>(State.class, this, StateSpacePackage.STATE_SPACE__INITIAL_STATES);
		}
		return initialStates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Rule> getRules() {
		if (rules == null) {
			rules = new EObjectResolvingEList<Rule>(Rule.class, this, StateSpacePackage.STATE_SPACE__RULES);
		}
		return rules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTransitionCount() {
		return transitionCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransitionCount(int newTransitionCount) {
		int oldTransitionCount = transitionCount;
		transitionCount = newTransitionCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateSpacePackage.STATE_SPACE__TRANSITION_COUNT, oldTransitionCount, transitionCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateEqualityHelper getEqualityHelper() {
		return equalityHelper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEqualityHelper(StateEqualityHelper newEqualityHelper, NotificationChain msgs) {
		StateEqualityHelper oldEqualityHelper = equalityHelper;
		equalityHelper = newEqualityHelper;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StateSpacePackage.STATE_SPACE__EQUALITY_HELPER, oldEqualityHelper, newEqualityHelper);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEqualityHelper(StateEqualityHelper newEqualityHelper) {
		if (newEqualityHelper != equalityHelper) {
			NotificationChain msgs = null;
			if (equalityHelper != null)
				msgs = ((InternalEObject)equalityHelper).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StateSpacePackage.STATE_SPACE__EQUALITY_HELPER, null, msgs);
			if (newEqualityHelper != null)
				msgs = ((InternalEObject)newEqualityHelper).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StateSpacePackage.STATE_SPACE__EQUALITY_HELPER, null, msgs);
			msgs = basicSetEqualityHelper(newEqualityHelper, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateSpacePackage.STATE_SPACE__EQUALITY_HELPER, newEqualityHelper, newEqualityHelper));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StateSpacePackage.STATE_SPACE__STATES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getStates()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StateSpacePackage.STATE_SPACE__STATES:
				return ((InternalEList<?>)getStates()).basicRemove(otherEnd, msgs);
			case StateSpacePackage.STATE_SPACE__EQUALITY_HELPER:
				return basicSetEqualityHelper(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StateSpacePackage.STATE_SPACE__RULES:
				return getRules();
			case StateSpacePackage.STATE_SPACE__STATES:
				return getStates();
			case StateSpacePackage.STATE_SPACE__INITIAL_STATES:
				return getInitialStates();
			case StateSpacePackage.STATE_SPACE__OPEN_STATES:
				return getOpenStates();
			case StateSpacePackage.STATE_SPACE__TRANSITION_COUNT:
				return getTransitionCount();
			case StateSpacePackage.STATE_SPACE__EQUALITY_HELPER:
				return getEqualityHelper();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StateSpacePackage.STATE_SPACE__RULES:
				getRules().clear();
				getRules().addAll((Collection<? extends Rule>)newValue);
				return;
			case StateSpacePackage.STATE_SPACE__STATES:
				getStates().clear();
				getStates().addAll((Collection<? extends State>)newValue);
				return;
			case StateSpacePackage.STATE_SPACE__INITIAL_STATES:
				getInitialStates().clear();
				getInitialStates().addAll((Collection<? extends State>)newValue);
				return;
			case StateSpacePackage.STATE_SPACE__OPEN_STATES:
				getOpenStates().clear();
				getOpenStates().addAll((Collection<? extends State>)newValue);
				return;
			case StateSpacePackage.STATE_SPACE__TRANSITION_COUNT:
				setTransitionCount((Integer)newValue);
				return;
			case StateSpacePackage.STATE_SPACE__EQUALITY_HELPER:
				setEqualityHelper((StateEqualityHelper)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case StateSpacePackage.STATE_SPACE__RULES:
				getRules().clear();
				return;
			case StateSpacePackage.STATE_SPACE__STATES:
				getStates().clear();
				return;
			case StateSpacePackage.STATE_SPACE__INITIAL_STATES:
				getInitialStates().clear();
				return;
			case StateSpacePackage.STATE_SPACE__OPEN_STATES:
				getOpenStates().clear();
				return;
			case StateSpacePackage.STATE_SPACE__TRANSITION_COUNT:
				setTransitionCount(TRANSITION_COUNT_EDEFAULT);
				return;
			case StateSpacePackage.STATE_SPACE__EQUALITY_HELPER:
				setEqualityHelper((StateEqualityHelper)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case StateSpacePackage.STATE_SPACE__RULES:
				return rules != null && !rules.isEmpty();
			case StateSpacePackage.STATE_SPACE__STATES:
				return states != null && !states.isEmpty();
			case StateSpacePackage.STATE_SPACE__INITIAL_STATES:
				return initialStates != null && !initialStates.isEmpty();
			case StateSpacePackage.STATE_SPACE__OPEN_STATES:
				return openStates != null && !openStates.isEmpty();
			case StateSpacePackage.STATE_SPACE__TRANSITION_COUNT:
				return transitionCount != TRANSITION_COUNT_EDEFAULT;
			case StateSpacePackage.STATE_SPACE__EQUALITY_HELPER:
				return equalityHelper != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (transitionCount: ");
		result.append(transitionCount);
		result.append(')');
		return result.toString();
	}

} //StateSpaceImpl