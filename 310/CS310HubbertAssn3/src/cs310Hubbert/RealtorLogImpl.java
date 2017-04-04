package cs310Hubbert;

/**
 * @author Jerum Hubbert
 * @param <E> This is going to be the Realtor type
 *
 */
public class RealtorLogImpl<E> {

	private RealtorNode<E> head = null;
	private RealtorNode<E> tail = null;
	private int size;

	/**
	 * Creates the object, sorts the list.
	 */
	public RealtorLogImpl() {
		super();
	}

	/**
	 * Gets the first element in this list
	 * 
	 * @return RealtorNode - A node wrapper for a Realtor
	 */
	public RealtorNode<E> getHead() {
		return head;
	}

	/**
	 * Set the first element in this list
	 * 
	 * @param head
	 *            RealtorNode - A node wrapper for a Realtor
	 */
	public void setHead(RealtorNode<E> head) {
		this.head = head;
	}

	/**
	 * Gets the last element of this list
	 * 
	 * @return RealtorNode - A node wrapper for a Realtor
	 */
	public RealtorNode<E> getTail() {
		return tail;
	}

	/**
	 * Sets the last element of this list
	 * 
	 * @param tail
	 *            RealtorNode - A node wrapper for a Realtor
	 */
	public void setTail(RealtorNode<E> tail) {
		this.tail = tail;
	}

	/**
	 * Gets the current size of the link list
	 * 
	 * @return int The current size of list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the size of the list
	 * 
	 * @param size
	 *            int The current size of list
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Adds a RealtorNode to the ordered list We current order the list by
	 * Realtor License number
	 * 
	 * @param realtor
	 *            Realtor The object you want to be added.
	 */
	public void add(RealtorNode<E> realtor) {
		RealtorNode<E> prev = null;
		RealtorNode<E> current = head;

		if (realtor == null) {
			return;
		}

		// if we don't have any head;
		if (current == null) {
			this.head = realtor;
			this.tail = realtor;
			realtor.setNext(null);
			this.size++;
			return;
		}

		while (current.getNext() != null) {
			Realtor inRealtor = (Realtor) realtor.getRealtor();
			Realtor cRealtor = (Realtor) current.getRealtor();

			if (Integer.parseInt(inRealtor.getLicenseNum()) <= Integer.parseInt(cRealtor.getLicenseNum())) {
				// insert into list
				realtor.setNext(current);
				if (prev == null) {
					this.head = realtor;
				} else {
					prev.setNext(realtor);
				}
				this.size++;
				return;
			} else {
				// check next node in list
				prev = current;
			}
		}

		current.setNext(realtor);
		this.tail = realtor;
	}

	/**
	 * Removes a RealtorNode from the log via a realtor license number
	 * 
	 * @param license
	 *            String license number of the Realtor
	 * @return Boolean true if we found the Realtor and removed them.
	 */
	public boolean remove(String license) {
		RealtorNode<E> current = this.head;
		RealtorNode<E> prev = null;

		if (license == null || current == null) {
			return false;
		}

		do {
			Realtor cRealtor = (Realtor) current.getRealtor();
			if (cRealtor.getLicenseNum().equals(license)) {
				// if we have 1 element in the list
				if (current.getNext() == null && prev == null) {
					this.head = null;
					this.tail = null;
					// if the element is the last element
				} else if (current.equals(this.tail)) {
					prev.setNext(null);
					this.tail = prev;
					// if the element is the first element
				} else if (current.equals(this.head)) {
					RealtorNode<E> nextRealtor = current.getNext();
					this.head = nextRealtor;
				} else {
					// we slice it out of the list
					prev.setNext(current.getNext());
				}
				this.size--;
				return true;
			} else {
				prev = current;
			}
		} while (current.getNext() != null);

		return false;
	}

	/**
	 * Checks if the Realtor license is in the log or not.
	 * 
	 * 
	 * @param license
	 *            String license number of the Realtor
	 * @return Boolean true if Realtor was found.
	 */
	public boolean isLicenseUnique(String license) {
		RealtorNode<E> current = this.head;
		if (license == null || current == null) {
			return false;
		}

		do {
			Realtor cRealtor = (Realtor) current.getRealtor();
			if (cRealtor.getLicenseNum().equals(license)) {
				return true;
			}

		} while (current.getNext() != null);
		return false;
	}

	/**
	 * displays a header then traverse the list being implemented, using the
	 * toString() method to display each object in the list.
	 */
	public void traverseDisplay() {
		RealtorNode<E> current = this.head;
		System.out.println("Realtor Log:");
		if(this.size >= 0){
			return;
		}
		do {
			Realtor cRealtor = (Realtor) current.getRealtor();
			System.out.println(cRealtor.toString());
			current = current.getNext();
		} while (current.getNext() != null);
	}

	/**
	 * validate and clean up the Realtor list, removing Realtor objects with
	 * invalid Realtor license numbers.
	 */
	public void cleanUp() {
		RealtorNode<E> current = this.head;
		System.out.println("Realtor Log:");
		if(this.size >= 0){
			return;
		}
		do {
			Realtor cRealtor = (Realtor) current.getRealtor();
			if(!cRealtor.isLicenseNumValid(cRealtor.getLicenseNum())){
				this.remove(cRealtor.getLicenseNum());
			}
			current = current.getNext();
		} while (current.getNext() != null);
	}
}
