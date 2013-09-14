package jpower.socket;

import jpower.socket.event.ConnectEvent;
import jpower.socket.event.DisconnectEvent;
import jpower.socket.event.ErrorEvent;
import jpower.socket.event.LineReceivedEvent;
import jpower.socket.event.LineSentEvent;

public class EventHandler {
    @Deprecated
    public void onLineReceivedEvent(LineReceivedEvent event) {

    }

    @Deprecated
    public void onConnectEvent(ConnectEvent event) {

    }

    @Deprecated
    public void onErrorEvent(ErrorEvent event) {
        event.getException().printStackTrace();
    }

    @Deprecated
    public void onDisconnectedEvent(DisconnectEvent event) {

    }

    @Deprecated
    public void onLineSentEvent(LineSentEvent event) {

    }

    public void onLineSent(LineSentEvent event) {

    }

    public void onLineReceived(LineReceivedEvent event) {

    }

    public void onConnect(ConnectEvent event) {

    }

    public void onDisconnect(DisconnectEvent event) {

    }

    public void onError(ErrorEvent event) {
        event.getException().printStackTrace();
    }

    public void onEvent(Event event) {

    }
}
