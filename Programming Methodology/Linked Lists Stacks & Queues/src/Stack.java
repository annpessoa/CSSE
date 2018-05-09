/**
 * This class functions as a Stack of Objects through the use of the superclass,
 * List. The Stack is LIFO, meaning the last elements input into the list will
 * be the first ones removed. This class overrides the insert and remove methods
 * by using push and pop methods.
 * 
 * @author Anneliese Pessoa
 *
 */
public class Stack extends List {

	/*
	 * This method will add the next Object to the beginning of the stack by
	 * using the superclass, List.
	 */
	public void push(Object next) {
		insert(next, 0);
	}

	/*
	 * This method will remove the Object at the beginning of the list by using
	 * the superclass, List.
	 */
	public Object pop() {
		return remove(0);
	}

	/*
	 * Driver to test the Stack. Exception tests are in the comments.
	 */
	public static void main(String[] args) {
		Stack myTest = new Stack();

		// test removing from empty list error
		// myTest.pop();

		// pushed elements
		myTest.push(1);
		myTest.push(2);
		myTest.push(3);
		myTest.push(4);
		myTest.push(5);

		System.out.println("Test list: " + myTest);
		// popping elements is reverse the order of stored elements
		myTest.pop();
		System.out.println("Pop one element: " + myTest);
		myTest.pop();
		System.out.println("Pop one element: " + myTest);
	}
}
