package cs310Hubbert;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Jerum Hubbert
 *
 * @param <E>
 *            Whatever you want this node to hold
 */
public class TreeNode<E> implements Iterable<TreeNode<E>> {

	protected E data;
	protected TreeNode<E> left;
	protected TreeNode<E> right;;

	/**
	 * 
	 * @param data
	 *            Content which we want added to the node
	 */
	public TreeNode(E data) {
		this.data = data;
		this.right = null;
		this.left = null;
	}

	/**
	 * Gets the node content
	 * 
	 * @return E Content of the node.
	 */
	public E getData() {
		return data;
	}

	/**
	 * Sets the node content
	 * 
	 * @param data
	 *            E Content of the node.
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Get the left node
	 * 
	 * @return TreeNode A node in the tree
	 */
	public TreeNode<E> getLeft() {
		return this.left;
	}

	/**
	 * Set the left node
	 * 
	 * @param left TreeNode A node in the tree
	 */
	public void setLeft(TreeNode<E> left) {
		this.left = left;
	}

	/**
	 * Get the right node
	 * 
	 * @return TreeNode A node in the tree
	 */
	public TreeNode<E> getRight() {
		return this.right;
	}

	/**
	 * Set the right node
	 * 
	 * @param right TreeNode A node in the tree
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
