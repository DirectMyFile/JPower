package jpower.socket.event;

import jpower.socket.Event;
import jpower.socket.Socket;

public class ConnectEvent extends Event {

    public ConnectEvent(Socket powerSocket) {
        super(powerSocket);
    }
}
