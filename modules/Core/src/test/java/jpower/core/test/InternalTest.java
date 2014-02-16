package jpower.core.test;

import jpower.core.internal.PowerInternalSystem;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;

public class InternalTest {
    @Test
    public void testAllClasses() {
        Class[] classes = PowerInternalSystem.getLoadedClasses(getClass().getClassLoader());
        assertNotNull(classes);
    }
}
