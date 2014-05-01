package jpower.core.test;

import jpower.core.config.Configuration;
import jpower.core.config.Property;
import jpower.core.utils.FileUtils;
import jpower.core.utils.ListUtils;
import jpower.core.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;
import java.util.Properties;

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
   public void testSaveToFile() throws IOException
   {
      Property test = config.set("test", "hello");
      test.addComment("This is a test property");
      assertEquals("# This is a test property\ntest: hello\n",
              test.toString());
      config.save(configFile);
      assertEquals("# This is a test property\ntest: hello\n", FileUtils.toString(configFile));
   }

   @Test
   public void testLoadFromFile() throws IOException
   {
      testSaveToFile();
      config.load(configFile);
      Property test = config.getProperty("test");
      assertNotNull(test);
      assertEquals("hello", test.value());
      assertEquals("This is a test property", test.comments().get(0));
   }

   @Test
   public void testSaveToWriter() throws IOException
   {
      Writer writer = new StringWriter();
      config.set("letter.a", "Letter A")
         .addComment("This is A of course");
      config.set("letter.b", "Letter B");
      config.save(writer);
      assertEquals("# This is A of course\nletter.a: Letter A\nletter.b: Letter B\n", writer.toString());
   }

   @Test
   public void testLoadFromReader() throws IOException
   {
      Reader reader = new StringReader("# This is A of course\nletter.a: Letter A\nletter.b: Letter B\n");
      config.load(reader);
      assertEquals("Letter A", config.get("letter.a"));
      assertEquals("This is A of course", config.getProperty("letter.a").comments().get(0));
      assertEquals("Letter B", config.get("letter.b"));
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
      List<Property> group = config.group("letter");
      List<String> letters = ListUtils.collect(group, Property::value);
      assertEquals(3, letters.size());
      assertEquals("Letter A", letters.get(0));
      assertEquals("Letter B", letters.get(1));
      assertEquals("Letter C", letters.get(2));
   }

   @Test
   public void testFailureToParseNoSpaces() throws IOException
   {
      FileUtils.write(configFile, "key:value");
      assertNotNull(TestUtils.thrown(() -> config.load(configFile)));
   }

   @Test
   public void testFailureToParseRandomStuff() throws IOException
   {
      FileUtils.write(configFile, "dlfjkasl;djf;sldnvcmxn,e:dj:fjhds:fhfhfhueudjjf:\nfjfjdskl\n\fdsjfjs\n\n\n");
      assertNotNull(TestUtils.thrown(() -> config.load(configFile)));
   }

   @Test
   public void testFailureToParseIncorrectFormat() throws IOException
   {
      FileUtils.write(configFile, "key=value");
      assertNotNull(TestUtils.thrown(() -> config.load(configFile)));
   }

   @Test
   public void testToJavaProperties()
   {
      config.set("letter.a", "Letter A");
      config.set("letter.b", "Letter B");
      config.set("letter.c", "Letter C");
      Properties props = config.toProperties();
      assertEquals(props.getProperty("letter.a"), "Letter A");
      assertEquals(props.getProperty("letter.b"), "Letter B");
      assertEquals(props.getProperty("letter.c"), "Letter C");
   }

   @SuppressWarnings("ResultOfMethodCallIgnored")
   @After
   public void cleanup()
   {
      configFile.delete();
   }
}
