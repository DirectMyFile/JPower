package jpower.core.test;

import org.junit.Test;
import static org.junit.Assert.*;
import jpower.core.utils.*;
import jpower.core.math.*;
import java.math.BigInteger;

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
