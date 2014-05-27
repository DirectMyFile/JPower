package jpower.core.test;

import jpower.core.utils.TestUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestUtilsTest
{
   @Test
   public void testThrownRuntime()
   {
      Throwable thrown = TestUtils.thrown(() -> {
         throw new RuntimeException("This is a runtime exception");
      });
      assertNotNull(thrown);
      assertTrue(thrown instanceof RuntimeException);
      assertEquals("This is a runtime exception", thrown.getMessage());
   }

   @Test
   public void testThrownChecked()
   {
      Throwable thrown = TestUtils.thrown(() -> {
         throw new Exception("This is an exception");
      });
      assertNotNull(thrown);
      assertTrue(thrown instanceof Exception);
      assertEquals("This is an exception", thrown.getMessage());
   }

   @Test
   public void testThrownNone()
   {
      Throwable thrown = TestUtils.thrown(() -> {
         return;
      });
      assertNull(thrown);
   }
}
