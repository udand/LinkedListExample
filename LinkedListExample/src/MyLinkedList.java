/**
 * LinkedList class implements a doubly-linked list.
 * Umang Desai. 
 * uxd120330
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {
	/**
	 * Construct an empty LinkedList.
	 */
	public MyLinkedList() {
		clear();
	}

	/**
	 * Change the size of this collection to zero.
	 */
	public void clear() {
		beginMarker = new Node<AnyType>(null, null, null);
		endMarker = new Node<AnyType>(null, beginMarker, null);
		beginMarker.next = endMarker;

		theSize = 0;
	}

	/**
	 * Returns the number of items in this collection.
	 * 
	 * @return the number of items in this collection.
	 */
	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Adds an item to this collection, at the end.
	 * 
	 * @param x
	 *            any object.
	 * @return true.
	 */
	public boolean add(AnyType x) {
		add(size(), x);
		return true;
	}

	/**
	 * Adds an item to this collection, at specified position. Items at or after
	 * that position are slid one position higher.
	 * 
	 * @param x
	 *            any object.
	 * @param idx
	 *            position to add at.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between 0 and size(), inclusive.
	 */
	public void add(int idx, AnyType x) {
		addBefore(getNode(idx, 0, size()), x);
	}

	/**
	 * Adds an item to this collection, at specified position p. Items at or
	 * after that position are slid one position higher.
	 * 
	 * @param p
	 *            Node to add before.
	 * @param x
	 *            any object.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between 0 and size(), inclusive.
	 */
	private void addBefore(Node<AnyType> p, AnyType x) {
		Node<AnyType> newNode = new Node<AnyType>(x, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		theSize++;
	}

	/**
	 * Returns the item at position idx.
	 * 
	 * @param idx
	 *            the index to search in.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 */
	public AnyType get(int idx) {
		return getNode(idx).data;
	}

	/**
	 * Changes the item at position idx.
	 * 
	 * @param idx
	 *            the index to change.
	 * @param newVal
	 *            the new value.
	 * @return the old value.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 */
	public AnyType set(int idx, AnyType newVal) {
		Node<AnyType> p = getNode(idx);
		AnyType oldVal = p.data;

		p.data = newVal;
		return oldVal;
	}

	/**
	 * Gets the Node at position idx, which must range from 0 to size( ) - 1.
	 * 
	 * @param idx
	 *            index to search at.
	 * @return internal node corrsponding to idx.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between 0 and size( ) - 1, inclusive.
	 */
	private Node<AnyType> getNode(int idx) {
		return getNode(idx, 0, size() - 1);
	}

	/**
	 * Gets the Node at position idx, which must range from lower to upper.
	 * 
	 * @param idx
	 *            index to search at.
	 * @param lower
	 *            lowest valid index.
	 * @param upper
	 *            highest valid index.
	 * @return internal node corrsponding to idx.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between lower and upper, inclusive.
	 */
	private Node<AnyType> getNode(int idx, int lower, int upper) {
		Node<AnyType> p;

		if (idx < lower || idx > upper)
			throw new IndexOutOfBoundsException("getNode index: " + idx
					+ "; size: " + size());

		if (idx < size() / 2) {
			p = beginMarker.next;
			for (int i = 0; i < idx; i++)
				p = p.next;
		} else {
			p = endMarker;
			for (int i = size(); i > idx; i--)
				p = p.prev;
		}

		return p;
	}

	/**
	 * Removes an item from this collection.
	 * 
	 * @param idx
	 *            the index of the object.
	 * @return the item was removed from the collection.
	 */
	public AnyType remove(int idx) {
		return remove(getNode(idx));
	}

	/**
	 * Removes the object contained in Node p.
	 * 
	 * @param p
	 *            the Node containing the object.
	 * @return the item was removed from the collection.
	 */
	private AnyType remove(Node<AnyType> p) {
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize--;

		return p.data;
	}

	/**
	 * Swap the values for the given index.
	 * 
	 * @param idx
	 *            - first index
	 * @param idy
	 *            - second index
	 * @return true for successful swap.
	 */
	public boolean swap(int idx, int idy) {
		AnyType node = null;
		node = set(idy, getNode(idx).data);
		set(idx, node);
		return true;
	}

	/**
	 * reverse function reverse LinkedList data.
	 * 
	 * @return - MyLinkedList reverse LinkedList.
	 */
	public MyLinkedList<AnyType> reverse() {
		MyLinkedList<AnyType> linkedList = new MyLinkedList<>();
		for (int i = size() - 1; i >= 0; i--) {
			linkedList.add(getNode(i).data);
		}
		return linkedList;
	}

	/**
	 * erase function remove items from LinkedList.
	 * 
	 * @param idx
	 *            - starting index to delete element.
	 * @param n
	 *            - number of element to delete.
	 */
	public void erase(int idx, int n) {
		if ((idx + n) > size()) {
			throw new IndexOutOfBoundsException("Out of range " + (idx + n)
					+ "; size: " + size());
		}

		for (int i = idx; i < (idx + n); i++) {
			remove(idx);
		}
	}

	/**
	 * insertList insert the elements into the list.
	 * 
	 * @param idx
	 *            - starting index from which to enter elements.
	 * @param linkedList
	 *            - data to be inserted.
	 */
	public MyLinkedList<AnyType> insertList(int idx,
			MyLinkedList<AnyType> linkedList) {
		if ((idx) > size()) {
			throw new IndexOutOfBoundsException("Out of range " + (idx)
					+ "; size: " + size());
		}

		MyLinkedList<AnyType> myLinkedList = new MyLinkedList<AnyType>();

		for (int i = 0; i < idx; i++) {
			myLinkedList.add(getNode(i).data);
		}

		for (int i = 0; i < linkedList.size(); i++) {
			myLinkedList.add(linkedList.getNode(i).data);
		}

		for (int i = idx; i < size(); i++) {
			myLinkedList.add(getNode(i).data);
		}

		return myLinkedList;
	}

	/**
	 * Returns a String representation of this collection.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");

		for (AnyType x : this)
			sb.append(x + " ");
		sb.append("]");

		return new String(sb);
	}

	/**
	 * Obtains an Iterator object used to traverse the collection.
	 * 
	 * @return an iterator positioned prior to the first element.
	 */
	public java.util.Iterator<AnyType> iterator() {
		return new LinkedListIterator();
	}

	/**
	 * This is the implementation of the LinkedListIterator. It maintains a
	 * notion of a current position and of course the implicit reference to the
	 * MyLinkedList.
	 */
	private class LinkedListIterator implements java.util.Iterator<AnyType> {
		private Node<AnyType> current = beginMarker.next;
		private boolean okToRemove = false;

		public boolean hasNext() {
			return current != endMarker;
		}

		public AnyType next() {
			if (!hasNext())
				throw new java.util.NoSuchElementException();

			AnyType nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		public void remove() {
			if (!okToRemove)
				throw new IllegalStateException();

			MyLinkedList.this.remove(current.prev);
			okToRemove = false;
		}
	}

	/**
	 * This is the doubly-linked list node.
	 */
	private static class Node<AnyType> {
		public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
			data = d;
			prev = p;
			next = n;
		}

		public AnyType data;
		public Node<AnyType> prev;
		public Node<AnyType> next;
	}

	private int theSize;
	private Node<AnyType> beginMarker;
	private Node<AnyType> endMarker;
}

