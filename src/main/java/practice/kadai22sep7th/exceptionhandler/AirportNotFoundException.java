package practice.kadai22sep7th.exceptionhandler;

public class AirportNotFoundException extends RuntimeException {

    public AirportNotFoundException() {
        super();
    }

    public AirportNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AirportNotFoundException(String message) {
        super(message);
    }

    public AirportNotFoundException(Throwable cause) {
        super(cause);
    }

}
