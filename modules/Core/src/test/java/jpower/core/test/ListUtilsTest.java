package jpower.core.test;

import jpower.core.utils.ArrayUtils;
import jpower.core.utils.ListUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListUtilsTest
{
   @Test
   public void testCollectWorks()
   {
      List<String> expected = ArrayUtils.toList(new String[] {
         "HELLO",
         "WORLD"
      });
      List<String> input = new ArrayList<String>() {{
         add("Hello");
         add("World");
      }};
      assertEquals(expected, ListUtils.collect(input, String::toUpperCase));
   }
}
