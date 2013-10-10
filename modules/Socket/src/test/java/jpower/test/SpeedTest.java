package jpower.test;

import jpower.socket.EventHandler;
import jpower.socket.Socket;
import jpower.socket.SocketBuilder;
import org.junit.Test;

public class SpeedTest {
    @Test
    public void eventHandler() {
        EventHandler handler = new EventHandler();
    }

    @Test
    public void jpowerSocketBuilder() {
        SocketBuilder builder = new SocketBuilder();
        builder.setEventHandler(new EventHandler());
        builder.setHost("localhost");
        builder.setPort(1000);
        Socket powerSocket = builder.create();
    }
}
