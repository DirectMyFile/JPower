package jpower.socket.event;

import jpower.socket.Event;
import jpower.socket.JPowerSocket;

public class DisconnectEvent extends Event {

    public DisconnectEvent(JPowerSocket powerSocket) {
        super(powerSocket);
    }
}
