package jpower.core.test;

import jpower.core.config.Configuration;
import jpower.core.config.Property;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    private Configuration config;

    @Before
    public void prepare() {
        config = new Configuration();
    }

    @Test
    public void testSave() throws IOException {
        Property test = config.set("test", "hello");
        test.addComment("This is a test property");
        config.save(new File("test.cfg"));
    }

    @Test
    public void testLoad() throws IOException {
        testSave();
        config.load(new File("test.cfg"));
        Property test = config.getProperty("test");
        assertEquals("hello", test.value());
        assertEquals("This is a test property", test.comments().get(0));
        new File("test.cfg").deleteOnExit();
    }
}
