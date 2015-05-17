package jpower.core.test;

import jpower.core.AdvancedService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AdvancedServiceTest {
   private AdvancedService service;
   private boolean state;

   @Before
   public void prepare() {
      service = new AdvancedService() {
         @Override
         public void start() {
            state = true;
         }

         @Override
         public void stop() {
            state = false;
         }
      };
   }

   @Test
   public void test() throws Exception {
      assertFalse(state);
      service.start();
      assertTrue(state);
      service.stop();
      assertFalse(state);
      service.restart();
      assertTrue(state);
      service.stop();
      assertFalse(state);
      service.start();
      assertTrue(state);
      service.restart();
      assertTrue(state);
   }
}
