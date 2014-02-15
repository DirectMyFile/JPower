package jpower.core.test;

import jpower.core.TaskFactory;
import org.junit.Test;

import static org.junit.Assert.fail;

public class FactoryTest {
    private int count = 0;

    @Test
    public void testTaskFactory() {
        final boolean[] checks = new boolean[]{false, false};
        TaskFactory factory = new TaskFactory(() -> {
            checks[count] = true;
            count++;
        });
        factory.newInstance().execute();
        factory.newInstance().execute();
        if (!(checks[0] && checks[1])) {
            fail("Task Factory failed to recreate the task.");
        }
    }
}
