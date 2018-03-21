package com.cs473;

public class AgentProgram {
	public AgentProgram() {
	    Percept p
	  }

	public Action execute(Percept percept) {
	    Location location = percept.getLocation();

	    if (location.isDirty()) {
	      return VacuumEnvironment.getAction("Vacuum");

	    } else if (location.getName().equals("A")) {

	      return VacuumEnvironment.getAction("Right"));
	    } else if (location.getName().equals("B")) {

	      return VacuumEnvironment.getAction("Left"));

	    } else {
	      return VacuumEnvironment.getAction("NoOp");
	    }
	}

	public void runSumlator() {
		VacuumEnvironment env = new VacuumEnvironment();

		while (isAnotherStep()) {
			for (Agent agent : env.agents()) {
				Percept percept = getPerceptsSeenBy(agent);
				Action action = agent.execute(percept);

				execute(agent, action);

				updatePerformanceMeasure(agent, action, percept);
			}
		}

	}
}