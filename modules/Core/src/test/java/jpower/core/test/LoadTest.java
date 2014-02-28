package jpower.core.test;

import jpower.core.Repeater;
import jpower.core.Worker;
import jpower.core.utils.ThreadUtils;
import org.junit.Before;
import org.junit.Test;

public class LoadTest {
    private Worker worker;

    @Before
    public void prepare() {
        worker = new Worker(50);
        worker.start();
    }

    @Test
    public void testWorkerLoad() {
        new Repeater(count -> worker.addTask(() -> ThreadUtils.sleep(20))).run(500);
    }
}
