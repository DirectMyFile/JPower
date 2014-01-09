package jpower.core.test;

import jpower.core.utils.NetUtils;
import jpower.core.utils.ThreadUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UtilsTest {
    @Test
    public void testNetPing() {
        assertTrue(NetUtils.ping("127.0.0.1"));
    }

    @Test
    public void testThreadSleep() {
        int time = 1000;
        long begin = System.currentTimeMillis();
        ThreadUtils.sleep(time);
        long end = System.currentTimeMillis();
        long slept = end - begin;
        assertEquals(time, slept);
    }
}
