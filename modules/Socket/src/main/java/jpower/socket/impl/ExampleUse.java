package jpower.socket.impl;

import jpower.socket.Socket;
import jpower.socket.SocketBuilder;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

public class ExampleUse {
    private static Socket socket;

    public static void main(String[] args) {
        ExampleEventHandler eventHandler = new ExampleEventHandler();
        socket = new SocketBuilder().setHost("irc.esper.net").setPort(6667).setEventHandler(eventHandler).create();
        socket.setLogLevel(Level.ALL);
        socket.getLogger().addHandler(new ConsoleHandler());
        socket.connect();
    }

    public static Socket getSocket() {
        return socket;
    }
}