package jpower.core.test;

import jpower.core.utils.IOUtils;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class IOUtilsTest {
    @Test
    public void testResourceToString() {
        String version = IOUtils.getResourceAsString(getClass(), "jpower/core/release");
        assertNotNull(version);
    }
}