class TestLinkedList {
	public static void main(String[] args) {
		MyLinkedList<Integer> lst = new MyLinkedList<Integer>();

		for (int i = 0; i < 10; i++)
			lst.add(i);
		for (int i = 20; i < 30; i++)
			lst.add(0, i);

		// lst.remove(0);
		// lst.remove(lst.size() - 1);
		//
		// System.out.println(lst);

		// java.util.Iterator<Integer> itr = lst.iterator();
		// while (itr.hasNext()) {
		// itr.next();
		// itr.remove();
		// System.out.println(lst);
		// }
		System.out
				.println("\n=========================ORIGINAL LIST=========================\n");
		System.out.println(lst);

		System.out
				.println("\n=====================AFTER SWAP FUNCTION=========================\n");

		lst.swap(7, 1); // swap the 1st and 7th index value.
		System.out.println(lst);

		System.out
				.println("\n================AFTER REVERSE FUNCTION==========================\n");

		MyLinkedList<Integer> lst1 = new MyLinkedList<Integer>();
		lst1 = lst.reverse(); // reverse the linked list elements.

		System.out.println(lst1);

		System.out
				.println("\n==================AFTER ERASE FUNCTION====================\n");

		lst1.erase(1, 4); // erase 4 values starting from the index1.
		System.out.println(lst1);

		System.out
				.println("\n==================AFTER INSERT LIST FUNCTION=====================\n");

		MyLinkedList<Integer> lst2 = new MyLinkedList<Integer>();
		for (int i = 100; i < 105; i++)
			lst2.add(i);
		System.out.println(lst1.insertList(1, lst2));  // Insert 5 values starting from the 1st index.
	}
}