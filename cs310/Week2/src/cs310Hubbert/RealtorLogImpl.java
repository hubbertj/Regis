package cs310Hubbert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Jerum Hubbert
 *
 */
public class RealtorLogImpl {

	// ordered list
	private ArrayList<Realtor> realtorLog;

	/**
	 *  Creates the object, sorts the list.
	 */
	public RealtorLogImpl() {
		super();
		this.realtorLog = new ArrayList<Realtor>();
		this.realtorLog.sort(this.getComparator());
	}

	/** Adds a Realtor to the list
	 * 
	 * @param realtor Realtor The object you want to be added.
	 */
	public void add(Realtor realtor) {
		if (realtor == null) {
			return;
		}
		this.realtorLog.add(realtor);
		this.realtorLog.sort(this.getComparator());
	}
	
	/**
	 *  Used internally to get a comparator to sort the list
	 *  this ensures we are sort the same way every time
	 * 
	 * @return Comparator A comparator used to sort the list
	 */
	private Comparator<Realtor> getComparator(){
		return new Comparator<Realtor>() {

			@Override
			public int compare(Realtor realtor1, Realtor realtor2) {
				return realtor1.getLicenseNum().compareTo(realtor2.getLicenseNum());
			}
			
		};
	}

	/**
	 * Removes a Realtor from the log via license number
	 * 
	 * @param license String license number of the Realtor
	 * @return Boolean true if we found the Realtor and removed them.
	 */
	public boolean remove(String license) {
		Realtor real = new Realtor();
		real.setLicenseNum(license);
		int index = Collections.binarySearch(this.realtorLog, real, getComparator());
		if(index != -1){
			this.realtorLog.remove(index);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Checks if the realtor license is in the log or not.
	 * 
	 * 
	 * @param license String license number of the Realtor
	 * @return Boolean true if realtor was found.
	 */
	public boolean isLicenseUnique(String license) {
		Realtor real = new Realtor();
		real.setLicenseNum(license);
		this.realtorLog.sort(this.getComparator());
		if(Collections.binarySearch(this.realtorLog, real, getComparator()) > -1){
			return false;
		}
		return true;
	}

	/**
	 * @return ArrayList a collection of Realtors
	 */
	public ArrayList<Realtor> getRealtorLog() {
		return realtorLog;
	}
}
