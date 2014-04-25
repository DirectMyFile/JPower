package jpower.core.test;

import jpower.core.Timer;
import jpower.core.utils.ByteUtils;
import jpower.core.utils.ThreadUtils;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class UtilsTest {
    @Test
    public void testNetPing() throws UnknownHostException {
        // assertTrue(NetUtils.ping("0.0.0.0"));
    }

    @Test
    public void testThreadSleep() {
        int time = 1000;
        int range = 20;
       long slept = Timer.timeOf(() -> ThreadUtils.sleep(time));
        boolean decent = slept < time + range || slept > time + range;
        assertTrue("Expected " + time + "ms of sleep, but got " + slept + "ms of sleep.", decent);
    }

    @Test
    public void testByteReverse() {
        byte[] bytes = {5, 3, 2, 56, 7, 3, 9};
        byte[] reversed = ByteUtils.reverse(bytes);
        assertArrayEquals(new byte[]{9, 3, 7, 56, 2, 3, 5}, reversed);
    }
}
