package jpower.core.test;

import jpower.core.Task;
import jpower.core.Worker;
import jpower.core.utils.ThreadUtils;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WorkerTest {
    @Test
    public void testTaskExecution() {
        final boolean[] didWork = {false};
        Worker worker = new Worker();
        worker.start();
        worker.addTask(new Task() {
            @Override
            public void execute() {
                didWork[0] = true;
            }
        });
        ThreadUtils.sleep(100);
        while (worker.isWorking());
        assertTrue("Task was executed on Worker.", didWork[0]);
    }
}