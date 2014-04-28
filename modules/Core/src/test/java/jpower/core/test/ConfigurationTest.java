package jpower.core.test;

import jpower.core.config.Configuration;
import jpower.core.config.Property;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

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

   @Test
   public void testPropertyCreation() throws IOException
   {
      Property test = new Property("test");
      test.addComment("Hello There");
      assertTrue(test.comments().contains("Hello There"));
      assertEquals("test", test.key());
      assertNull(test.value());
      test.set("this is a value");
      assertEquals("this is a value", test.value());
   }

   @Test
   public void testGrouping()
   {
      config.set("letter.a", "Letter A");
      config.set("letter.b", "Letter B");
      config.set("letter.c", "Letter C");
      List<Property> letters = config.group("letter");
      assertEquals(3, letters.size());
      assertEquals("Letter A", letters.get(0).value());
      assertEquals("Letter B", letters.get(1).value());
      assertEquals("Letter C", letters.get(2).value());
   }
}
