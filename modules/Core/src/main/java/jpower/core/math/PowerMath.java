package jpower.core.math;

import java.math.BigInteger;

/**
 * Utility class that contains many different
 * math utilities for anything and everything.
 */
public class PowerMath
{

   /**
    * Calculate the fibonacci sequence up to a certain digit.
    *
    * @param count Number of digits you want to calculate
    * @return BigInteger array of numbers
    */
   public static BigInteger[] fibonacci(int count)
   {
      BigInteger[] digits = new BigInteger[count];
      BigInteger last = new BigInteger("0");
      BigInteger current = new BigInteger("1");
      for (int i = 0; i < count; i++)
      {
         digits[i] = last;
         current = last.add(current);
         last = current.subtract(last);
      }
      return digits;
   }
}
