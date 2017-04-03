package cs310Hubbert;

import java.util.LinkedList;

/**
 * @author Jerum Hubbert
 *
 */
public class PropertyLogImpl {

	private Property[] propertyArray; // unordered list
	private LinkedList<Property> proreptyLinkList;
	private int numProperties = 0;
	private final int PROPERTY_SIZE = 1000;

	/**
	 *  Constructor which instantiate a private array
	 */
	public PropertyLogImpl() {
		super();
		this.propertyArray = new Property[PROPERTY_SIZE];
	}

	/** Gets the full array we are working with
	 * 
	 * @return Property[] a array of properties
	 */
	public Property[] getPropertyArray() {
		return propertyArray;
	}

	/** Get the current number of properties in out list
	 * 
	 * @return int the number of properties in the array / log
	 */
	public int getNumProperties() {
		return numProperties;
	}

	/**
	 * Adds a property to our log
	 * 
	 * @param property the object we want to add.
	 * @return Boolean true if the property was added.
	 */
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

	/**
	 * Removes all properties which belong to license number.
	 * 
	 * @param license String a unique number for a realtor.
	 * @return Boolean true if we removed a property from out array.
	 */
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

	/**
	 * Searches the array for properties with MLS numbers
	 * which match, if found they are removed.
	 * 
	 * @param mlsNum int a number which is unique to a property 
	 * @return Boolean true if we found a property which matched and removed it.
	 */
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

	/**
	 * Determines if a MLS number is the array or not.
	 * 
	 * @param mlsNum int a number which is unique to a property 
	 * @return Boolean true if did not find the MLS number in our array.
	 */
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

	/**
	 * Used to determine the numnber of properties a Realtor has
	 * by license number
	 * 
	 * @param license String a unique number for a realtor.
	 * @return int the number of properties for Realtor license
	 */
	public int numberOfProperties(String license) {
		int count = 0;
		for (int i = 0; i < this.numProperties; i++) {
			if (propertyArray[i].getLicenseNum().equals(license)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns the sum of all properties in the array
	 * 
	 * 
	 * @return Double The total value for all properties in the array
	 */
	public double totalPropertyValue() {
		int askingPriceSum = 0;
		for (int i = 0; i < this.numProperties; i++) {
			if (propertyArray[i].getAskingPrice() != null && propertyArray[i].getAskingPrice() != 0) {
				askingPriceSum += propertyArray[i].getAskingPrice();
			}
		}
		return askingPriceSum;
	}

	/**
	 * Returns the sum of all properties for a single realtor.
	 * 
	 * @param license String a unique number for a realtor.
	 * @return Double The total value for all properties for a realtor
	 */
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
