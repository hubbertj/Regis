package cs310Hubbert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RealtorLogImpl {

	// ordered list
	private ArrayList<Realtor> realtorLog;

	public RealtorLogImpl() {
		super();
		this.realtorLog = new ArrayList<Realtor>();
		this.realtorLog.sort(this.getComparator());
	}

	public void add(Realtor realtor) {
		if (realtor == null) {
			return;
		}
		this.realtorLog.add(realtor);
		this.realtorLog.sort(this.getComparator());
	}
	
	private Comparator<Realtor> getComparator(){
		return new Comparator<Realtor>() {

			@Override
			public int compare(Realtor realtor1, Realtor realtor2) {
				return realtor1.getLicenseNum().compareTo(realtor2.getLicenseNum());
			}
			
		};
	}

	public boolean remove(String license) {
		return true;
	}

	public boolean isLicenseUnique(String license) {
		Realtor real = new Realtor();
		real.setLicenseNum(license);
		this.realtorLog.sort(this.getComparator());
		if(Collections.binarySearch(this.realtorLog, real, getComparator()) > -1){
			return false;
		}
		return true;
	}

	public ArrayList<Realtor> getRealtorLog() {
		return realtorLog;
	}
}
