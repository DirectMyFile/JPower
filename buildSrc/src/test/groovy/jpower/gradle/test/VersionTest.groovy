package jpower.gradle.test

import jpower.gradle.Version
import org.junit.Test

class VersionTest {
    @Test
    void testIncrementing() {
        def version = new Version("1.4.0")
        assert (version++).toString() == "1.4.1"
    }
}
