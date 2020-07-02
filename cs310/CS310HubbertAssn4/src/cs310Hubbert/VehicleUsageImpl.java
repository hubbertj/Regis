package cs310Hubbert;

import java.util.HashMap;

/**
 * @author Jay
 *
 */
public class VehicleUsageImpl {

	HashMap<Realtor, Car> vehicleAssigmment = new HashMap<Realtor, Car>();

	/**
	 *  Blank constructor
	 */
	public VehicleUsageImpl() {
		super();
	}
	
	/**
	 * @return HashMap The object which hold all the data
	 */
	public HashMap<Realtor, Car> getVehicleAssigmment() {
		return vehicleAssigmment;
	}

	/**
	 * Add data to our list
	 * 
	 * @param realtor Realtor A person who sells houses
	 * @param car Car A thing for transportation
	 * @return Boolean True if we added the realtor and car to the list
	 */
	public Boolean add(Realtor realtor, Car car) {
		try {
			this.vehicleAssigmment.put(realtor, car);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * Removes a Realtor from the checked out list.
	 * 
	 * @param realtor
	 *            A agent of the office
	 * @return Car The car which the Realtor had checked out or null if nothing
	 *         was checked out
	 */
	public Car remove(Realtor realtor) {
		return this.vehicleAssigmment.remove(realtor);
	}

	/**
	 * Using the realtor you can get the car he or she has checked out
	 * 
	 * @param realtor Realtor A person who sells houses
	 * @return Car The car which the Realtor had checked out or null if nothing
	 *         was checked out
	 */
	public Car getRealtorCurrentCar(Realtor realtor) {
		if (this.vehicleAssigmment.get(realtor) != null) {
			return this.vehicleAssigmment.get(realtor);
		}
		return null;
	}
}
