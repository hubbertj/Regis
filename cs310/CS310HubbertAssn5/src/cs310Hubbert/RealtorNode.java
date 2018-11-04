package cs310Hubbert;

/**
 * @author Jay
 *
 * @param <E> This will be the Realtor type more then likely
 */
public class RealtorNode<E> {
	
	private RealtorNode<E> next; 
	private E realtor;
	
	/**
	 * Blank constructor for the RealtorNode class
	 */
	/**
	 * @param dataItem The type we are working with.
	 */
	public RealtorNode(E dataItem){
		super();
		realtor = dataItem;
		next = null;
	}

	/**
	 * Gets the next link
	 * 
	 * @return A RealtorNode which wraps a Realtor
	 */
	public RealtorNode<E> getNext() {
		return next;
	}

	/**
	 * Sets the next link 
	 * 
	 * @param next - A RealtorNode which is next in the Link
	 */
	public void setNext(RealtorNode<E> next) {
		this.next = next;
	}

	/**
	 * Gets the Realtor which is wrapped in this class
	 * 
	 * @return Realtor A representation of a Realtor Agent
	 */
	public E getRealtor() {
		return realtor;
	}

	/**
	 * Sets the Realtor for this Node
	 * 
	 * @param realtor Realtor A representation of a Realtor Agent
	 */
	public void setRealtor(E realtor) {
		this.realtor = realtor;
	}
}
