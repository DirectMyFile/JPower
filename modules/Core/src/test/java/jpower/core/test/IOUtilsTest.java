package jpower.core.test;

import jpower.core.utils.IOUtils;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class IOUtilsTest {
    @Test
    @Ignore
    public void testResourceToString() {
        String version = IOUtils.getResourceAsString(getClass(), "jpower/core/release");
        assertNotNull(version);
    }
}
