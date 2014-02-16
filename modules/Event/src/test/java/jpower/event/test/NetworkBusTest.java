package jpower.event.test;

import jpower.core.utils.ThreadUtils;
import jpower.event.ClientEventBus;
import jpower.event.EventHandler;
import jpower.event.ServerEventBus;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class NetworkBusTest {

    private boolean worked = false;

    private ServerEventBus server;

    @Before
    public void prepare() {
        try {
            server = new ServerEventBus("127.0.0.1", 46839);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testServerClientInteraction() {
        try {
            server.start();
            server.register(this);
            ClientEventBus client = new ClientEventBus("127.0.0.1", 46839);
            client.connect();
            client.post(new TestEvent());
            ThreadUtils.sleep(500);
            assertTrue(worked);
            worked = false;
            server.unregister(this);
            client.register(this);
            server.post(new TestEvent());
            ThreadUtils.sleep(500);
            assertTrue(worked);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void handleEvent(TestEvent event) {
        worked = event.getPayload().equals("Success");
    }
}
