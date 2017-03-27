package cs310Hubbert;

public class PropertyLogImpl {

	// unordered list
	private Property[] propertyArray;

	private int numProperties = 0;

	private final int PROPERTY_SIZE = 1000;

	public PropertyLogImpl() {
		super();
		this.propertyArray = new Property[PROPERTY_SIZE];
	}

	public Property[] getPropertyArray() {
		return propertyArray;
	}

	public int getNumProperties() {
		return numProperties;
	}

	public boolean add(Property property) {
		try {
			
			this.propertyArray[this.numProperties] = property;
			this.numProperties++;
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean remove(String license) {
		boolean didWeDelete = false;
		for (int i = 0; i < this.numProperties; i++) {
			if (this.propertyArray[i].getLicenseNum().equals(license)) {
				this.propertyArray[i] = this.propertyArray[this.numProperties - 1];
				this.numProperties--;
				didWeDelete = true;
			}
		}
		return didWeDelete;
	}

	public boolean remove(int mlsNum) {
		boolean didWeDelete = false;
		for (int i = 0; i < this.numProperties; i++) {
			if (new Integer(propertyArray[i].getMlsNum()) == mlsNum) {
				this.propertyArray[i] = this.propertyArray[this.numProperties - 1];
				this.numProperties--;
				didWeDelete = true;
			}
		}
		return didWeDelete;
	}

	public boolean isMlsUnique(int mlsNum) {
		boolean recordFound = true;
		for (int i = 0; i < this.numProperties; i++) {
			if (Integer.parseInt(propertyArray[i].getMlsNum()) == mlsNum) {
				recordFound = false;
				break;
			}
		}
		return recordFound;
	}

	public int numberOfProperties(String license) {
		int count = 0;
		for (int i = 0; i < this.numProperties; i++) {
			if (propertyArray[i].getLicenseNum().equals(license)) {
				count++;
			}
		}
		return count;
	}

	public double totalPropertyValue() {
		int askingPriceSum = 0;
		for (int i = 0; i < this.numProperties; i++) {
			if (propertyArray[i].getAskingPrice() != null && propertyArray[i].getAskingPrice() != 0) {
				askingPriceSum += propertyArray[i].getAskingPrice();
			}
		}
		return askingPriceSum;
	}

	public double totalPropertyValue(String license) {
		int askingPriceSum = 0;
		for (int i = 0; i < this.numProperties; i++) {
			if (propertyArray[i].getAskingPrice() != null && propertyArray[i].getAskingPrice() != 0
					&& propertyArray[i].getLicenseNum().equals(license)) {
				askingPriceSum += propertyArray[i].getAskingPrice();
			}
		}
		return askingPriceSum;
	}

}
