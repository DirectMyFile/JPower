package jpower.socket.event;

import jpower.socket.Event;
import jpower.socket.Socket;

public class LineReceivedEvent extends Event {
    private String line;

    public LineReceivedEvent(Socket powerSocket, String line) {
        super(powerSocket);
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public void sendLine(String line) {
        powerSocket.send(line);
    }
}
