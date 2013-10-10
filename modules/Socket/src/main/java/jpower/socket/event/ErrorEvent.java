package jpower.socket.event;

import jpower.socket.Event;
import jpower.socket.Socket;

public class ErrorEvent extends Event {
    private int errorId;
    private Exception exception;

    public ErrorEvent(int errorId, Exception exception, Socket powerSocket) {
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

    public Socket getPowerSocket() {
        return powerSocket;
    }
}
