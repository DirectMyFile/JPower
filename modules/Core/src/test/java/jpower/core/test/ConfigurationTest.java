package jpower.core.test;

import jpower.core.config.Configuration;
import jpower.core.config.Property;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ConfigurationTest
{
   private Configuration config;

   @Rule
   public TemporaryFolder tempFolder = new TemporaryFolder();

   public File configFile;

   @Before
   public void prepare() throws IOException
   {
      configFile = tempFolder.newFile("test.cfg");
      config = new Configuration();
   }

   @Test
   public void testSave() throws IOException
   {
      Property test = config.set("test", "hello");
      test.addComment("This is a test property");
      assertEquals("# This is a test property" + System.lineSeparator() + "test: hello" + System.lineSeparator(),
                   test.toString());
      config.save(configFile);
   }

   @Test
   public void testLoad() throws IOException
   {
      testSave();
      config.load(configFile);
      Property test = config.getProperty("test");
      assertEquals("hello", test.value());
      assertEquals("This is a test property", test.comments().get(0));
      configFile.deleteOnExit();
   }
}
