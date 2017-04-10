package cs310Hubbert;

/**
 * @author Jay
 *
 */
public class CarStackImpl {

	private final int CAR_NUMBER = 4;
	@SuppressWarnings("unused")
	private Car[] cars;

	/**
	 * 
	 */
	public CarStackImpl() {
		this.cars = new Car[this.CAR_NUMBER];
	}

	/**
	 * @return
	 */
	@SuppressWarnings("null")
	public Car pop() {
		return new Car((Integer) null, "");
	}

	/**
	 * @param car
	 * @return
	 */
	public Boolean push(Car car) {
		return true;
	}

	/**
	 * Checks if the stack is empty or not
	 * 
	 * @return Boolean True if the stack is empty
	 */
	public Boolean isEmpty() {
		Boolean isEmpty = true;
		for (Integer i = 0; i < this.cars.length; i++) {
			if (cars[i] != null) {
				isEmpty = false;
			}
		}
		return isEmpty;
	}
	
	/**
	 * Checks if the stack is full or not
	 * @return Boolean True if the stack is full
	 */
	public Boolean isFull(){ 
		Boolean isFull = true;
		for (Integer i = 0; i < this.cars.length; i++) {
			if (cars[i] == null) {
				isFull = false;
			}
		}
		return isFull;
	}
}
