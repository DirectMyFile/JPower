package jpower.core.test;

import jpower.core.GotoContext;
import jpower.core.Wrapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

   @Test
   public void testChaining()
   {
      GotoContext ctx = GotoContext.make();
      Wrapper<Integer> number = Wrapper.of(0);
      ctx.define("increment", () -> number.set(number.get() + 1));
      ctx.define("decrement", () -> number.set(number.get() - 1));
      ctx.run("increment")
              .run("decrement");
      assertEquals(0, number.get().intValue());
   }
}
