package jpower.core.test;

import jpower.core.math.PowerMath;
import jpower.core.utils.ArrayUtils;
import jpower.core.utils.ListUtils;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class PowerMathTest
{
   @Test
   public void testFibonacciTen()
   {
      int[] expected = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34};
      BigInteger[] ints = PowerMath.fibonacci(10);
      ListUtils.forEach(ArrayUtils.toList(ints), (a, i) -> assertEquals(("" + expected[i]), a.toString()));
   }
}
