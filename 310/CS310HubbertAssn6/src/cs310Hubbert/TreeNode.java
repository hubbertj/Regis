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
	
	private E data;
	private TreeNode<E> parent;
	private List<TreeNode<E>> children;
	
	/**
	 * 
	 * @param data
	 */
	public TreeNode(E data) {
        this.data = data;
        this.children = new LinkedList<TreeNode<E>>();
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
	public TreeNode<E> getParent() {
		return parent;
	}
	
	/**
	 * 
	 * @param parent
	 */
	public void setParent(TreeNode<E> parent) {
		this.parent = parent;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<TreeNode<E>> getChildren() {
		return children;
	}
	
	/**
	 * 
	 * @param children
	 */
	public void setChildren(List<TreeNode<E>> children) {
		this.children = children;
	}

	@Override
	public Iterator<TreeNode<E>> iterator() {
		return null;
	}

}
