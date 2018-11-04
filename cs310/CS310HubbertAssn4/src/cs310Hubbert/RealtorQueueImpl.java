package cs310Hubbert;

/**
 * @author Jay
 *
 */
public class RealtorQueueImpl {

	private RealtorNode<Realtor> front;
	private RealtorNode<Realtor> back;
	private int size;
	
	/**
	 *  Standard constructor
	 */
	public RealtorQueueImpl(){
		super();
		this.front = null;
		this.back = null;
		this.size = 0;
	}

	/**
	 * Returns the first element in the link list
	 * this object should be the first to get processed
	 * 
	 * @return RealtorNode A wrapper for a realtor object
	 */
	public RealtorNode<Realtor> getFront() {
		return front;
	}


	/**
	 * Returns the newest element in the link list
	 * this object should be the last to get processed
	 * 
	 * @return RealtorNode A wrapper for a realtor object
	 */
	public RealtorNode<Realtor> getBack() {
		return back;
	}
	
	/**
	 * The size of the whole list
	 * 
	 * @return int the current size of the link list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * pushes a realtor into the queue
	 * 
	 * @param realtor Realtor A person who sells houses
	 * @return Realtor A person who sells houses
	 */
	public Realtor add(Realtor realtor){
		if(this.front == null){
			this.back = new RealtorNode<Realtor>(realtor);
			this.front = this.back;
			this.size ++;
			return realtor;
		}
		RealtorNode<Realtor> realtorNode = new RealtorNode<Realtor>(realtor);
		this.back.setNext(realtorNode);
		this.back = realtorNode;
		realtorNode.setNext(null);
		this.size ++;
		return realtor;
	}
	
	/**
	 * 
	 * Looks at the next object which should be processed
	 * @return Realtor A person who sells house
	 */
	public Realtor peek(){
		if(this.size == 0){
			return null;
		}
		return this.front.getRealtor();
	}
	
	/**
	 * Grabs the next object which should be processed
	 * removes it from the queue
	 * 
	 * @return Realtor A person who sells house
	 */
	public Realtor remove(){
		Realtor realtor = this.peek();
		if(realtor == null){
			return realtor;
		}
		this.front = this.front.getNext();
		this.size --;
		return realtor;
	}
	
	/**
	 * Checks if the Queue is empty or not
	 * 
	 * @return Boolean True if the Queue is empty
	 */
	public Boolean isEmpty() {
		return (this.size <= 0);
	}
	
	/**
	 * Checks if the Queue is full or not
	 * @return Boolean True if the Queue is full
	 */
	public Boolean isFull(){ 
		try{
			if(this.back == null){
				return false;
			}
			RealtorNode<Realtor> tmp = this.back;
			this.add(new Realtor());
			this.back = tmp;
			return false;
		}catch(Exception ex){
			ex.printStackTrace();
			return true;
		}
	}
}
