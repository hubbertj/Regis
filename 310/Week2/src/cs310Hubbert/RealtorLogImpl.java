package cs310Hubbert;

import java.util.ArrayList;

public class RealtorLogImpl {

	// ordered list
	private ArrayList<Realtor> realtorLog;

	public RealtorLogImpl() {
		super();
		this.realtorLog = new ArrayList<Realtor>();
	}

	public void add(Realtor realtor) {
		int index = 0;
		if (realtor == null || realtor.equals(null)) {
			return;
		}
		for (Realtor real : this.realtorLog) {
			if(new Integer(real.getLicenseNum()) > new Integer(realtor.getLicenseNum()) ){
				this.realtorLog.add(index, realtor);
				break;
			}
			index ++;
		}
	}

	public boolean remove(String license) {
		return true;
	}

	public boolean isLicenseUnique(String license) {
		return true;
	}
}
