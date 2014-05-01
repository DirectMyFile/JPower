package jpower.core.test;

import jpower.core.Timer;
import jpower.core.Wrapper;
import jpower.core.utils.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StringUtilsTest
{
   @Test
   public void testTokenizeDefault()
   {
      assertEquals(new ArrayList<String>()
      {{
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

   @Test
   public void testTokenizeSpeed()
   {
      Wrapper<List<String>> lettersList = Wrapper.of(null);
      Wrapper<String[]> lettersArray = Wrapper.of(null);
      long tokenized = Timer.timeOf(() -> lettersList.set(StringUtils.tokenize("A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P", '|')));
      long split = Timer.timeOf(() -> lettersArray.set("A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P".split("\\|")));
      System.out.println("Tokenized to: " + lettersList.get().toString());
      System.out.println("Tokenizer took: " + tokenized + "ms");
      System.out.println("Tokenized to: " + Arrays.toString(lettersArray.get()));
      System.out.println("Split took: " + split + "ms");
      assertArrayEquals(lettersList.get().toArray(), lettersArray.get());
      boolean decent = tokenized < split + 2 || tokenized > split + 2;
      assertTrue(decent);
   }
}
