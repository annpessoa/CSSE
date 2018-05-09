import java.util.NoSuchElementException;

/**
 * This class was created to store an ArrayList of objects. The objects in an
 * ArrayList are inserted individually into the array. This class includes the
 * methods insert and remove which add elements to an Object array and remove
 * elements from an Object array respectively. A size method is included to
 * return the number of elements in the Object array. If the array fills up,
 * doubleInSize will increase the size of the array to accommodate for new
 * Objects. This class will also override the toString and equals methods.
 * 
 * @author Anneliese Pessoa
 * 4/26/2016
 *
 */
public class ArrayList {

	private Object[] data;
	private int numElements;

	/*
	 * Constructor that sets the size of the ArrayList and the numElements.
	 */
	public ArrayList() {
		data = new Object[100];// set size of the Object array
		numElements = 0; // set numElements of the array
	}

	/*
	 * This method will insert an object into the ArrayList. If the ArrayList is
	 * full, the method calls doubleInSize() in order to make more space for the
	 * inserted Object. All objects following the inserted object will be
	 * shifted to the right.
	 */
	public void insert(Object other, int index) {
		if (numElements == data.length) { // test the array size
			doubleInSize(); // double the size
		}
		shiftRight(index); // shift all elements to the right
		numElements++; // add to numElements
		data[index] = other; // set the object at index to other
	}

	/*
	 * This method will remove the element at the index and shift all values
	 * left in the array. If the array is empty, an exception is thrown. The
	 * method returns the Object that has been removed.
	 */
	public Object remove(int index) {
		if (isEmpty()) { // throw an exception if the ArrayList is empty
			throw new NoSuchElementException("Cannot remove from empty ArrayList");
		}
		Object retVal = data[0]; // store the removed object
		shiftLeft(index); // shift all elements to the left
		numElements--; // subtract from numElements;
		return retVal; // return removed object
	}

	/*
	 * This method will shift all elements in the array to the right starting at
	 * the start index.
	 */
	public void shiftRight(int start) {
		for (int i = data.length - 2; i >= start; i--) { // loop through array
			data[i + 1] = data[i]; // shift all values right
		}

	}

	/*
	 * This method will shift all elements in the array to the left starting at
	 * the start index.
	 */
	public void shiftLeft(int start) {
		for (int i = start; i < numElements - 1; i++) { // loop through array
			data[i] = data[i + 1]; // shift all values left
		}
	}

	/*
	 * This method will double the size of the array by creating a new array and
	 * copying all values from the previous array onto the new one.
	 */
	public void doubleInSize() {
		System.out.println("In resize!!");
		// create an array that is 2x the length of the original
		Object[] temp = new Object[data.length * 2];
		for (int i = 0; i < numElements; i++) { // loop through the array
			temp[i] = data[i]; // copy values at each index
		}
		data = temp; // set the data arrays values
	}

	/*
	 * This method returns the number of elements in the queue.
	 */
	public int size() {
		return numElements;
	}

	/*
	 * This method will return the contents of the ArrayList in the form of a
	 * String.
	 */
	@Override
	public String toString() {
		String retVal = "";
		for (int i = 0; i < numElements; i++) { // loop through the array
			retVal += data[i]; // add each element to the String
		}
		return retVal; // return the array as a string
	}

	/*
	 * This method will return whether or not the ArrayList is empty.
	 */
	public boolean isEmpty() {
		return numElements == 0;
	}

	/*
	 * This method will return the index of the first occurrence of the other
	 * Object. Returns -1 if not found./* This method will return whether or not
	 * the queue is empty.
	 */
	public int indexOf(Object other) {
		for (int i = 0; i < numElements; i++) { // loop through the array
			// test if the object in the array matches other
			if (data[i] == other) {
				return i; // return the index of the other object
			}
		}
		return -1;
	}

	/*
	 * This method will compare two ArrayLists and will exit the program if the
	 * object passed into the method is not an ArrayList.
	 */
	@Override
	public boolean equals(Object other) {
		// test if the object is null or not an ArrayList
		if (other == null || !(other instanceof ArrayList)) {
			// print out an error message
			System.err.println("Cannot compare this object to the ArrayList");
			System.exit(0);// exit the program
		}
		ArrayList that = (ArrayList) other; // make temporary ArrayList object
		if (numElements != that.size()) {
			return false;// return false if the sizes are not the same
		}
		for (int i = 0; i < numElements; i++) {// loop through the array
			if (data[i] != that.data[i]) {
				return false;// return false if any value is different
			}
		}
		return true;
	}

}
