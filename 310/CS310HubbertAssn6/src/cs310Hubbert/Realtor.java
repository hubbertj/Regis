package cs310Hubbert;

/**
 *
 * @author Jerum Hubbert
 */
public class Realtor {
	private String licenseNum;
	private String firstName;
	private String lastName;
	private String phoneNum;
	private Double commission;

	/**
	 *  A blank constructor for the Realtor object.
	 */
	public Realtor() {
		super();
	}

	/**
	 * Main constructor for the Realtor object.
	 * 
	 * 
	 * 
	 * @param licenseNum String the license number for the Realtor
	 * @param firstName String the first name of the Realtor
	 * @param lastName String the last name of the Realtor
	 * @param phoneNum String office phone of the Realtor
	 * @param commission Double the amount of money the Realtor makes on sales
	 */
	public Realtor(String licenseNum, String firstName, String lastName, String phoneNum, Double commission) {
		super();
		this.setLicenseNum(licenseNum);
		this.firstName = firstName;
		this.lastName = lastName;
		this.setPhoneNum(phoneNum);
		this.commission = commission;
	}

	/**
	 * @param realtorArr String[] Array which hold all needed values to create a Realtor
	 */
	public Realtor(String[] realtorArr) {
		this.setRealtorAttributes(realtorArr);
	}

	/**
	 * @param arr String[] Array which hold all needed values to create a Realtor
	 */
	private void setRealtorAttributes(String[] arr) {
		this.setLicenseNum(arr[0]);
		this.setFirstName(arr[1]);
		this.setLastName(arr[2]);
		this.setPhoneNum(arr[3]);
		this.setCommission(Double.parseDouble(arr[4]));
	}

	/**
	 * This method check to see if the Realtor license number meets
	 * requirements.
	 * 
	 * 
	 * @param licenseNum String the license number for the Realtor
	 * @return Boolean true if license number is valid
	 */
	public Boolean isLicenseNumValid(String licenseNum) {
		int licenseLength = licenseNum.length();

		// Check if we format llnnnnnnn l=letter n=number
		if (licenseLength != 9) {
			return false;
		} else if (!(Character.isLetter(licenseNum.charAt(0))) && !(Character.isLetter(licenseNum.charAt(1)))) {
			return false;
		} else if (!(Character.isDigit(licenseNum.charAt(2))) && !(Character.isDigit(licenseNum.charAt(4)))
				&& !(Character.isDigit(licenseNum.charAt(5))) && !(Character.isDigit(licenseNum.charAt(6)))
				&& !(Character.isDigit(licenseNum.charAt(7))) && !(Character.isDigit(licenseNum.charAt(8)))) {
			return false;
		}
		return true;

	}

	/**
	 * This method check to see if the Realtor phone number meets
	 * requirements.
	 * 
	 * 
	 * @param phoneNum String office phone of the Realtor
	 * @return Boolean true if phone number is valid
	 */
	public Boolean isPhoneNumValid(String phoneNum) {
		String regexStr = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";
		// regex check for phone number
		if (phoneNum.matches(regexStr)) {
			return true;
		}
		return false;
	}

	/**
	 * @return String the license number for the Realtor
	 */
	public String getLicenseNum() {
		return licenseNum;
	}

	/**
	 * Sets the license number
	 * 
	 * @param licenseNum String the license number for the Realtor
	 */
	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}

	/**
	 * Sets the phone number
	 * 
	 * @param phoneNum String office phone of the Realtor
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * 
	 * 
	 * @return String first name of Realtor
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name
	 * 
	 * @param firstName String first name of Realtor
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return String last name of Realtor
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name
	 * 
	 * @param lastName String last name of Realtor
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 
	 * 
	 * @return String phone numer of Realtor
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * @return Double The amount a Realtor makes on a sale
	 */
	public Double getCommission() {
		return commission;
	}

	/**
	 * Sets the commission for the Realtor
	 * 
	 * @param commission Double The amount a Realtor makes on a sale
	 */
	public void setCommission(Double commission) {
		this.commission = commission;
	}
	
	/**
	 * 
	 * @param realtor
	 * @return
	 */
	public int compareTo(Realtor realtor){
		return this.licenseNum.compareTo(realtor.getLicenseNum());
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Realtor real = (Realtor) obj;
		if (real == null || !real.getLicenseNum().equals(this.licenseNum)) {
			return false;
		} else if (!real.getFirstName().equals(this.firstName)) {
			return false;
		} else if (!real.getLastName().equals(this.lastName)) {
			return false;
		} else if (!real.getPhoneNum().equals(this.phoneNum)) {
			return false;
		} else if (!real.getCommission().equals(this.commission)) {
			return false;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return licenseNum + ", " + firstName + ", " + lastName + ", " + phoneNum + ", " + commission.toString();
	}
}
