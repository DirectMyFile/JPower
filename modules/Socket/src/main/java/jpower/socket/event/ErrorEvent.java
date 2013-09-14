package jpower.socket.event;

import jpower.socket.Event;
import jpower.socket.JPowerSocket;

public class ErrorEvent extends Event {
    private int errorId;
    private Exception exception;

    public ErrorEvent(int errorId, Exception exception, JPowerSocket powerSocket) {
        super(powerSocket);
        this.errorId = errorId;
        this.exception = exception;
    }

    public int getErrorId() {
        return errorId;
    }

    public Exception getException() {
        return exception;
    }

    public JPowerSocket getPowerSocket() {
        return powerSocket;
    }
}
