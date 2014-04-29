package jpower.core.test;

import jpower.core.Repeater;
import jpower.core.Wrapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RepeaterTest
{
   @Test
   public void testRepeatingWorks() {
      Wrapper<Integer> count = new Wrapper<>(0);
      Repeater repeater = new Repeater(i -> {
         count.set(count.get() + 1);
      });
      repeater.run(40);
      assertEquals(40, count.get().intValue());
   }
}
