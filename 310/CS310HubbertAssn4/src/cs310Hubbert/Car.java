package cs310Hubbert;

/**
 * @author Jay
 *
 */
public class Car {
	
	private int id;
	private String type;
	
	/**
	 * @param id
	 * @param type
	 */
	public Car(int id, String type){
		this.id = id;
		this.type = type;
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
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}	
}
