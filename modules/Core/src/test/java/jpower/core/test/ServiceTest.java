package jpower.core.test;

import jpower.core.Service;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ServiceTest
{
   private Service service;
   private boolean state;

   @Before
   public void prepare()
   {
      service = new Service()
      {
         @Override
         public void start()
         {
            state = true;
         }

         @Override
         public void stop()
         {
            state = false;
         }
      };
   }

   @Test
   public void test()
   {
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
