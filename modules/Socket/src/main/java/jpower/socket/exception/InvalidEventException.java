package jpower.socket.exception;

public class InvalidEventException extends Exception {

    public InvalidEventException() {
        super();
    }

    public InvalidEventException(String message) {
        super(message);
    }

    public InvalidEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEventException(Throwable cause) {
        super(cause);
    }
}
