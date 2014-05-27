package jpower.core.test;

import jpower.core.Wrapper;
import jpower.core.utils.CallUtils;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("ThrowableResultOfMethodCallIgnored")
public class CallUtilsTest
{
   @Test
   public void testTryCatch()
   {
      Wrapper<Throwable> caught = new Wrapper<>(null);
      CallUtils.tryCatch(() -> {
         throw new Exception("This should be caught");
      }, caught::set);
      assertNotNull(caught.get());
      assertEquals("This should be caught", caught.get().getMessage());
   }

   @Test
   public void testCallIgnoreExceptions()
   {
      CallUtils.callIgnoreExceptions(() -> {
         throw new Exception("SHOULD BE IGNORED");
      });
   }

   @Test
   public void testInvokerOf() throws Exception
   {
      Wrapper<Boolean> worked = Wrapper.of(false);
      Runnable action = () -> worked.set(true);
      CallUtils.invokerOf(action).invokeMethod("run");
      assertTrue(worked.get().booleanValue());
   }
}
