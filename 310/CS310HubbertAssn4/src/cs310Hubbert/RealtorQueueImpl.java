package cs310Hubbert;

import java.util.LinkedList;

/**
 * @author Jay
 *
 */
public class RealtorQueueImpl {
	
	private LinkedList<Realtor> realtorQueue = new LinkedList<Realtor>();
	
	private Realtor front;
	private Realtor back;
	
	/**
	 * 
	 */
	public RealtorQueueImpl(){
		super();
		this.front = null;
		this.back = null;
	}

	/**
	 * @return
	 */
	public Realtor getFront() {
		return front;
	}


	/**
	 * @return
	 */
	public Realtor getBack() {
		return back;
	}

	public void add(Realtor realtor){
		
	}
	
	public Realtor remove(Realtor realtor){
		return new Realtor();
	}
	
	/**
	 * Checks if the Queue is empty or not
	 * 
	 * @return Boolean True if the Queue is empty
	 */
	public Boolean isEmpty() {
		if(realtorQueue.size() > 0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Checks if the Queue is full or not
	 * @return Boolean True if the Queue is full
	 */
	public Boolean isFull(){ 
		try{
			this.realtorQueue.add(new Realtor());
			this.realtorQueue.removeLast();
			return false;
			
		}catch(Exception ex){
			ex.printStackTrace();
			return true;
		}
	}
}
