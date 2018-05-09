import java.util.NoSuchElementException;

/**
 * This class was created to store a queue of objects. The objects in a queue
 * are first in first out (FIFO). This class includes the methods enqueue and
 * dequeue which add elements to an Object array and remove elements from an
 * Object array respectively. A size method is included to return the number of
 * elements in the Object array. If the array fills up, doubleInSize will
 * increase the size of the array to accommodate for new Objects. This class
 * will also override the toString and equals methods.
 * 
 * @author Anneliese Pessoa
 * 4/26/2016
 *
 */
public class Queue {

	private Object[] data;
	private int numElements;

	/*
	 * Constructor that sets the size of the queue and the numElements.
	 */
	public Queue() {
		data = new Object[100];// set size of the Object array
		numElements = 0; // set numElements of the array
	}

	/*
	 * This method will enqueue an object into the queue. If the queue is full,
	 * the method calls doubleInSize() in order to make more space for the
	 * enqueued Object.
	 */
	public void enqueue(Object other) {
		if (numElements == data.length) {// test the array size
			doubleInSize();// double the size
		}
		data[numElements++] = other;// add element to the array
	}

	/*
	 * This method will dequeue the first element in the queue. If the array is
	 * empty, an exception is thrown. The method returns the Object that has
	 * been removed.
	 */
	public Object dequeue() {
		if (isEmpty()) {// throw an exception if the queue is empty
			throw new NoSuchElementException("Cannot remove from empty queue");
		}
		Object retVal = data[0]; // store the dequeued object
		shiftLeft(0); // shift all elements left
		numElements--; // subtract from numElements;
		return retVal; // return the dequeued object
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
	 * This method will return the contents of the queue in the form of a
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
	 * This method will return whether or not the queue is empty.
	 */
	public boolean isEmpty() {
		return numElements == 0;
	}

	/*
	 * This method will compare two queues and will exit the program if the
	 * object passed into the method is not a queue.
	 */
	@Override
	public boolean equals(Object other) {
		// test if the object is null or not a queue
		if (other == null || !(other instanceof Queue)) {
			// print out an error message
			System.err.println("Cannot compare this object to the Queue");
			System.exit(0); // exit the program
		}
		Queue that = (Queue) other;// make temporary queue object
		if (numElements != that.size()) {
			return false; // return false if the sizes are not the same
		}
		for (int i = 0; i < numElements; i++) { // loop through the array
			if (data[i] != that.data[i]) {
				return false; // return false if any value is different
			}
		}
		return true;
	}
}
