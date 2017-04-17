package cs310Hubbert;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author Jerum Hubbert
 *
 */
public class PropertyLogImpl {

	private LinkedList<Property> proreptyLinkList;

	/**
	 * Constructor which instantiate a private Link list
	 */
	public PropertyLogImpl() {
		super();
		this.proreptyLinkList = new LinkedList<Property>();
	}

	/**
	 * Gets the full list of properties
	 *
	 * @return LinkedList A list of properties
	 */
	public LinkedList<Property> getProreptyLinkList() {
		return proreptyLinkList;
	}

	/**
	 * Sets the full list of properties
	 * 
	 * @param proreptyLinkList
	 *            LinkedList A list of properties
	 */
	public void setProreptyLinkList(LinkedList<Property> proreptyLinkList) {
		this.proreptyLinkList = proreptyLinkList;
	}

	/**
	 * Get the current number of properties in out list
	 * 
	 * @return int the number of properties in the list
	 */
	public int getNumProperties() {
		return this.proreptyLinkList.size();
	}

	/**
	 * Adds a property to our log
	 * 
	 * @param property
	 *            the object we want to add.
	 * @return Boolean true if the property was added.
	 */
	public boolean add(Property property) {
		try {
			this.proreptyLinkList.add(property);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * Removes all properties which belong to license number.
	 * 
	 * @param license
	 *            String a unique number for a Realtor.
	 * @return Boolean true if we removed a property from out List.
	 */
	public boolean remove(String license) {
		Boolean hasRemoved = false;
		ListIterator<Property> pIterator = this.proreptyLinkList.listIterator();
		while (pIterator.hasNext()) {
			if (pIterator.next().getLicenseNum().equals(license)) {
				pIterator.remove();
				hasRemoved = true;
			}
		}
		return hasRemoved;
	}

	/**
	 * Searches the list for properties with MLS numbers which match, if found
	 * they are removed.
	 * 
	 * @param mlsNum
	 *            int a number which is unique to a property
	 * @return Boolean true if we found a property which matched and removed it.
	 */
	public boolean remove(int mlsNum) {
		boolean hasRemoved = false;
		ListIterator<Property> pIterator = this.proreptyLinkList.listIterator();
		while (pIterator.hasNext()) {
			if (Integer.parseInt(pIterator.next().getMlsNum()) == mlsNum) {
				pIterator.remove();
				hasRemoved = true;
			}
		}
		return hasRemoved;
	}

	/**
	 * Determines if a MLS number is the list or not.
	 * 
	 * @param mlsNum
	 *            int a number which is unique to a property
	 * @return Boolean true if did not find the MLS number in our list.
	 */
	public boolean isMlsUnique(int mlsNum) {
		boolean recordFound = false;
		ListIterator<Property> pIterator = this.proreptyLinkList.listIterator();
		while (pIterator.hasNext()) {
			if (Integer.parseInt(pIterator.next().getMlsNum()) == mlsNum) {
				recordFound = true;
			}
		}
		return !recordFound;
	}

	/**
	 * Used to determine the number of properties a Realtor has by license
	 * number
	 * 
	 * @param license
	 *            String a unique number for a Realtor.
	 * @return int the number of properties for Realtor license
	 */
	public int numberOfProperties(String license) {
		int count = 0;
		ListIterator<Property> pIterator = this.proreptyLinkList.listIterator();
		while (pIterator.hasNext()) {
			if (pIterator.next().getLicenseNum().equals(license)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns the sum of all properties in the list
	 * 
	 * 
	 * @return Double The total value for all properties in the List
	 */
	public double totalPropertyValue() {
		int askingPriceSum = 0;
		for (int i = 0; i < this.proreptyLinkList.size(); i++) {
			Property property = this.proreptyLinkList.get(i);
			if (property.getAskingPrice() != null && property.getAskingPrice() != 0) {
				askingPriceSum += property.getAskingPrice();
			}
		}
		return askingPriceSum;
	}

	/**
	 * Returns the sum of all properties for a single realtor.
	 * 
	 * @param license
	 *            String a unique number for a realtor.
	 * @return Double The total value for all properties for a realtor
	 */
	public double totalPropertyValue(String license) {
		int askingPriceSum = 0;
		for (int i = 0; i < this.proreptyLinkList.size(); i++) {
			Property property = this.proreptyLinkList.get(i);
			if (property.getAskingPrice() != null && property.getAskingPrice() != 0
					&& property.getLicenseNum().equals(license)) {
				askingPriceSum += property.getAskingPrice();
			}
		}
		return askingPriceSum;
	}

	/**
	 * displays a header then traverse the list being implemented, using the
	 * toString() method to display each object in the list.
	 */
	public void traverseDisplay() {
		System.out.println("Property Log:");
		ListIterator<Property> pIterator = this.proreptyLinkList.listIterator();
		while (pIterator.hasNext()) {
			System.out.println(pIterator.next().toString());
		}
		System.out.println("\n");
	}

	/**
	 * validate and clean up the property list, removing Property objects with
	 * invalid MLS numbers
	 */
	public void cleanUp() {
		ListIterator<Property> pIterator = this.proreptyLinkList.listIterator();
		while (pIterator.hasNext()) {
			Property property = pIterator.next();
			if(!property.isMlsValid(property.getMlsNum())){
				pIterator.remove();
			}
		}
	}

}
