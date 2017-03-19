package CS310Hubbert;

public class Realtor {
	private String licenseNum;
	private String firstName;
	private String lastName;
	private String phoneNum;
	private Double commission;

	public Realtor() {
		this.init();
	}

	public Realtor(String licenseNum, String firstName, String lastName, String phoneNum, Double commission) {
		super();
		this.setLicenseNum(licenseNum);
		this.firstName = firstName;
		this.lastName = lastName;
		this.setPhoneNum(phoneNum);
		this.commission = commission;
		this.init();
	}

	private void init() {

	}

	public String getLicenseNum() {
		return licenseNum;
	}

	public void setLicenseNum(String licenseNum) {

		int licenseLength = licenseNum.length();

		// Check if we format llnnnnnnn l=letter n=number
		if (licenseLength != 9) {
			throw new ExceptionInInitializerError("License Number is incorrect!");
		} else if (!(Character.isLetter(licenseNum.charAt(0))) && !(Character.isLetter(licenseNum.charAt(1)))) {
			throw new ExceptionInInitializerError("License Number must start with two letters!");
		} else if (!(Character.isDigit(licenseNum.charAt(2))) && !(Character.isDigit(licenseNum.charAt(4)))
				&& !(Character.isDigit(licenseNum.charAt(5))) && !(Character.isDigit(licenseNum.charAt(6)))
				&& !(Character.isDigit(licenseNum.charAt(7))) && !(Character.isDigit(licenseNum.charAt(8)))) {
			throw new ExceptionInInitializerError("License Number must start with two letters!");
		}

		this.licenseNum = licenseNum;
	}

	public void setPhoneNum(String phoneNum) {
		// Check the phone number is 12 chars long and contains dashes and
		// digits in the correct places
		
		int licenseLength = phoneNum.length();

		// Check if we format llnnnnnnn l=letter n=number
		if (licenseLength != 9) {
			throw new ExceptionInInitializerError("License Number is incorrect!");
		} else if (!(Character.isLetter(licenseNum.charAt(0))) && !(Character.isLetter(licenseNum.charAt(1)))) {
			throw new ExceptionInInitializerError("License Number must start with two letters!");
		} else if (!(Character.isDigit(licenseNum.charAt(2))) && !(Character.isDigit(licenseNum.charAt(4)))
				&& !(Character.isDigit(licenseNum.charAt(5))) && !(Character.isDigit(licenseNum.charAt(6)))
				&& !(Character.isDigit(licenseNum.charAt(7))) && !(Character.isDigit(licenseNum.charAt(8)))) {
			throw new ExceptionInInitializerError("License Number must start with two letters!");
		}
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
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
