package jpower.core.test;

import jpower.core.utils.StringUtils;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest
{
   @Test
   public void testTokenizeDefault()
   {
      assertEquals(new ArrayList<String>() {{
         add("A");
         add("B");
         add("C");
         add("D");
      }}, StringUtils.tokenize("A|B|C|D", '|'));
   }

   @Test
   public void testTokenizeWithRemoveEmptyOption()
   {
      assertEquals(new ArrayList<String>()
      {{
            add("A");
            add("B");
            add("D");
         }}, StringUtils.tokenize("A|B||D", '|', true));
   }
}
