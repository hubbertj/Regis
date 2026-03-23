package com.cs473;

public class Location {
	private String name = "";

	private ArrayList<EnvironmentObj> contains = new ArrayList<>(); // Objs in this location

	public Location(Sting name) {
		this.name = name;

		if (r.nextInt(2) == 0) // Randomly decide if this location is dirty
			contains.add(new Dirty());
	}

	public String getName() {
		return name;
	}

	public ArrayList<EnvironmentObj> getContains() {
		return contains;
	}

	public void add(EnvironmentObj obj) {
		contains.add(obj);
	}

	public void remove(EnvironmentObj obj) {
		contains.remove(obj);
	}

	public boolean contains(EnviornmentObj other) {
		for (EnvironmentObj obj : contains)
			if (obj == other)
				return true;

		return false;
	}

	public void clean() {
		for (EnvironmentObj obj : contains)
			if (obj instanceof Dirty)
				contains.remove(obj);
	}

	public boolean isDirty() {
		for (EnvironmentObj obj : contains)
			if (obj instanceof Dirty)
				return true;

		return false;
	}
}