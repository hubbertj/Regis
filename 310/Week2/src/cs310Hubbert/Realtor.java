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

	public Realtor() {
		this.init();
	}

	/**
	 * This constructor requires all fields to be passed as parameters.
	 * 
	 * @param licenseNum
	 *            - this is an String attribute.
	 * 
	 * @param firstName
	 *            - this is a String attribute.
	 * 
	 * @param lastName
	 *            - this is a String attribute.
	 * 
	 * @param phoneNum
	 *            - this is an String attribute.
	 * 
	 * @param commission
	 *            - this is a Double attribute.
	 */

	public Realtor(String licenseNum, String firstName, String lastName, String phoneNum, Double commission) {
		super();
		this.setLicenseNum(licenseNum);
		this.firstName = firstName;
		this.lastName = lastName;
		this.setPhoneNum(phoneNum);
		this.commission = commission;
		this.init();
	}

	public Realtor(String[] realtorArr) {
		this.setRealtorAttributes(realtorArr);
		this.init();
	}

	private void init() {

	}

	public void setRealtorAttributes(String[] arr) {
		this.setLicenseNum(arr[0]);
		this.setFirstName(arr[1]);
		this.setLastName(arr[2]);
		this.setPhoneNum(arr[3]);
		this.setCommission(Double.parseDouble(arr[4]));
	}

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

	public Boolean isPhoneNumValid(String phoneNum) {
		String regexStr = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";
		// regex check for phone number
		if (phoneNum.matches(regexStr)) {
			return true;
		}
		return false;
	}

	public String getLicenseNum() {
		return licenseNum;
	}

	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	@Override
	public boolean equals(Object obj) {
		Realtor real = (Realtor) obj;
		if (!real.getLicenseNum().equals(this.licenseNum)) {
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

	@Override
	public String toString() {
		return licenseNum + ", " + firstName + ", " + lastName + ", " + phoneNum + ", " + commission.toString();
	}
}
