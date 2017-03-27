package cs310Hubbert;

/**
 *
 * @author Jerum Hubbert
 */
public class Property {

	private String mlsNum;
	private String licenseNum;
	private String streetAddr;
	private String city;
	private String state;
	private int zipCode;
	private int bedrooms;
	private Double bathrooms;
	public Boolean sold;
	private Double askingPrice;

	public Property() {
		this.init();
	}

	/**
	 * This constructor requires all fields to be passed as parameters.
	 * 
	 * @param mlsNum
	 *            - this is an String attribute.
	 * 
	 * @param licenseNum
	 *            - this is a String attribute.
	 * 
	 * 
	 * @param streetAddr
	 *            - this is an String attribute.
	 * 
	 * @param city
	 *            - this is a String attribute.
	 * 
	 * @param state
	 *            - this is a String attribute.
	 * 
	 * @param zipCode
	 *            - this is an integer attribute.
	 * 
	 * @param bedrooms
	 *            - this is a integer attribute.
	 * 
	 * @param bathrooms
	 *            - this is a Double attribute.
	 * 
	 * @param sold
	 *            - this is an Boolean attribute.
	 * 
	 * @param askingPrice
	 *            - this is a Double attribute.
	 */

	public Property(String mlsNum, String licenseNum, String streetAddr, String city, String state, int zipCode,
			int bedrooms, Double bathrooms, Boolean sold, Double askingPrice) {
		super();
		this.mlsNum = mlsNum;
		this.licenseNum = licenseNum;
		this.streetAddr = streetAddr;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.sold = sold;
		this.askingPrice = askingPrice;
		this.init();
	}

	public Property(String[] propertyArr) {
		this.setPropertyAttributes(propertyArr);
		this.init();
	}

	private void init() {
	}

	public Boolean isMlsValid(String mlsNum) {
		// check if mls is 7 digits;
		if (mlsNum.length() != 7) {
			return false;
		}
		return true;
	}

	private void setPropertyAttributes(String[] arr) {
		this.setMlsNum(arr[0]);
		this.setLicenseNum(arr[1]);
		this.setStreetAddr(arr[2]);
		this.setCity(arr[3]);
		this.setState(arr[4]);
		this.setZipCode(Integer.parseInt(arr[5]));
		this.setBedrooms(Integer.parseInt(arr[6]));
		this.setBathrooms(Double.parseDouble(arr[7]));
		this.setSold(arr[8].equalsIgnoreCase("y"));
		this.setAskingPrice(Double.parseDouble(arr[9]));
	}

	public Boolean isStateValid(String state) {
		// only accept "CO” or “WY”
		if (!state.equals("CO") && !state.equals("WY")) {
			return false;
		}
		return true;
	}

	public Boolean isZipcodeValid(int zipCode) {
		String zipStr = new Integer(zipCode).toString();

		// check if zip code is 5 digits long
		if (zipStr.length() != 5) {
			return false;
		}

		String subZip = zipStr.substring(0, 2);

		// Check the zip code is a Colorado zip code
		if (!subZip.equals("80") && !subZip.equals("81") && !subZip.equals("82") && !subZip.equals("83")) {
			return false;
		}
		return true;
	}

	public String getMlsNum() {
		return mlsNum;
	}

	public void setMlsNum(String mlsNum) {
		this.mlsNum = mlsNum;
	}

	public String getLicenseNum() {
		return licenseNum;
	}

	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}

	public String getStreetAddr() {
		return streetAddr;
	}

	public void setStreetAddr(String streetAddr) {
		this.streetAddr = streetAddr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		state = state.toUpperCase();
		this.state = state;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	public Double getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(Double bathrooms) {
		this.bathrooms = bathrooms;
	}

	public Boolean getSold() {
		return sold;
	}

	public void setSold(Boolean sold) {
		this.sold = sold;
	}

	public Double getAskingPrice() {
		return askingPrice;
	}

	public void setAskingPrice(Double askingPrice) {
		this.askingPrice = askingPrice;
	}

	@Override
	public boolean equals(Object obj) {
		Property prop = (Property) obj;
		if (!prop.getMlsNum().equals(this.mlsNum)) {
			return false;
		} else if (!prop.getLicenseNum().equals(this.licenseNum)) {
			return false;
		} else if (!prop.getStreetAddr().equals(this.streetAddr)) {
			return false;
		} else if (!prop.getCity().equals(this.city)) {
			return false;
		} else if (!prop.getState().equals(this.state)) {
			return false;
		} else if (!(prop.getZipCode() == this.zipCode)) {
			return false;
		} else if (!(prop.getBedrooms() == this.bedrooms)) {
			return false;
		} else if (!prop.getBathrooms().equals(this.bathrooms)) {
			return false;
		} else if (!prop.getSold().equals(this.sold)) {
			return false;
		} else if (!prop.getAskingPrice().equals(this.askingPrice)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return mlsNum + ", " + licenseNum + ", " + streetAddr + ", " + city + ", " + state + ", "
				+ Integer.toString(zipCode) + ", " + Integer.toString(bedrooms) + ", " + Double.toString(bathrooms)
				+ ", " + Boolean.toString(sold) + ", " + Double.toString(askingPrice);
	}

}
