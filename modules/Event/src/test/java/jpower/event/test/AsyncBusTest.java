package jpower.event.test;

import jpower.core.utils.ThreadUtils;
import jpower.event.AsyncEventBus;
import jpower.event.EventHandler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AsyncBusTest {
   private int worked;

   @EventHandler
   public void handleTestEvent(TestEvent event) {
      if (event.getPayload().equals("Success")) {
         worked = 1;
      }
   }

   @Test
   public void testAsyncExecution() {
      AsyncEventBus eventBus = new AsyncEventBus();
      eventBus.register(this);
      eventBus.post(new TestEvent());
      ThreadUtils.sleep(100);
      assertEquals(1, worked);
   }

   @Test
   public void testRegistration() {
      AsyncEventBus eventBus = new AsyncEventBus();
      eventBus.register(this);
      eventBus.post(new TestEvent());
      ThreadUtils.sleep(100);
      assertEquals(1, worked);
      worked = 0;
      eventBus.unregister(this);
      eventBus.post(new TestEvent());
      ThreadUtils.sleep(100);
      assertEquals(0, worked);
   }
}
