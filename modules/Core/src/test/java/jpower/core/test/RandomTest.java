package jpower.core.test;

import jpower.core.utils.ArrayUtils;
import jpower.core.utils.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RandomTest {
   @Test
   public void testStringRandomness() {
      int times = 50;
      int count = 0;
      while (count < times) {
         String[] strings = StringUtils.random(50, 20);
         assertFalse(ArrayUtils.containsDuplicates(strings));
         count++;
      }
   }

   @Test
   public void testStringLength() {
      String random = StringUtils.random(50);
      assertEquals(50, random.length());
   }
}
