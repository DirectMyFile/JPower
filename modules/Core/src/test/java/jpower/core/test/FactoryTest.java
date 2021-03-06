package jpower.core.test;

import jpower.core.TaskFactory;
import org.junit.Test;

import static org.junit.Assert.fail;

@SuppressWarnings("TypeMayBeWeakened")
public class FactoryTest {
   private int count;

   @Test
   public void testTaskFactory() {
      final boolean[] checks = new boolean[]{false, false};
      TaskFactory factory = new TaskFactory(() -> {
         checks[count] = true;
         count++;
      });
      factory.create().execute();
      factory.create().execute();
      if (!(checks[0] && checks[1])) {
         fail("Task Factory failed to recreate the task.");
      }
   }
}
