package cs310Hubbert;

import java.util.HashSet;

/**
 * @author Jerum Hubbert
 *
 */
public class RealtorLogImpl {

	private HashSet<Realtor> realtorSet = new HashSet<Realtor>();
	private Realtor[] hashTable;
	private final int START_CAPACITY = 23;
	private int size;

	/**
	 * Main Constructor addes realtors to the set and to the hashtable.
	 * 
	 */
	public RealtorLogImpl() {
		this.hashTable = new Realtor[this.START_CAPACITY];
	}
	
	/** Gets the hashset of all the realtors
	 * 
	 * @return HashSet The hash set of all the realtors
	 */
	public HashSet<Realtor> getRealtorSet() {
		return realtorSet;
	}
	
	/** Gets the capacity of the hash table.
	 * 
	 * @return int Max capacity of the hash table.
	 */
	public int getSTART_CAPACITY() {
		return START_CAPACITY;
	}

	/**
	 * Gets the current size of the hash table
	 * 
	 * @return int The current size of hash table
	 */
	public int getSize() {
		return size;
	}
	
	private int insert(int hashCode, Realtor realtor) throws Exception {
		int index = hashCode;

		if (index < 0 || index > this.hashTable.length) {
			index += this.hashTable.length;
			hashCode = this.hashTable.length;
		}

		while (index < this.hashTable.length) {
			if (this.hashTable[index] == null) {
				this.hashTable[index] = realtor;
				return index;
			}
			index++;

			if (index == hashCode) {
				throw new Exception("Hash table is full");
			}
			// wrap back around the array
			if (index >= this.hashTable.length) {
				index = 0;
			}
		}

		// never reached but just in case
		return index;
	}
	
	/**Calculates the hash code using the realtor license number
	 * 
	 * @param licenseNum String the license number of the realtor
	 * @return int The hash code for the license number
	 */
	public int getHash(String licenseNum) {
		char[] chars = null;
		int totalCount = 0;
		int hash = 0;
		if (licenseNum == null) {
			return 0;
		}
		chars = licenseNum.toCharArray();

		for (char character : chars) {
			int asciiNum = (int) character;
			totalCount += asciiNum;
		}

		if (totalCount != 0) {
			hash = totalCount % this.START_CAPACITY;
		}
		return hash;
	}
	
	/**
	 * Calculates the hash code using a Realtor object
	 * 
	 * @param realtor Realtor The realtor you want to get a hash for
	 * @return int The hash code for the Realtor object
	 */
	public int getHash(Realtor realtor) {
		char[] chars = null;
		int totalCount = 0;
		int hash = 0;
		if (realtor.equals(null) || realtor == null || realtor.getLicenseNum() == null) {
			return 0;
		}
		chars = realtor.getLicenseNum().toCharArray();

		for (char character : chars) {
			int asciiNum = (int) character;
			totalCount += asciiNum;
		}

		if (totalCount != 0) {
			hash = totalCount % this.START_CAPACITY;
		}
		return hash;
	}
	
	/**
	 * Places a Realtor into the hash table, if we failed to insert return -1
	 * 
	 * @param realtor Realtor The realtor you insert into the table
	 * @return int the index where the Realtor was placed
	 */
	public int add(Realtor realtor) {
		if(!this.realtorSet.add(realtor)){
			return -1;
		}
		int hashCode = this.getHash(realtor);
		int index = 0;
		try {
			index = this.insert(hashCode, realtor);
			this.size++;
			return index;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * Trys to find the Realtor in the hash table using the Realtor license Number
	 * if not found returns null
	 * 
	 * @param licenseNum String the license number of the realtor
	 * @return Realtor The found Realtor
	 */
	public Realtor find(String licenseNum) {
		int hashCode = this.getHash(licenseNum);
		int index = hashCode;

		if (index < 0 || index > this.hashTable.length) {
			index += this.hashTable.length;
			hashCode = this.hashTable.length;
		}

		while (index < this.hashTable.length) {
			if (this.hashTable[index] == null) {
				return null;
			}
			Realtor realtor = this.hashTable[index];

			if (realtor.getLicenseNum().equals(licenseNum)) {
				return realtor;
			}

			index++;
			if (index == hashCode) {
				return null;
			}

			// wrap back around the array
			if (index >= this.hashTable.length) {
				index = 0;
			}
		}
		return null;
	}
	
	/**
	 *  Displays a message of all the Realtors in the table and at which index
	 */
	public void displayHash() {
		int index = 0;
		System.out.println("Realtor Set:");
		for (Realtor realtor : this.hashTable) {
			if (realtor == null) {
				System.out.println("\tIndex " + index + " is empty");
			} else {
				System.out.println("\tIndex " + index + " contains Realtor " + realtor.getLicenseNum() + ", "
						+ realtor.getFirstName() + " " + realtor.getLastName());
			}
			index++;
		}
	}
}
