package jpower.event.test;

import jpower.core.utils.ThreadUtils;
import jpower.event.EventBus;
import jpower.event.EventHandler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BaseBusTest {
   private int worked;

   public static void main(String[] args) {
      new BaseBusTest().testEventExecution();
   }

   @EventHandler
   public void handleTestEvent(TestEvent event) {
      if (event.getPayload().equals("Success")) {
         worked = 1;
      }
   }

   @Test
   public void testEventExecution() {
      EventBus bus = new EventBus();
      bus.register(this);
      bus.post(new TestEvent());
      ThreadUtils.sleep(100);
      assertEquals(1, worked);
   }

   @Test
   public void testRegistration() {
      EventBus eventBus = new EventBus();
      eventBus.register(this);
      eventBus.post(new TestEvent());
      assertEquals(1, worked);
      worked = 0;
      eventBus.unregister(this);
      eventBus.post(new TestEvent());
      assertEquals(0, worked);
   }
}
