package jpower.core.test;

import jpower.core.utils.CallUtils;
import org.junit.Test;

public class CallUtilsTest
{
   @Test
   public void testCallIgnoreExceptions()
   {
      CallUtils.callIgnoreExceptions(() -> {
         throw new Exception("SHOULD BE IGNORED");
      });
   }
}
