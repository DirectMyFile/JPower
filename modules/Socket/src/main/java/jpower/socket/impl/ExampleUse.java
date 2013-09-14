package jpower.socket.impl;

import jpower.socket.JPowerSocket;
import jpower.socket.JPowerSocketBuilder;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

public class ExampleUse {
    private static JPowerSocket powerSocket;

    public static void main(String[] args) {
        ExampleEventHandler eventHandler = new ExampleEventHandler();
        powerSocket = new JPowerSocketBuilder().setHost("localhost").setPort(10008).setEventHandler(eventHandler).create();
        powerSocket.setLogLevel(Level.ALL);
        powerSocket.getLogger().addHandler(new ConsoleHandler());
        powerSocket.connect();
    }

    public static JPowerSocket getPowerSocket() {
        return powerSocket;
    }
}