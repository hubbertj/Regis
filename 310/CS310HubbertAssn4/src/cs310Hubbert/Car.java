package cs310Hubbert;

/**
 * @author Jay
 *
 */
public class Car {
	
	public static enum CAR_TYPES {LUXURY, BASIC};
	private int id;
	private CAR_TYPES type;
	private String alias;
	
	/**
	 * Constructs the car object
	 * @param id The id you want the car to have
	 * @param alias The nickname for the car
	 * @param type Enum which defines what the car is
	 */
	public Car(int id, String alias, CAR_TYPES type){
		this.id = id;
		this.type = type;
		this.alias = alias;
	}
	
	/**
	 * gets the id of the car
	 * @return Int the id of the car
	 */
	public int getId() {
		return id;
	}

	/**
	 * sets the id of the car
	 * @param id Int the id of the car
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * gets the type of the car
	 * @return CAR_TYPES The type of the car
	 */
	public CAR_TYPES getType() {
		return type;
	}

	/**
	 * sets the CAR_TYPE of the car
	 * @param type CAR_TYPE a enum of a type
	 */
	public void setType(CAR_TYPES type) {
		this.type = type;
	}

	/**
	 * gets the nick name of the car
	 * @return String the nick name
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * sets the nick name of the car
	 * @param alias String the nick name
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return (this.id == ((Car) obj).getId());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "id " + this.id + " alias " + this.alias + " type " + this.type.toString(); 
	}
	
}
