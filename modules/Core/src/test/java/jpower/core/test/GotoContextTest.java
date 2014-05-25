package jpower.core.test;

import org.junit.Test;
import static org.junit.Assert.*;

import jpower.core.Wrapper;
import jpower.core.GotoContext;

public class GotoContextTest
{
   @Test
   public void testExecution()
   {
      GotoContext ctx = GotoContext.make();
      Wrapper<Integer> number = Wrapper.of(0);
      ctx.define("increment", () -> number.set(number.get() + 1));
      ctx.run("increment");
      assertEquals(1, number.get().intValue());
   }
}
