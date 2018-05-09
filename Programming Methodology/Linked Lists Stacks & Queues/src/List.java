
/**
 * This class functions as a List of Objects through the use of the Node
 * class. The class is able to insert and remove Objects at any location in the
 * List by going through each element in the list. Indexes must be valid
 * in order to get correct results; they cannot be larger than the size of the
 * list or less than 0.
 * 
 * @author Anneliese Pessoa
 *
 */
public class List {

	Node head = null; // start of the linked list

	/*
	 * This method will insert an Object into the List at any index as
	 * long as it is positive. Throws a LinkedListException if indexes are
	 * invalid.
	 */
	public void insert(Object next, int index) {
		Node newNode = new Node(next, null); // create new node
		if (isEmpty()) { // first test for an empty list
			if (index > 0 || index < 0) { // test if index is not 0
				throw new LinkedListException("Error - invalid index");
			}
			head = newNode; // set new head

		} else if (size() == 1) { // test for a list containing one element
			if (index < 0 || index > 1) { // test if index is not 0 or 1
				throw new LinkedListException("Error - invalid index");
			} else if (index == 0) { // add onto the start of the list
				newNode.next = head;
				head = newNode;
			} else if (index == 1) { // add onto the end of the list
				head.next = newNode;
			}

		} else { // test for a list containing more than 2 elements
			if (index < 0 || index > size()) { // test if index is valid
				throw new LinkedListException("Error - invalid index");
			}
			if (index == 0) { // if the index is 0, create a new head
				newNode.next = head; // set next value in newNode to equal head
				head = newNode; // set head to new head
			}else {
				Node current = head; // create a temporary current Node
				// loop through the list
				for (int currentIndex = 0; currentIndex < index - 1; currentIndex++) {
					current = current.next;
				}
				// copy values from current to newNode starting at index
				newNode.next = current.next;
				// change values after index to include the new Node
				current.next = newNode;
			}
		}
	}

	/*
	 * This method removes and returns Objects from the List but only if
	 * the index given is valid and the list is not empty. Throws an exception
	 * if the index is invalid or if the list is empty.
	 */
	public Object remove(int index) {
		Object retVal = null; // initialize returned Object
		if (isEmpty()) { // test if the list is empty
			throw new LinkedListException("Error - cannot remove from an empty List");

		} else if (size() == 1) { // remove from a list containing one element
			if (index != 0) { // test for valid index
				throw new LinkedListException("Error - invalid index");
			}
			retVal = head.data; // set retVal to return the removed Object
			head = null; // remove the Object by setting head to null

		} else { // remove from list containing more than one Object
			if (index < 0 || index >= size()) { // test for valid index
				throw new LinkedListException("Error - invalid index");
			}
			if (index == 0) { // remove and replace head
				retVal = head.data; // set retVal to return the removed Object
				head = head.next; // move all values up the list to the head
			} else { // remove from any other place in the list

				Node current = head; // int i = 0
				Node prev = null;

				int currentIndex = 0; // i = 0
				// i < numElements
				while (current != null && currentIndex != index) {
					prev = current;
					current = current.next; // i++
					currentIndex++;
				}
				if (current == head) {
					head = head.next; // move all values up the list to the head
				} else {
					prev.next = current.next; // cut out prev.next
				}
				retVal = current.data; // set retVal to return the removed Object
			}
		}
		return retVal; // return the removed object
	}

	/*
	 * This method will add the input Object to the end of the List.
	 */
	public void append(Object next) {
		int index = size(); // index at the end of the list
		insert(next, index); // append Object to the end of the list
	}

	/*
	 * This method will delete the Object at the given index by calling the
	 * remove function.
	 */
	public void delete(int index) {
		remove(index); // delete the object at index
	}

	/*
	 * This method will return the size of the List.
	 */
	public int size() {
		Node current = head; // int i = 0
		int size = 0;
		while (current != null) { // i < numElements
			size++;
			current = current.next; // i++
		}
		return size;
	}

	/*
	 * This method will create a String that contains all values in the
	 * List.
	 */
	@Override
	public String toString() {
		Node current = head; // int i = 0
		String retVal = "";
		while (current != null) { // i < numElements
			retVal += current.data + ", ";
			current = current.next; // i++
		}
		return retVal;
	}

	/*
	 * This method returns a boolean stating whether the List is empty or
	 * not. This is done by testing if the head of the list is null.
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/*
	 * This method will return the index of the target Object. Returns -1 if the
	 * object is not found.
	 */
	public int indexOf(Object target) {
		int retVal = -1; // set default returned value to -1
		int index = 0; // start at index 0
		Node current = head; // initialize current node to the head
		while (current != null) { // loop until there are no more values
			// set returned value to the index of the found target
			if (current.data.equals(target)) {
				retVal = index;
			}
			index++; // increment index
			current = current.next; // go to next Node
		}
		return retVal;
	}

	/*
	 * Driver to test the List. Exception tests are in the comments.
	 */
	public static void main(String[] args) {
		List empty = new List();
		List one = new List();
		List multiple = new List();

		one.append(5);
		multiple.append(10);
		multiple.append(20);
		multiple.append(30);

		System.out.println("Empty:" + empty);
		System.out.println("One:" + one);
		System.out.println("Multiple:" + multiple);

		one.delete(0);
		multiple.delete(1);
		System.out.println("One (upon delete):" + one);
		System.out.println("Multiple (upon delete):" + multiple);

		// test invalid indexes
		// one.insert(600, 1);
		// multiple.insert(400, 2);

		one.insert(600, 0);
		multiple.insert(400, 1);
		System.out.println("One (on insert):" + one);
		System.out.println("Multiple(on insert):" + multiple);
		
		List myTest = new List();
		//test removing from empty list error
		//myTest.remove(0);
		
		//test invalid indexes in an empty list
		//myTest.insert(1, -1);
		//myTest.insert(1, 1);
		
		myTest.insert(1,0); //insert to empty list
		
		//test invalid indexes in a list that contains one element
		//myTest.insert(2,-1);
		//myTest.insert(2, 2);
		//myTest.remove(-1);
		//myTest.remove(2);
		
		myTest.insert(0, 0); //insert to a list containing one element
		
		//test invalid indexes in a list that contains more than two elements
		//myTest.insert(-1, -1);
		//myTest.insert(3, 4);
		//myTest.remove(-1);
		//myTest.remove(6);
		
		myTest.insert(-1, 0); //insert to a list containing more than one element
		myTest.insert(3, 3); 
		myTest.insert(2, 3); //insert in between two objects
		
		//test removing elements past the size of the list
		//myTest.remove(5);
	   
		myTest.remove(1); // remove element at index 1
		myTest.remove(0); // remove element at index 0
		myTest.remove(myTest.size()-1); //remove element at the end of the list
		
		System.out.println("Test list: " + myTest);
		
	}

	private class Node {
		// super public variables for Node class
		Object data;
		Node next;

		public Node(Object d, Node n) {
			data = d; // set data variable
			next = n; // set next variable
		}
	}

}
