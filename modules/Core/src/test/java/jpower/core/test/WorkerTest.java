package jpower.core.test;

import jpower.core.MultiTask;
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

    @Test
    public void testMultiTaskExecution() {
        final boolean[] didWork = {false, false};
        Worker worker = new Worker();
        worker.start();
        worker.addTask(new MultiTask(new Task() {
            @Override
            public void execute() {
                didWork[0] = true;
            }
        }, new Task() {
            @Override
            public void execute() {
                didWork[1] = true;
            }
        }));
        ThreadUtils.sleep(100);
        while (worker.isWorking());
        assertTrue("Both tasks were executed on Worker.", didWork[0] && didWork[1]);
    }
}