package data_structures;

import java.util.Iterator;

/**
 * Main class for a Red Black Tree. A type of binary tree which must follow specific guidelines, while each node must be labeled as Red or Black, and the root is always black.
 * @author Milad Balkhinezhad
 */

public class RedBlackTree<K, V> implements RedBlackI<K, V> {

	Node<K,V> root;
	int size;

	class Node <K,V> {
		K key;
		V value;
		Node<K,V> left, right, parent;
		boolean isLeftChild, black;

		public Node (K key, V value) {
			this.key = key;
			this.value = value;
			left = right = parent = null;
			black = false;
			isLeftChild = false;
		}
	}

	/**
	 * The method to add to the RBTree.  It will not allow duplicate additions.
	 * @param key the key to add
	 * @param value the value associated with the key
	 */
	public void add(K key, V value) {
		Node<K,V> node = new Node<K,V>(key,value);
		if (root == null) {
			root = node;
			root.black = true;
			size++;
			return;
		}
		add(root,node);
		size++;
	}

	private void add(Node<K, V> parent, Node<K, V> newNode) {
		if (((Comparable<K>) newNode.key).compareTo(parent.key) > 0) {
			if (parent.right == null) {
				parent.right = newNode;
				newNode.parent = parent;
				newNode.isLeftChild = false;
				return;
			} else
				add(parent.right, newNode);
		} else {
			if (parent.left == null) {
				parent.left = newNode;
				newNode.parent = parent;
				newNode.isLeftChild = true;
				return;
			} else
				add(parent.left, newNode);
		}

		checkColor(newNode);
	}
	/**
	 * Method to check if the root node is black, and to fix nodes if they do not meet the requirements for the RBTree.
	 * @param node
	 */
	public void checkColor(Node<K, V> node) {
		if (node == root) {
			node.black = true;
			return;
		}
		if (!node.black && !node.parent.black) {
			correctTree(node);
		}
		if (node.parent != null) {
			checkColor(node.parent);
		}
	}
	/**
	 * A method to correct the tree if two consecutive red nodes appear on the tree. Rotates tree accordingly and applies RBTree rules.
	 * @param node
	 */
	public void correctTree(Node<K,V> node) {
		if (node.parent.isLeftChild) {
			// aunt is parent.right
			if (node.parent.parent.right == null || node.parent.parent.right.black) {
				//black aunt, rotate
				rotate(node);
			} else {
				if (node.parent.parent.right != null)
					node.parent.parent.right.black = true;
				node.parent.parent.black = false;
				node.parent.black =  true;		
			}
		} else {
			// aunt is parent.left
			if (node.parent.parent.left == null || node.parent.parent.left.black) {
				//black aunt, rotate
				rotate(node);
			} else {
				if (node.parent.parent.left != null)
					node.parent.parent.left.black = true;
				node.parent.parent.black = false;
				node.parent.black =  true;		
			}
		}
	}
	/**
	 * General rotation for the tree, implemented when tree needs to be balanced.
	 * @param node
	 */
	public void rotate(Node<K,V> node) {
		if (node.isLeftChild) {
			// parent is a left child
			if (node.parent.isLeftChild) {
				rightRotate(node.parent.parent);
				// after a right rotate, we become red, our parent becomes black, and our sibling (node.parent.right) is red
				// we become red
				node.black = false;
				// our parent becomes black
				node.parent.black = true;
				// Change sibling to be red
				if (node.parent.right != null)
					node.parent.right.black = false;
				// else, parent is a right child
			} else {
				rightLeftRotate(node.parent.parent);
				// after a right left, the node we start at is the new parent
				// and is black
				node.black = true;
				node.right.black = false;
				if(node.left != null)
					node.left.black = false;
			}
		} else {
			if (node.parent.isLeftChild) {
				leftRightRotate(node.parent.parent);
				// after a left right, the node w0e start at is the new parent
				// and is black
				node.black = true;
				node.right.black = false;
				node.left.black = false;
			} else {
				leftRotate(node.parent.parent);
				// after a left rotate, we become red, our parent becomes black, and our sibling (node.parent.left) is red
				node.black = false;
				node.parent.black = true;
				if (node.parent.left != null)
					node.parent.left.black = false;
			}
		}
	}
	/**
	 * General right rotation for the tree when it needs to be balanced.
	 * @param node
	 */
	public void rightRotate(Node<K,V> node) {
		Node<K,V> temp = node.left;
		node.left = temp.right;

		if (node.left != null) {
			node.left.parent = node;
			node.left.isLeftChild = true;
		}

		// check if we are the root node
		if (node.parent == null) {
			root = temp;
			temp.parent = null;
		} else {
			temp.parent = node.parent;
			if (node.isLeftChild) {
				temp.isLeftChild = true;
				temp.parent.left = temp;
			} else {
				temp.isLeftChild = false;
				temp.parent.right = temp;
			}
		}

		temp.right = node;
		node.isLeftChild = false;
		node.parent = temp;
	}
	/**
	 * General left rotation for the tree when it needs to be balanced.
	 * @param node
	 */
	public void leftRotate(Node<K,V> node) {
		Node <K,V> temp = node.right;
		node.right = temp.left;
		if (node.right != null) {
			node.right.parent = node;
			node.right.isLeftChild = false;
		}
		if (node.parent == null) {
			root = temp;
			temp.parent = null;
		}
		else {
			temp.parent = node.parent;
			if (node.isLeftChild) {
				temp.isLeftChild = true;
				temp.parent.left = temp;
			}
			else {
				temp.isLeftChild = false;
				temp.parent.right = temp;
			}
			temp.left = node;
			node.isLeftChild = true;
			node.parent = temp;
		}
	}

