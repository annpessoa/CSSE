/**
 * This class extends RuntimeException to provide the FindFile class with unique
 * runtime exceptions. This is done by using the superclass's constructor.
 * 
 * @author Anneliese Pessoa
 *
 */
public class FileException extends RuntimeException {

	/*
	 * Default FileException that contains the message:
	 * "An error occured in FindFile0."
	 */
	public FileException() {
		super("An error occured in FindFile.");
	}

	/*
	 * Unique FileException that contains a message more specific that relates
	 * to the error at hand.
	 */
	public FileException(String message) {
		super(message);
	}
}
