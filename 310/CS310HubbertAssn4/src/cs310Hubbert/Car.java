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
	 * @param id
	 * @param type
	 */
	public Car(int id, String alias, CAR_TYPES type){
		this.id = id;
		this.type = type;
		this.alias = alias;
	}
	
	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public CAR_TYPES getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(CAR_TYPES type) {
		this.type = type;
	}

	/**
	 * @return
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public boolean equals(Object obj) {
		return (this.id == ((Car) obj).getId());
	}

	@Override
	public String toString() {
		return "id " + this.id + " alias " + this.alias + " type " + this.type.toString(); 
	}
	
}
