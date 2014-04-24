package jpower.event.test;

import jpower.core.Wrapper;
import jpower.core.utils.ThreadUtils;
import jpower.event.EventBus;
import jpower.event.Handler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LambdaTest
{
   private EventBus eventBus;

   @Before
   public void prepare()
   {
      eventBus = new EventBus();
   }

   @Test
   public void testLambdaSupport()
   {
      Wrapper<Boolean> worked = new Wrapper<>(false);
      eventBus.register((Handler<TestEvent>) event -> worked.set(event.getPayload().equals("Success")));
      eventBus.post(new TestEvent());
      ThreadUtils.sleep(100);
      assertTrue(worked.set(false));
      eventBus.post(new TestEventTwo());
      assertFalse(worked.get());
   }
}