	/**
	 * Implements the leftRotate and rightRotate to generate a leftRightRotate when tree needs balancing.
	 */
	public void leftRightRotate(Node<K,V> node) {
		leftRotate(node.left);
		rightRotate(node);
	}

	/**
	 * Implements the rightRotate and leftRotate to generate a rightLeftRotate when tree needs balancing.
	 */
	public void rightLeftRotate(Node<K,V> node) {
		rightRotate(node.right);
		leftRotate(node);
	}

	/**
	 * Corrects the tree when the number of black nodes on either side of the root are unequal.
	 * @param node
	 * @return leftBlackNodes
	 */
	public int blackNodes(Node<K,V> node) {
		if (node == null)
			return 1;
		int rightBlackNodes = blackNodes(node.right);
		int leftBlackNodes = blackNodes(node.left);
		if (rightBlackNodes != leftBlackNodes)
			System.out.println("Unequal Black Nodes");
		if (rightBlackNodes > leftBlackNodes)
			rightBlackNodes = leftBlackNodes;
		if (node.black)
			leftBlackNodes++;
		return leftBlackNodes;

	}

	/**
	 * Tests whether the RBTree contains the key
	 * @param key the key to look for
	 * @return whether the key is found
	 */
	public boolean contains(K key) {
		Node<K, V> tmp = root;
		if (isEmpty()) {
			return false;
		}
		while (tmp.right != null && tmp.left != null) {
			if (((Comparable<K>) tmp.key).compareTo(key) == 0) {
				return true;
			}
			if (((Comparable<K>) tmp.key).compareTo(key) > 0) {
				tmp = tmp.left;
				return true;
			} else {
				tmp = tmp.right;
				return true;
			}
		}
		return false;
	}

	/**
	 * Get the value associated with a given key
	 * @param key the key to get the value for
	 * @return the current value
	 */
	public V getValue(K key) {
		if (root == null) {
			return null;
		}
		Node<K,V> current = root;
		while (((Comparable<K>)current.key).compareTo(key) != 0) {
			if (((Comparable<K>)key).compareTo((K)current.key) < 0)
				current = current.left;
			else 
				current = current.right;
			if (current == null)
				return null;
		}
		return current.value;
	}

	/**
	 * Returns the number of elements in the RBTree
	 * @return the number of elements in the tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Test whether the RBTree is empty
	 * @return <code>true</code> if the tree is empty
	 * 		   <code>false</code> if the tree is not empty 
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * The height of the tree. Recall that a tree with 
	 * only a root node has height 0 
	 * @return the height of the tree at the root node
	 */

	public int height() {
		if (root == null) {
			return 0;
		}
		return height(root) - 1;
	}

	public int height(Node<K,V> node) {
		if (node == null) {
			return 0;	
		}
		int leftheight = height(node.left) +1;
		int rightheight = height(node.right) +1;
		if (leftheight > rightheight) {
			return leftheight;
		}
		return rightheight;
	}

	/**
	 * An iterator for all the keys in the RBTree. This will
	 * iterate over the keys using <b>InOrder Traversal</b>
	 * @see java.lang.Iterable#iterator()
	 */

	@Override
	public Iterator<K> iterator() {
		return new IteratorHelper<K>(root);
	}

	private class IteratorHelper<K> implements Iterator<K> {
		K[] keys; //
		Node<K, V> tmp;
		int position;

		public IteratorHelper(Node<K, V> root) {
			keys = (K[]) new Object [size];
			position = 0;
			traversal((Node<K,V>) root);
			
		}
		
		public void traversal(Node<K, V> node) {
			tmp = node;
			if (node == null)
				return;
			if(node.left != null)
				traversal(node.left);
			keys[position++] = node.key;
			if(node.right != null)
				traversal(node.right);
		}

		@Override
		public boolean hasNext() {
			if (keys.length > position)
				return true;
			else
				return false;
		}

		@Override
		public K next() {
			if (!hasNext())
			return null;
			return keys[position++];
		}
		public void remove() {
			System.out.println("Unsupported Operation");
		}
	}

	/**
	 * Recursively print the tree. This method should print the
	 * entire tree using <em>Inorder Traversal</em> to the standard
	 * output (i.e. using System.out.println or System.out.print).
	 * You can print the tree one node per line, and use periods to
	 * note the hierarchy of the tree.
	 */

	public void print() {
		print(root, 0);
	}
	public void print(Node<K,V> node, int levels) {
		if (node == null)
			return;
		print(node.left, levels + 1);
		for (int i = 0; i < levels; i++) {
			System.out.print(".");
		}
		String color =" : Red";
		if (node.black) {
			color = " : Black";
		}
		System.out.println(node.key + color);
		print(node.right, levels + 1);
	}
}