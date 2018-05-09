
public class WumpusMountainException extends RuntimeException {

	public WumpusMountainException() {
		super("An error occured in the Wumpus Mountain");
	}

	public WumpusMountainException(String message) {
		super(message);
	}
}
