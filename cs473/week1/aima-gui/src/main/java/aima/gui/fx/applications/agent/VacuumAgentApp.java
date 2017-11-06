
/** Jerum Hubbert
 *  CS 473 
 *  Topic 2
 *  
 *  The Participation assignment in this Topic listed a number of simplifications made in the design of the Vacuum World Simulator example found in the Reinforcement Activities associated with this Topic. In this Assignment, you must extend the design and implementation of this Vacuum World Simulator to remove one of the following simplifications by:

	Extending the design and implementation so that it handles multiple locations, such as vacuuming the rooms in a house
	Extending the design and implementation so that the rules are not hard-coded in the agent program.
	Extending the design and implementation so there is another agent that makes messes (Dirt?) that must be cleaned up.
	In each case, you must also design the performance measures that should be used to assess whether the agent is behaving rationally.
	
	
	I have decide to take on the task of
	Extending the design and implementation so that it handles multiple locations, such as vacuuming the rooms in a house
 * 
 */
package aima.gui.fx.applications.agent;

import aima.core.agent.Action;
import aima.core.agent.impl.AbstractAgent;
import aima.core.environment.vacuum.*;
import aima.core.search.agent.NondeterministicSearchAgent;
import aima.core.search.nondeterministic.NondeterministicProblem;
import aima.core.util.Tasks;
import aima.gui.fx.framework.IntegrableApplication;
import aima.gui.fx.framework.Parameter;
import aima.gui.fx.framework.TaskExecutionPaneBuilder;
import aima.gui.fx.framework.TaskExecutionPaneCtrl;
import aima.gui.fx.views.SimpleEnvironmentViewCtrl;
import aima.gui.fx.views.VacuumEnvironmentViewCtrl;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.awt.Color;
import java.awt.Label;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;

public class VacuumAgentApp extends IntegrableApplication {

    public static void main(String[] args) {
        launch(args);
    }

    public final static String PARAM_ENV = "environment";
    public final static String PARAM_AGENT = "agent";

    private TaskExecutionPaneCtrl taskPaneCtrl;
    private SimpleEnvironmentViewCtrl envViewCtrl;
    protected VacuumEnvironment env = null;
    protected AbstractAgent agent = null;

    @Override
    public String getTitle() {
        return "Vacuum Agent App";
    }

    /**
     * Defines state view, parameters, and call-back functions and calls the
     * simulation pane builder to create layout and controller objects.
     */
    @Override
    public Pane createRootPane() {
        BorderPane root = new BorderPane();

        StackPane envView = new StackPane();
        StackPane stack = new StackPane();
        stack.getChildren().add(new Rectangle(), new Label("Go!"));
//        stack.getChildren().add(new Rectangle(), new Label("Go!"));
        
        envViewCtrl = new VacuumEnvironmentViewCtrl(envView);
//
        List<Parameter> params = createParameters();
//
        TaskExecutionPaneBuilder builder = new TaskExecutionPaneBuilder();
        builder.defineParameters(params);
        builder.defineStateView(envView);
        builder.defineInitMethod(this::initialize);
        builder.defineTaskMethod(this::startExperiment);
        taskPaneCtrl = builder.getResultFor(root);

        return root;
    }

    protected List<Parameter> createParameters() {
        Parameter p1 = new Parameter(PARAM_ENV, "A/B Deterministic Environment", "A/B Non-Deterministic Environment", "House Deterministic Environment", "House Non-Deterministic Environment");
        Parameter p2 = new Parameter(PARAM_AGENT, "TableDrivenVacuumAgent", "ReflexVacuumAgent",
                "SimpleReflexVacuumAgent", "ModelBasedReflexVacuumAgent", "NondeterministicVacuumAgent");
        return Arrays.asList(p1, p2);
    }

    /**
     * Is called after each parameter selection change.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void initialize() {
        switch (taskPaneCtrl.getParamValueIndex(PARAM_ENV)) {
            case 0:
                env = new VacuumEnvironment();
                break;
            case 1:
                env = new NondeterministicVacuumEnvironment();
                break;
            case 2:
                env = new NondeterministicHouseVacuumEnvironment();
                break;
            case 3:
                env = new NondeterministicHouseVacuumEnvironment();
                break;
        }
        agent = null;
        switch (taskPaneCtrl.getParamValueIndex(PARAM_AGENT)) {
            case 0:
                agent = new TableDrivenVacuumAgent();
                break;
            case 1:
                agent = new ReflexVacuumAgent();
                break;
            case 2:
                agent = new SimpleReflexVacuumAgent();
                break;
            case 3:
                agent = new ModelBasedReflexVacuumAgent();
                break;
            case 4:
                agent = new NondeterministicSearchAgent<>(percept -> (VacuumEnvironmentState) percept, env);
                break;
        }
        if (env != null && agent != null) {
            envViewCtrl.initialize(env);
            env.addEnvironmentView(envViewCtrl);
            env.addAgent(agent);
        }
    }

    /**
     * Starts the experiment.
     */
    @SuppressWarnings("unchecked")
    public void startExperiment() {
        if (agent instanceof NondeterministicSearchAgent<?, ?>) {
            NondeterministicProblem<VacuumEnvironmentState, Action> problem =
                    new NondeterministicProblem<>((VacuumEnvironmentState) env.getCurrentState(),
                            VacuumWorldFunctions::getActions, VacuumWorldFunctions.createResultsFunction(agent),
                            VacuumWorldFunctions::testGoal, (s, a, sPrimed) -> 1.0);
            // Set the problem now for this kind of agent
            ((NondeterministicSearchAgent<VacuumEnvironmentState, Action>) agent).makePlan(problem);
        }
        while (!env.isDone() && !Tasks.currIsCancelled()) {
            env.step();
            taskPaneCtrl.setStatus("Performance=" + env.getPerformanceMeasure(agent));
            taskPaneCtrl.waitAfterStep();
        }
        envViewCtrl.notify("Performance=" + env.getPerformanceMeasure(agent));
    }

    @Override
    public void cleanup() {
        taskPaneCtrl.cancelExecution();
    }
}
