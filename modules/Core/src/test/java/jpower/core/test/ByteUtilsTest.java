package jpower.core.test;

import jpower.core.utils.ByteUtils;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ByteUtilsTest
{
   @Test
   public void testByteReverse()
   {
      byte[] bytes = {5, 3, 2, 56, 7, 3, 9};
      byte[] reversed = ByteUtils.reverse(bytes);
      assertArrayEquals(new byte[]{9, 3, 7, 56, 2, 3, 5}, reversed);
   }
}
