/**
 * This class functions as a Stack of Objects through the use of the superclass,
 * List. The Stack is FIFO, meaning the first elements input into the list will
 * be removed first. This class overrides the insert and remove methods by using
 * enqueue and dequeue methods.
 * 
 * @author Anneliese Pessoa
 *
 */
public class Queue extends List {
	
	/*
	 * This method will add the next Object to the beginning of the Queue by
	 * using the superclass, List.
	 */
	public void enqueue(Object next) {
		insert(next, 0);
	}
	
	/*
	 * This method will remove the Object at the end of the list by using
	 * the superclass, List.
	 */
	public Object dequeue() {
		return remove(size() - 1);
	}

	/*
	 * Driver to test the Queue. Exception tests are in the comments.
	 */
	public static void main(String[] args) {
		Queue myTest = new Queue();

		// test removing from empty list error
		// myTest.dequeue();

		// pushed elements
		myTest.enqueue(1);
		myTest.enqueue(2);
		myTest.enqueue(3);
		myTest.enqueue(4);
		myTest.enqueue(5);

		System.out.println("Test list: " + myTest);
		// First in first out principle shown by dequeueing here
		myTest.dequeue();
		System.out.println("Dequeue one element: " + myTest);
		myTest.dequeue();
		System.out.println("Dequeue one element: " + myTest);
	}
}
