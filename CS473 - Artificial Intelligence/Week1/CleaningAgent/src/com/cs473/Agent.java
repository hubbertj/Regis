package com.cs473;

public class Agent extends VacuumEnvironment {
	protected AgentProgram program;

	public AbstractAgent() {
	  }

	public Agent(AgentProgram aProgram) {
		program = aProgram;
	}

	public Action execute(Percept p) {
		if (null != program) {
			return program.execute(p);
		}
		return VacuumEnvironment.getAction("NoOp");
	}
}