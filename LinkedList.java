
package data_structures;

import java.util.Iterator;

import data_structures.LinkedList.Node;

/**
 * The linked list for our hash will only implement the
 * methods in the HashListI interface, a reduced set of
 * methods compared to the linked list from Assignment 1.
 * 
 * @author Milad Balkhinezhad
 *
 */
public class LinkedList<E> implements HashListI<E> {

	class Node<E> {
		E data;
		Node<E> next;
		public Node (E obj){
			data = obj;
			next = null;
		}
	}
	public Node<E> head;
	public Node<E> tail;
	public int currentSize;
	private Comparable<E> data;

	public LinkedList() {
		head = null;
		tail = null;
		currentSize = 0;

	}
	
	/**
	 * Adds an object to the list.
	 * 
	 * @param obj the object to be added to the list.
	 */
	
	public void add(E obj) {
		Node<E> node = new Node<E>(obj);
		node.next = head;
		if (head == null)
			tail = node;
		head = node;
		currentSize++;
		return;
	}
	
	/**
	 * Remove an object from the list
	 * @param obj The object to remove
	 * @return The object removed
	 */
	
	public E remove(E obj) {
		if (isEmpty())
			return null;
		Node<E> current = head , previous = null;
		while (current != null) {
			if (((Comparable<E>)current.data).compareTo(obj) == 0) {
				previous.next = current.next;
				currentSize--;
				return current.data;
			}
			previous = current;
			current = current.next;
		}
		return null;
	}

	/**
	 * Make the list empty
	 */

	public void makeEmpty() {
		tail = head = null;
		currentSize = 0;
	}

	/**
	 * Is the list empty?
	 * @return true if the list is empty
	 */

	public boolean isEmpty() {
		return head == null;
	}
	
	/**
	 * The current number of elements in the list
	 * @return the size of the llist
	 */

	public int size() {
		return currentSize;
	}

	/**
	 * Does the list contain this object
	 * @param obj The object to look for
	 * @return True if the list contains it.
	 */

	public boolean contains(E obj) {
		Node <E> tmp = head;
		while (tmp.next != null) {
			if (tmp.data.equals(obj)) {
				return true;
			}
			tmp = tmp.next;
		}
		return false;
	}

	/**
	 * An iterator for the list
	 */

	public Iterator<E> iterator() {
		return new IteratorHelper();
	}
	class IteratorHelper implements Iterator<E> {
		Node<E> index;

		public IteratorHelper() {
			index = head;
		}

		@Override
		public boolean hasNext() {
			return index != null;
		}

		@Override
		public E next() {
			E tmp = index.data;
			index = index.next;
			return tmp;
		}
	}
}

