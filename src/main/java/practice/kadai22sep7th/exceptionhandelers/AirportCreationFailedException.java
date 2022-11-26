package practice.kadai22sep7th.exceptionhandelers;

public class AirportCreationFailedException extends Exception {

    public AirportCreationFailedException() {
    }

    public AirportCreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AirportCreationFailedException(String message) {
        super(message);
    }

    public AirportCreationFailedException(Throwable cause) {
        super(cause);
    }
}
