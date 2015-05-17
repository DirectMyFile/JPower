package jpower.core.test;

import jpower.core.ConditionalExecutor;
import jpower.core.Wrapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConditionTest {
   @Test
   public void testUntil() {
      Wrapper<Integer> count = new Wrapper<>(0);

      new ConditionalExecutor(() -> {
         count.set(count.get() + 1);
      }).until(() -> count.get() == 5);
      assertEquals(5, count.get().intValue());
   }

   @Test
   public void testWhen() {
      Wrapper<Integer> count = new Wrapper<>(0);

      new ConditionalExecutor(() -> {
         count.set(count.get() + 1);
      }).when(() -> count.get() != 5);
      assertEquals(5, count.get().intValue());
   }
}
