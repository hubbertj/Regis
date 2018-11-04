package cs310Hubbert;

import java.util.HashSet;

/**
 * @author Jerum Hubbert
 *
 */
public class RealtorLogImpl {

	private HashSet<Realtor> realtorSet = new HashSet<Realtor>();
	private BinaryTree<Realtor> binaryTree;
	private int size;

	/**
	 * Main Constructor.
	 * 
	 */
	public RealtorLogImpl() {
		this.binaryTree = new BinaryTree<Realtor>();
	}

	/**
	 * Gets the hashset of all the realtors
	 * 
	 * @return HashSet The hash set of all the realtors
	 */
	public HashSet<Realtor> getRealtorSet() {
		return realtorSet;
	}

	/**
	 * Gets the current size of the hash table
	 * 
	 * @return int The current size of hash table
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Places a Realtor into the log
	 * 
	 * @param realtor
	 *            Realtor The realtor you insert into the log
	 * @return Realtor the Realtor which was placed in the log, null if Realtor
	 *         wasn't placed in the log.
	 */
	public Realtor add(Realtor realtor) {
		if (!this.realtorSet.add(realtor)) {
			return null;
		}
		if (this.binaryTree.add(realtor)) {
			this.size ++;
			return realtor;
		} else {
			return null;
		}
	}

	/**
	 * Trys to find the Realtor in the log using the Realtor license Number.
	 * 
	 * @param licenseNum
	 *            String the license number of the Realtor
	 * @return Realtor The found Realtor, null if not found.
	 */
	public Realtor find(String licenseNum) {
		if (licenseNum == null) {
			return null;
		}

		Realtor realtor = new Realtor();
		realtor.setLicenseNum(licenseNum);
		return this.binaryTree.find(realtor);
	}

	/**
	 * Displays all the data in the tree using preOrder Traverse
	 */
	public void traverseDisplay() {
		System.out.println("Realtor List:");
		System.out.println(this.binaryTree.toString());
	}
}
