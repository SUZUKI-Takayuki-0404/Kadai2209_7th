package practice.kadai22sep7th.exceptionhandelers;

public class DuplicateAirportCodeException extends Exception {

    public DuplicateAirportCodeException() {
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
