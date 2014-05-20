package jpower.event.test;

import jpower.core.utils.ThreadUtils;
import jpower.event.ClientEventBus;
import jpower.event.EventHandler;
import jpower.event.ServerEventBus;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class NetworkBusTest
{

   private boolean worked;

   private ServerEventBus server;

   @Before
   public void prepare() throws IOException
   {
      server = new ServerEventBus("127.0.0.1", 46839);
   }

   @Test
   public void testServerClientInteraction() throws IOException
   {
      server.start();
      server.register(this);
      ClientEventBus client = new ClientEventBus("127.0.0.1", 46839);
      client.connect();
      client.post(new TestEvent());
      ThreadUtils.sleep(1000);
      assertTrue(worked);
      worked = false;
      server.unregister(this);
      client.register(this);
      server.post(new TestEvent());
      ThreadUtils.sleep(1000);
      assertTrue(worked);
   }

   @EventHandler
   public void handleEvent(TestEvent event)
   {
      worked = event.getPayload().equals("Success");
   }
}
