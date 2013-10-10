package jpower.socket.impl;

import jpower.socket.Event;
import jpower.socket.EventHandler;
import jpower.socket.Socket;
import jpower.socket.event.*;

public class ExampleEventHandler extends EventHandler {
    @Override
    public void onLineReceived(LineReceivedEvent event) {
        System.out.println("Received: " + event.getLine());
    }

    @Override
    public void onConnect(ConnectEvent event) {
        int loopCount = 0;
        Socket socket = event.getSocket();
        socket.send("");
    }

    @Override
    public void onDisconnect(DisconnectEvent event) {
        System.out.println("Socket Disconnected.");
        event.getSocket().connect();
    }

    @Override
    public void onLineSent(LineSentEvent event) {
       System.out.println("Sent: " + event.getLine());
    }

    @Override
    public void onError(ErrorEvent event) {
        System.out.println("Error: " + event.getException().getClass().getName());
        event.getException().printStackTrace();
    }

    @Override
    public void onEvent(Event event) {

    }
}
