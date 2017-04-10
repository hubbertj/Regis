package cs310Hubbert;

import java.util.EmptyStackException;

/**
 * @author Jay
 *
 */
public class CarStackImpl{
	
	private int topOfStack = -1;
	private final int CAR_NUMBER = 4;
	private Car[] cars;

	/**
	 * 
	 */
	public CarStackImpl() {
		this.cars = new Car[this.CAR_NUMBER];
	}

	
	/**
	 * @return
	 * @throws EmptyStackException
	 */
	public Car pop() throws EmptyStackException {
		if(this.isEmpty()){
			throw new EmptyStackException();
		}
		return this.cars[this.topOfStack --];
	}

	/**
	 * @param car
	 * @return
	 */
	public Car push(Car car) {
		if(this.topOfStack == this.cars.length -1){
			return null; //stack is full
		}
		this.topOfStack ++;
		this.cars[topOfStack] = car;
		return car;
	}

	/**
	 * Checks if the stack is empty or not
	 * 
	 * @return Boolean True if the stack is empty
	 */
	public Boolean isEmpty() {
		return (this.topOfStack == -1);
	}
	
	/**
	 * @return
	 */
	public Car peek(){
		if(this.isEmpty()){
			return null;
		}
		return this.cars[this.topOfStack];
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
