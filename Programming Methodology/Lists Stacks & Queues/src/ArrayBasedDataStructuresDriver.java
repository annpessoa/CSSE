/*
 *  Incomplete Driver for ArrayList(ObjectList), Stack and Queue
 *  (Edit): Complete Driver for ArrayList, Stack, and Queue - tests 
 *  all methods in each class.
 *  
 *  @author Anneliese Pessoa
 *  4/26/2016
 * 
 */

public class ArrayBasedDataStructuresDriver {

	/*
	 * Main method: runs all methods included in this class.
	 */
	public static void main(String[] args) {
		System.out.println("Stack Tests : \n");
		stackTests();
		System.out.println("\nQueue Tests : \n");
		queueTests();
		System.out.println("\nArrayList Tests: \n");
		arrayListTests();
	}

	/*
	 * This method contains tests for all methods in ArrayList.
	 */
	private static void arrayListTests() {
		// todo: make more tests here
		ArrayList a = new ArrayList();

		a.insert('B', 0);
		a.insert('a', 0);
		a.insert('t', 1);

		System.out.println(a.toString());

		while (a.isEmpty() == false) {
			System.out.println(a.remove(0));
		}
		// Extra tests
		ArrayList test = new ArrayList(); // create new ArrayList
		// add the word "Test" into the ArrayList
		test.insert('T', 0);
		test.insert('e', 1);
		test.insert('s', 2);
		test.insert('t', 3);
		// print out the ArrayList
		System.out.println(test.toString());
		// remove the letter s
		test.remove(2);
		// print out the ArrayList
		System.out.println(test.toString());
		// inset the letter x where s used to be
		test.insert('x', 2);
		// print out the ArrayList
		System.out.println(test.toString());
		// shift all values left starting at index 1
		test.shiftLeft(1);
		// print out the ArrayList
		System.out.println(test.toString());
		// shift all values right starting at index 1
		test.shiftRight(1);
		// print out the ArrayList
		System.out.println(test.toString());
		// test size method
		System.out.println("size of the ArrayList: " + test.size());
		// test indexOf method
		System.out.println("Index of A: " + test.indexOf('A'));
		System.out.println("Index of t: " + test.indexOf('t'));
		// test equality between multiple arrays
		System.out.println("Is ArrayList a equal to ArrayList test? " + a.equals(test));
		ArrayList test2 = new ArrayList();
		test2 = test;
		System.out.println("Is ArrayList test equal to ArrayList test2? " + test.equals(test2));
	}

	/*
	 * This method contains tests for all methods in Queue.
	 */
	private static void queueTests() {
		// todo: make more tests here
		Queue a = new Queue();

		a.enqueue('B');
		a.enqueue('a');
		a.enqueue('t');

		System.out.println(a.toString());

		while (a.isEmpty() == false) {
			System.out.println(a.dequeue());
		}

		// Extra tests
		Queue test = new Queue(); // make a new queue
		// add the word "Test" to the array
		test.enqueue('T');
		test.enqueue('e');
		test.enqueue('s');
		test.enqueue('t');
		// print out the queue
		System.out.println(test.toString());
		test.dequeue(); // dequeue one item
		// print out the queue
		System.out.println(test.toString());
		// shift all values left starting at index 1
		test.shiftLeft(1);
		// print out the queue
		System.out.println(test.toString());
		// Print out the size of the array
		System.out.println("Size of the queue: " + test.size());
		// test if the array is empty
		System.out.println("Is the queue empty? " + test.isEmpty());
		// test equals function
		System.out.println("Is queue a equal to queue test? " + a.equals(test));
		Queue test2 = new Queue();
		test2 = test;
		System.out.println("Is queue test equal to queue test2? " + test.equals(test2));

	}

	/*
	 * This method contains tests for all methods in Stack.
	 */
	private static void stackTests() {
		// todo: make more tests here
		Stack a = new Stack();

		a.push('B');
		a.push('a');
		a.push('t');

		System.out.println(a.toString());

		while (a.isEmpty() == false) {
			System.out.println(a.pop());
		}

		// Extra tests
		Stack test = new Stack();
		// add the word "Test" to the array
		test.push('T');
		test.push('e');
		test.push('s');
		test.push('t');
		// print out the Stack
		System.out.println(test.toString());
		test.pop(); // pop one object
		// print out the Stack
		System.out.println(test.toString());
		// test size function
		System.out.println("Size of the stack: " + test.size());
		// test isEmpty()
		System.out.println("Is the stack empty? " + test.isEmpty());
		// test equals function
		System.out.println("Is stack a equal to stack test? " + a.equals(test));
		Stack test2 = new Stack();
		test2 = test;
		System.out.println("Is stack test equal to stack test2? " + test.equals(test2));

	}

}