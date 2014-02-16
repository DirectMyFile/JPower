package jpower.event.test;

import jpower.core.Wrapper;
import jpower.event.EventBus;
import jpower.event.GlobalEventBus;
import jpower.event.Handler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GlobalBusTest {
    private EventBus busA;
    private EventBus busB;
    private EventBus busC;

    @Before
    public void prepare() {
        busA = new EventBus();
        busB = new EventBus();
        busC = new EventBus();
    }

    @Test
    public void testEventDelegation() {
        TestEvent testEvent = new TestEvent();
        Wrapper<Boolean> worked = new Wrapper<>(false);
        GlobalEventBus.get().register((Handler<TestEvent>) event -> {
            worked.set(event.getPayload().equals("Success"));
        });
        busA.post(testEvent);
        assertTrue(worked.set(false));
        busB.post(testEvent);
        assertTrue(worked.set(false));
        busC.post(testEvent);
        assertTrue(worked.set(false));
    }
}
