package practice.kadai22sep7th.exceptionhandler;

public class AirportNotChangedException extends RuntimeException {

    public AirportNotChangedException() {
        super();
    }

    public AirportNotChangedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AirportNotChangedException(String message) {
        super(message);
    }

    public AirportNotChangedException(Throwable cause) {
        super(cause);
    }

}
