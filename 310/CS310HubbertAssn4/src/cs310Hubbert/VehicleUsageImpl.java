package cs310Hubbert;

import java.util.HashMap;

/**
 * @author Jay
 *
 */
public class VehicleUsageImpl {

	HashMap<Realtor, Car> vehicleAssigmment = new HashMap<Realtor, Car>();

	/**
	 * 
	 */
	public VehicleUsageImpl() {
		super();
	}

	/**
	 * @param realtor
	 * @param car
	 * @return
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
	 * @param realtor
	 * @return
	 */
	public Car getRealtorCurrentCar(Realtor realtor) {
		if (this.vehicleAssigmment.get(realtor) != null) {
			return this.vehicleAssigmment.get(realtor);
		}
		return null;
	}
}
