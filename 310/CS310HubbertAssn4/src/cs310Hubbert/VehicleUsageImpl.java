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
	public VehicleUsageImpl(){
		super();
	}
	
	/**
	 * @param realtor
	 * @param car
	 * @return
	 */
	public Boolean add(Realtor realtor, Car car){
		try{
			this.vehicleAssigmment.put(realtor, car);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @param realtor
	 * @return
	 */
	public Boolean remove(Realtor realtor){
		if(this.vehicleAssigmment.remove(realtor) != null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @param realtor
	 * @return
	 */
	public Car getRealtorCurrentCar(Realtor realtor){
		if(this.vehicleAssigmment.get(realtor) != null){
			return this.vehicleAssigmment.get(realtor);
		}
		return null;
	}
}
