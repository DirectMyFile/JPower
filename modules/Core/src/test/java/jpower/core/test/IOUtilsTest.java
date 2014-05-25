package jpower.core.test;

import jpower.core.utils.ArrayUtils;
import jpower.core.utils.IOUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IOUtilsTest
{
   @Test
   public void testResourceToString()
   {
      String version = IOUtils.getResourceAsString(getClass(), "jpower/core/release.properties");
      assertNotNull(version, "IOUtils.getResourceAsString() failed");
   }

   @Test
   public void testReadLines() throws IOException
   {
      BufferedReader input = new BufferedReader(new StringReader("Hello\nWorld"));
      List<String> expected = ArrayUtils.toList(new String[] {
              "Hello",
              "World"
      });
      List<String> actual = IOUtils.readLines(input);
      assertEquals(expected, actual);
   }
}
