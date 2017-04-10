package cs310Hubbert;

import java.util.LinkedList;

/**
 * @author Jay
 *
 */
public class RealtorQueueImpl {

	private RealtorNode<Realtor> front;
	private RealtorNode<Realtor> back;
	private int size;
	
	/**
	 * 
	 */
	public RealtorQueueImpl(){
		super();
		this.front = null;
		this.back = null;
		this.size = 0;
	}

	public RealtorNode<Realtor> getFront() {
		return front;
	}


	public RealtorNode<Realtor> getBack() {
		return back;
	}
	
	public int getSize() {
		return size;
	}

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
	
	public Realtor peek(){
		if(this.size == 0){
			return null;
		}
		return this.front.getRealtor();
	}
	
	/**
	 * @return
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
