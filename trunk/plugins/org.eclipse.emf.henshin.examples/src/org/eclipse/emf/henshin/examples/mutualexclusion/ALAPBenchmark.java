package org.eclipse.emf.henshin.examples.mutualexclusion;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.InterpreterFactory;
import org.eclipse.emf.henshin.interpreter.RuleApplication;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.TransformationSystem;
import org.eclipse.emf.henshin.model.TransformationUnit;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;

public class ALAPBenchmark {
	
	/**
	 * Relative path to the example files.
	 */
	public static final String PATH = "src/org/eclipse/emf/henshin/examples/mutualexclusion";

	final static int GRAPH_SIZE_MIN = 1000;
	
	final static int GRAPH_SIZE_MAX = 10000;
	
	final static int STEP = 1000;
	
	final static int ITERATIONS = 3;

	public static void run(String path) {

		System.out.println("***************** Alap Transformation Sequence***************");
		System.out.println("Memory allocated:" +Runtime.getRuntime().maxMemory()/1024/1024 + "Mb");
		System.out.println("GRAPH_SIZE_MIN:" + GRAPH_SIZE_MIN);
		System.out.println("GRAPH_SIZE_MAX:" + GRAPH_SIZE_MAX);
		System.out.println("STEP:" + STEP);
		System.out.println("ITERATIONS:" + ITERATIONS + " , first iteration is not included into evaluation");
		System.out.println("*************************************************************");

		// Create a resource set with a base directory:
		HenshinResourceSet resourceSet = new HenshinResourceSet(path);

		// Load the transformation system:
		TransformationSystem trasys = resourceSet
				.getTransformationSystem("mutualexclusion.henshin");

		//Load initial model
		EObject container = resourceSet.getObject("initialgraph.xmi");
		
		// Load the rules:
		Rule newRule = trasys.findRuleByName("newRule");
		TransformationUnit alap2Unit = trasys.findUnitByName("alap2");
//		LoggingApplicationMonitorImpl monitor = new LoggingApplicationMonitorImpl();

		
		//Perform benchmark for several graph sizes
		for(int graphSize = GRAPH_SIZE_MIN; graphSize <=GRAPH_SIZE_MAX; graphSize +=STEP){

			long sum = 0;
			for (int j = 0; j < ITERATIONS; j++) {
	
				EObject container2 = EcoreUtil.copy(container);
				
				// Initialize the Henshin interpreter:
				EGraph graph = InterpreterFactory.INSTANCE.createEGraph();
				graph.addTree(container2);
				Engine engine = InterpreterFactory.INSTANCE.createEngine();
	
				UnitApplication unitAppl = InterpreterFactory.INSTANCE.createUnitApplication(engine);
				unitAppl.setEGraph(graph);
				RuleApplication ruleAppl = InterpreterFactory.INSTANCE.createRuleApplication(engine);
				ruleAppl.setEGraph(graph);
				// get Starting Time
				long startTime = System.currentTimeMillis();
				
				// create initial graph
				ruleAppl.setRule(newRule);

				for (int i = 0; i < graphSize - 2; i++) {
					ruleAppl.execute(null);
				}
		
				//execute sequences of rules
				unitAppl.setUnit(alap2Unit);
			    unitAppl.execute(null);
				
				//get finish time
				long finishTime = System.currentTimeMillis();
				
				if(j!=0) //don't include first ITERATION; emf  classes are loaded
					sum = sum + finishTime - startTime;
	
				//persist the resulting graph
				//BenchmarkHelper.persistGraph(BENCHMARK_CASE, resourceSet, container2);
			}
	
			System.out.println("Graph size: "+graphSize +"  average time:" + (sum/(ITERATIONS-1)));
		} //FOR several STEPS
	}

	public static void main(String[] args) {
		run(PATH);
	}

}