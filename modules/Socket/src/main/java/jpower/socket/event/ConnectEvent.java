package jpower.socket.event;

import jpower.socket.Event;
import jpower.socket.JPowerSocket;

public class ConnectEvent extends Event {

    public ConnectEvent(JPowerSocket powerSocket) {
        super(powerSocket);
    }
}
