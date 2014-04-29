package jpower.core.test;

import jpower.core.ParseException;
import jpower.core.config.Configuration;
import jpower.core.config.Property;
import jpower.core.utils.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Ignore;
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
      assertEquals("# This is a test property\ntest: hello\n", config.toString());
   }

   @Test
   @Ignore("Major Bugs")
   public void testLoad() throws IOException
   {
      testSave();
      config.load(configFile);
      Property test = config.getProperty("test");
      assertNotNull(test);
      assertEquals("hello", test.value());
      assertEquals("This is a test property", test.comments().get(0));
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

   @Test(expected = ParseException.class)
   public void testFailureToParseNoSpaces() throws IOException
   {
      FileUtils.write(configFile, "key:value");
      config.load(configFile);
   }

   @Test(expected = ParseException.class)
   public void testFailureToParseRandomStuff() throws IOException
   {
      FileUtils.write(configFile, "dlfjkasl;djf;sldnvcmxn,e:dj:fjhds:fhfhfhueudjjf:\nfjfjdskl\n\fdsjfjs\n\n\n");
      config.load(configFile);
   }

   @Test(expected = ParseException.class)
   public void testFailureToParseIncorrectFormat() throws IOException
   {
      FileUtils.write(configFile, "key=value");
      config.load(configFile);
   }

   @SuppressWarnings("ResultOfMethodCallIgnored")
   @After
   public void cleanup()
   {
      configFile.delete();
   }
}
