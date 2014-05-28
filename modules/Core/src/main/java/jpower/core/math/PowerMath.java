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

   /**
    * Calculates the Factorial of a number
    *
    * @param number number to get factorial of
    * @return factorial
    */
   public static int factorial(int number)
   {
      return number == 1 ? 1 : number * factorial(number - 1);
   }

   /**
    * Gets the highest number in the given numbers
    *
    * @param numbers input
    * @return maximum number
    */
   public static int maximum(int... numbers)
   {
      boolean first = true;
      int max = 0;
      for (int n : numbers)
      {
         if (first)
         {
            max = n;
            first = false;
            continue;
         }
         if (n > max)
         {
            max = n;
         }
      }
      return max;
   }
}
