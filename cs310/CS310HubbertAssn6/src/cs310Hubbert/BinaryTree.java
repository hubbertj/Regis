package cs310Hubbert;

/**
 * 
 * @author Jerum Hubbert
 *
 */
public class BinaryTree<T> {

	private TreeNode<T> root;
	private Boolean addReturn = false;

	/**
	 * Constructor that sets the root of the tree
	 * 
	 * @param node TreeNode The root of the tree
	 */
	public BinaryTree(TreeNode<T> node) {
		this.root = node;
	}

	/**
	 * Constructor that sets the root data, leftTree, and rightTree
	 * 
	 * @param data
	 *            The data The data from the root.
	 * @param leftTree
	 *            The left tree to be set.
	 * @param rightTree
	 *            The right tree to be set.
	 */
	public BinaryTree(T data, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		this.root = new TreeNode<T>(data);

		if (leftTree != null) {
			this.root.setLeft(leftTree.getRoot());
		} else {
			this.root.setLeft(null);
		}

		if (rightTree != null) {
			this.root.setRight(rightTree.getRoot());
		} else {
			this.root.setRight(null);
		}
	}

	/**
	 * Constructor a blank constructor
	 */
	public BinaryTree() {
		this(null);
	}

	/**
	 * Gets the tree root
	 * 
	 * @return TreeNode The root of the tree
	 */
	public TreeNode<T> getRoot() {
		return root;
	}

	/**
	 * Sets the tree root
	 * 
	 * @param root
	 *            TreeNode The root of the tree
	 */
	public void setRoot(TreeNode<T> root) {
		this.root = root;
	}

	@Override
	/**
	 * Traverse through the tree and outputs data.
	 * 
	 * @return String a readable representation of the binary tree
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		this.preOrderTraverse(this.root, 1, str);
		return str.toString();
	}

	/**
	 * Find data using binary search
	 * 
	 * @param fTarget The target we are trying to find
	 * @return T The type which BinaryTree class is built for, null is target
	 *         was not found.
	 */
	public T find(T fTarget) {
		return find(this.root, fTarget);
	}

	private T find(TreeNode<T> node, T fTarget) {
		String value = "";
		String dataStr = "";

		if (node == null) {
			return null;
		}

		if (fTarget instanceof Realtor && node.getData() instanceof Realtor) {
			value = ((Realtor) fTarget).getLicenseNum();
			dataStr = ((Realtor) node.getData()).getLicenseNum();
		}

		if (fTarget instanceof Property && node.getData() instanceof Property) {
			value = ((Property) fTarget).getMlsNum();
			dataStr = ((Property) node.getData()).getMlsNum();
		}

		int cResult = value.compareTo(dataStr);

		if (cResult == 0) {
			return node.data;
		} else if (cResult < 0) {
			return this.find(node.getLeft(), fTarget);
		} else {
			return this.find(node.getRight(), fTarget);
		}
	}

	private void preOrderTraverse(TreeNode<T> aNode, int depth, StringBuilder str) {
		if (aNode != null) {
			str.append(aNode.toString());
			str.append("\n");
			this.preOrderTraverse(aNode.getLeft(), depth++, str);
			this.preOrderTraverse(aNode.getRight(), depth++, str);
		}
	}

	/**
	 * Adds a item to the correct order in the binary tree
	 * 
	 * @param  item T The item you want to add to the tree
	 * @return Boolean True if the item was added, false if it was not.
	 */
	public Boolean add(T item) {
		this.root = add(this.root, item);
		return this.addReturn;
	}

	private TreeNode<T> add(TreeNode<T> newRoot, T item) {
		String value = "";
		String dataStr = "";

		if (newRoot == null) {
			this.addReturn = true;
			return new TreeNode<T>(item);
		}

		if (item instanceof Realtor && newRoot.getData() instanceof Realtor) {
			value = ((Realtor) item).getLicenseNum();
			dataStr = ((Realtor) newRoot.getData()).getLicenseNum();
		}

		if (item instanceof Property && newRoot.getData() instanceof Property) {
			value = ((Property) item).getMlsNum();
			dataStr = ((Property) newRoot.getData()).getMlsNum();
		}

		if (value.compareTo(dataStr) == 0) {
			this.addReturn = false;
			return newRoot;
		} else if (value.compareTo(dataStr) < 0) {
			newRoot.setLeft(add(newRoot.getLeft(), item));
			return newRoot;
		} else {
			newRoot.setRight(add(newRoot.getRight(), item));
			return newRoot;
		}
	}

}
