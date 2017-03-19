package CS310Hubbert;

public class Property {
	
	private String mlsNum;
	private String licenseNum;
	private Realtor realtor;
	private String streetAddr;
	private String city;
	private char state;
	private int zipCode;
	private int bedrooms;
	private Double bathrooms;
	public Boolean sold;
	private Double askingPrice;
	
	public Property() {
		this.init();
	}
	
	public Property(String mlsNum, String licenseNum, Realtor realtor, String streetAddr, String city, char state,
			int zipCode, int bedrooms, Double bathrooms, Boolean sold, Double askingPrice) {
		super();
		this.mlsNum = mlsNum;
		this.licenseNum = licenseNum;
		this.realtor = realtor;
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


	private void init(){
		System.out.println("We are making a motherfuck property");
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
	public Realtor getRealtor() {
		return realtor;
	}
	public void setRealtor(Realtor realtor) {
		this.realtor = realtor;
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
	public char getState() {
		return state;
	}
	public void setState(char state) {
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
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	
}
