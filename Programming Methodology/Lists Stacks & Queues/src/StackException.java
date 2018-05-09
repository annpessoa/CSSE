
public class StackException extends RuntimeException {

	public StackException() {
		super("Something bad happened in stack.");
	}

	public StackException(String message) {
		super(message);
	}
}
