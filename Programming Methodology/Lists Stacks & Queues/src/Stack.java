import java.util.NoSuchElementException;

/**
 * This class was created to store a stack of objects. The objects in stack are
 * last in first out (LIFO). This class includes the methods push and pop which
 * add elements to an Object array and remove elements from an Object array
 * respectively. A size method is included to return the number of elements in
 * the Object array. If the array fills up, doubleInSize will increase the size
 * of the array to accommodate for new Objects. This class will also override
 * the toString and equals methods.
 * 
 * @author Anneliese Pessoa
 * 4/26/2016
 *
 */
public class Stack {
	private Object[] data;
	private int numElements;

	/*
	 * Constructor that sets the size of the stack and the numElements.
	 */
	public Stack() {
		data = new Object[100]; // set size of the Object array
		numElements = 0; // set numElements of the array
	}

	/*
	 * This method will push an object into the stack. If the stack is full, the
	 * method calls doubleInSize() in order to make more space for the pushed
	 * Object.
	 */
	public void push(Object other) {
		if (numElements == data.length) { // test the array size
			doubleInSize(); // double the size
		}
		data[numElements++] = other; // add element to the array
	}

	/*
	 * This method will pop the last element in the stack. If the array is
	 * empty, an exception is thrown. The method returns the Object that has
	 * been removed.
	 */
	public Object pop() {
		if (isEmpty()) { // throw an exception if the stack is empty
			throw new NoSuchElementException("The stack is empty! Cannot pop any more elements");
		}
		Object poppedObject = data[numElements - 1]; // store the popped object
		data[--numElements] = null; // remove the Object by setting it to null
		return poppedObject; // return the popped object
	}

	/*
	 * This method returns the number of elements in the stack.
	 */
	public int size() {
		return numElements;
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
	 * This method will return the contents of the stack in the form of a
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
	 * This method will return whether or not the stack is empty.
	 */
	public boolean isEmpty() {
		return numElements == 0;
	}

	/*
	 * This method will compare two stacks and will exit the program if the
	 * object passed into the method is not a Stack.
	 */
	@Override
	public boolean equals(Object other) {
		// test if the object is null or not a stack
		if (other == null || !(other instanceof Stack)) {
			// print out an error message
			System.err.println("Cannot compare this object to the Stack");
			System.exit(0); // exit the program
		}
		Stack that = (Stack) other; // make temporary stack object
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
