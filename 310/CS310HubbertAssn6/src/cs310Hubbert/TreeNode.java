package cs310Hubbert;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Jerum Hubbert
 *
 * @param <E>
 */
public class TreeNode<E> implements Iterable<TreeNode<E>> {
	
	protected E data;
	protected TreeNode<E> left;
	protected TreeNode<E> right;;
	
	/**
	 * 
	 * @param data
	 */
	public TreeNode(E data) {
        this.data = data;
        this.right = null;
        this.left = null;
    }
	
	/**
	 * 
	 * @return
	 */
	public E getData() {
		return data;
	}
	
	/**
	 * 
	 * @param data
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * 
	 * @return
	 */
	public TreeNode<E> getLeft() {
		return this.left;
	}
	
	/**
	 * 
	 * @param parent
	 */
	public void setLeft(TreeNode<E> left) {
		this.left = left;
	}
	
	/**
	 * 
	 * @return
	 */
	public TreeNode<E> getRight() {
		return this.right;
	}
	
	/**
	 * 
	 * @param children
	 */
	public void setRight(TreeNode<E> right) {
		this.right = right;
	}
	
	
	@Override
	public String toString() {
		return this.data.toString();
	}

	@Override
	public Iterator<TreeNode<E>> iterator() {
		return null;
	}

}
