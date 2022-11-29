package practice.kadai22sep7th.exceptionhandler;

public class DuplicateAirportCodeException extends Exception {

    public DuplicateAirportCodeException() {
        super();
    }

    public DuplicateAirportCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateAirportCodeException(String message) {
        super(message);
    }

    public DuplicateAirportCodeException(Throwable cause) {
        super(cause);
    }
}
