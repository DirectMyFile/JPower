package jpower.test;

import jpower.socket.EventHandler;
import jpower.socket.JPowerSocket;
import jpower.socket.JPowerSocketBuilder;
import org.junit.Test;

public class SpeedTest {
    @Test
    public void eventHandler() {
        EventHandler handler = new EventHandler();
    }

    @Test
    public void jpowerSocketBuilder() {
        JPowerSocketBuilder builder = new JPowerSocketBuilder();
        builder.setEventHandler(new EventHandler());
        builder.setHost("localhost");
        builder.setPort(1000);
        JPowerSocket powerSocket = builder.create();
    }
}
