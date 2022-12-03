package practice.kadai22sep7th.exceptionhandler;

public class SameAsCurrentAirportException extends RuntimeException {

    public SameAsCurrentAirportException() {
        super();
    }

    public SameAsCurrentAirportException(String message, Throwable cause) {
        super(message, cause);
    }

    public SameAsCurrentAirportException(String message) {
        super(message);
    }

    public SameAsCurrentAirportException(Throwable cause) {
        super(cause);
    }

}
