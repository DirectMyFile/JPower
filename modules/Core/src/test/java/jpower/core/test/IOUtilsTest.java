package jpower.core.test;

import jpower.core.annotation.Disabled;
import jpower.core.utils.IOUtils;

import static org.junit.Assert.assertNotNull;

public class IOUtilsTest {
    @Disabled
    public void testResourceToString() {
        String version = IOUtils.getResourceAsString(getClass(), "jpower/core/release");
        assertNotNull(version);
    }
}
