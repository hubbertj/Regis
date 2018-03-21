package com.cs473;
import com.cs473.Location;


public class VacuumEnvironment {

	private HashMap<String Location>locations=new HashMap<>();
	private HashMap<String, Action> actions = new HashMap<>();

	private ArrayList<Agent> agents = new ArrayList<>();
	private Random rnd = new Random();

	public static Action findAction(String name) {
		return actions.get(name);
	}

	public static Collection<Action> getActions() {
		actions.values();
	}

	public VacuumEnvironment() {
	    locations.put("A", new Location("A"));
	    locations.put("B", new Location("B"));

	    Agent agt = new Agent();
	    agents.add(agt);
	    r.nextInt(2) ? locA.add(agt) : locB.add(agt);

	    actions.put("NoOp", new Action("NoOp");
	    actions.put("Left", new Action("Left");
	    actions.put("Right", new Action("Right");
	    actions.put("Vacuum", new Action("Vacuum");
	  }

	public ArrayList<Percept> getPerceptsSeenBy(Agent agt) {
		ArrayList<Percept> percepts = new ArrayList<>();

		percepts.add(new Percept(agentLocation(agt))); // Fully observable

		return percepts;
	}

	public void execute(Agent agt, Action act) {
	    String action = act.getName();

	    if (!action.equals("NoOp")) {
	      Location location = agentLocation(agt);

	      if (action.equals("Vacuum") {
	        location.clean();

	      else if (action.equals("Left") || action.equals("Right")) {
	        location.remove(agt);

	        if (location.getName().equals("A")
	          locations.get("B").add(agt);
	        else
	          locations.get("A").add(agt);
	      }
	    }
	  }

	public ArrayList<EnvironmentObj> contains() { // Derived asscociation
		ArrayList<EnvironmentObj> objs = new ArrayList<>();

		for (Location location : locations.values())
			objs.addAll(location.getContains());

		return objs;
	}

	public ArrayList<Agent> agents() {
		return agents;
	}

	private Location agentLocation(Agent agt) {    // Utility returning the Agent's current location
	    for (Location location : locations.values()) 
	      if (location.contains(agt)
	        return location;

	    return null;
	  }
}