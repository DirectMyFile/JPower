package jpower.socket.event;

import jpower.socket.Event;
import jpower.socket.Socket;

public class DisconnectEvent extends Event {

    public DisconnectEvent(Socket powerSocket) {
        super(powerSocket);
    }
}
