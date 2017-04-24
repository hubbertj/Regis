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

	/**
	 * Blank constructor for property
	 */
	public Property() {
		super();
	}

	/**
	 * constructor a property using raw values
	 * 
	 * @param mlsNum
	 *            String a unique number for a property
	 * @param licenseNum
	 *            String a unique number for the realtor associated with the
	 *            property
	 * @param streetAddr
	 *            String the street number for the property
	 * @param city
	 *            String the city name the property is located in
	 * @param state
	 *            String two letter designator for the state
	 * @param zipCode
	 *            int zipcode the property is located in
	 * @param bedrooms
	 *            int number of bedrooms in the property
	 * @param bathrooms
	 *            Double number of bathrooms in the property
	 * @param sold
	 *            Boolean has the property been sold or not
	 * @param askingPrice
	 *            Double the selling price of the property
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
	}

	/**
	 * Constructor for setting value for a property using a array
	 * 
	 * @param propertyArr
	 *            String[] a array of all values used to create a property
	 */
	public Property(String[] propertyArr) {
		this.setPropertyAttributes(propertyArr);
	}

	/**
	 * Check to see if the MLS number is valid or not.
	 * 
	 * @param mlsNum
	 *            String a unique number for a property
	 * @return Boolean true if the MLS number meets all requirements.
	 */
	public Boolean isMlsValid(String mlsNum) {
		// check if mls is 7 digits;
		if (mlsNum.length() != 7) {
			return false;
		}
		return true;
	}

	/**
	 * Helper method for creating a property
	 * 
	 * @param arr
	 *            String[] a array of all values used to create a property
	 */
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

	/**
	 * checks to see if the state meets requirements.
	 * 
	 * 
	 * @param state
	 *            String two letter designator for the state
	 * @return Boolean true if the state code is valid or not
	 */
	public Boolean isStateValid(String state) {
		// only accept "CO” or “WY”
		if (!state.equals("CO") && !state.equals("WY")) {
			return false;
		}
		return true;
	}

	/**
	 * checks to see if the zipcode meets requirements.
	 * 
	 * @param zipCode
	 *            int zipcode the property is located in
	 * @return Boolean true if the zipcode code is valid or not
	 */
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

	/**
	 * get the properties MLS number
	 * 
	 * @return String MLS Number of property
	 */
	public String getMlsNum() {
		return mlsNum;
	}

	/**
	 * set the properties MLS number
	 * 
	 * @param mlsNum
	 *            String a unique number for a property
	 */
	public void setMlsNum(String mlsNum) {
		this.mlsNum = mlsNum;
	}

	/**
	 * get the properties license number attached to this property
	 * 
	 * 
	 * @return String a unique number for the realtor associated with the
	 *         property
	 */
	public String getLicenseNum() {
		return licenseNum;
	}

	/**
	 * sets license number for property
	 * 
	 * @param licenseNum
	 *            String a unique number for the realtor associated with the
	 *            property
	 */
	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}

	/**
	 * gets the street address for property
	 * 
	 * 
	 * @return String the street number for the property
	 */
	public String getStreetAddr() {
		return streetAddr;
	}

	/**
	 * sets the street address for property
	 * 
	 * @param streetAddr
	 *            String the street number for the property
	 */
	public void setStreetAddr(String streetAddr) {
		this.streetAddr = streetAddr;
	}

	/**
	 * gets the city name for property
	 * 
	 * 
	 * @return String the city name the property is located in
	 */
	public String getCity() {
		return city;
	}

	/**
	 * sets the city name for property
	 * 
	 * 
	 * @param city
	 *            String the city name the property is located in
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * gets the state the property is in
	 * 
	 * 
	 * @return String two letter designator for the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * sets the state the property is in
	 * 
	 * @param state
	 *            String two letter designator for the state
	 */
	public void setState(String state) {
		state = state.toUpperCase();
		this.state = state;
	}

	/**
	 * gets the zipcode the property is in
	 * 
	 * @return int zipcode the property is located in
	 */
	public int getZipCode() {
		return zipCode;
	}

	/**
	 * sets the zipcode the property is in
	 * 
	 * @param zipCode
	 *            int zipcode the property is located in
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * gets number of bedrooms
	 * 
	 * @return int number of bedrooms in the property
	 */
	public int getBedrooms() {
		return bedrooms;
	}

	/**
	 * sets number of bedrooms
	 * 
	 * @param bedrooms
	 *            int number of bedrooms in the property
	 */
	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	/**
	 * gets the number of bath rooms for the property
	 * 
	 * @return Double number of bathrooms in the property
	 */
	public Double getBathrooms() {
		return bathrooms;
	}

	/**
	 * sets the number of bath rooms for the property
	 * 
	 * @param bathrooms
	 *            Double number of bathrooms in the property
	 */
	public void setBathrooms(Double bathrooms) {
		this.bathrooms = bathrooms;
	}

	/**
	 * Gets if the house is sold
	 * 
	 * @return Boolean has the property been sold or not
	 */
	public Boolean getSold() {
		return sold;
	}

	/**
	 * Sets the if the house is sold
	 * 
	 * @param sold
	 *            Boolean has the property been sold or not
	 */
	public void setSold(Boolean sold) {
		this.sold = sold;
	}

	/**
	 * Gets the asking price
	 * 
	 * @return Double the selling price of the property
	 */
	public Double getAskingPrice() {
		return askingPrice;
	}

	/**
	 * Sets the asking price
	 * 
	 * @param askingPrice
	 *            Double the selling price of the property
	 */
	public void setAskingPrice(Double askingPrice) {
		this.askingPrice = askingPrice;
	}

	/**
	 * 
	 * The result is a positive integer if this String object lexicographically
	 * follows the argument string. The result is zero if the strings are equal;
	 * compareTo returns 0 exactly when the equals(Object) method would return
	 * true.
	 * 
	 * @param property
	 *            A property object
	 * @return int A representation of equality 
	 */
	public int compareTo(Property property) {
		return this.mlsNum.compareTo(property.getMlsNum());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Property prop = (Property) obj;
		if (prop == null || !prop.getMlsNum().equals(this.mlsNum)) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return mlsNum + ", " + licenseNum + ", " + streetAddr + ", " + city + ", " + state + ", "
				+ Integer.toString(zipCode) + ", " + Integer.toString(bedrooms) + ", " + Double.toString(bathrooms)
				+ ", " + Boolean.toString(sold) + ", " + Double.toString(askingPrice);
	}

}
