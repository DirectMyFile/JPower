package jpower.socket.event;

import jpower.socket.Event;
import jpower.socket.JPowerSocket;

public class LineSentEvent extends Event {
    private String line;

    public LineSentEvent(JPowerSocket powerSocket, String line) {
        super(powerSocket);
        this.line = line;
    }

    public String getLine() {
        return line;
    }
}
