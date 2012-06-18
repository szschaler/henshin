package org.eclipse.emf.henshin.interpreter.impl;

import org.eclipse.emf.henshin.interpreter.ApplicationMonitor;
import org.eclipse.emf.henshin.interpreter.Assignment;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.InterpreterFactory;
import org.eclipse.emf.henshin.interpreter.Match;
import org.eclipse.emf.henshin.interpreter.RuleApplication;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.TransformationUnit;

/**
 * Default interpreter factory.
 * 
 * @author Christian Krause
 */
public class InterpreterFactoryImpl implements InterpreterFactory {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.InterpreterFactory#createEGraph()
	 */
	@Override
	public EGraph createEGraph() {
		return new EGraphImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.InterpreterFactory#createAssignment(org.eclipse.emf.henshin.model.TransformationUnit)
	 */
	@Override
	public Assignment createAssignment(TransformationUnit unit) {
		return new AssignmentImpl(unit);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.InterpreterFactory#createMatch(org.eclipse.emf.henshin.model.Rule)
	 */
	@Override
	public Match createMatch(Rule rule) {
		return new MatchImpl(rule);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.InterpreterFactory#createResultMatch(org.eclipse.emf.henshin.model.Rule)
	 */
	@Override
	public Match createResultMatch(Rule rule) {
		return new ResultMatchImpl(rule);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.InterpreterFactory#createEngine()
	 */
	@Override
	public Engine createEngine() {
		return new EngineImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.InterpreterFactory#createUnitApplication(org.eclipse.emf.henshin.interpreter.Engine)
	 */
	@Override
	public UnitApplication createUnitApplication(Engine engine) {
		return new UnitApplicationImpl(engine);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.InterpreterFactory#createRuleApplication(org.eclipse.emf.henshin.interpreter.Engine)
	 */
	@Override
	public RuleApplication createRuleApplication(Engine engine) {
		return new RuleApplicationImpl(engine);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.InterpreterFactory#createApplicationMonitor()
	 */
	@Override
	public ApplicationMonitor createApplicationMonitor() {
		return new ApplicationMonitorImpl();
	}

}