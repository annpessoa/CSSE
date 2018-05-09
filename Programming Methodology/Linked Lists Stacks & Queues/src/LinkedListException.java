/**
 * This class makes use of RuntimeException to throw exceptions that are unique
 * to the linked lists, stacks, and queues.
 * 
 * @author Anneliese Pessoa
 *
 */
public class LinkedListException extends RuntimeException {

	public LinkedListException() {
		super("There was an error in the List");
	}

	public LinkedListException(String message) {
		super(message);
	}
}
