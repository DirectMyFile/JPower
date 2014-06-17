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
    * Gets the largest number in the given numbers
    *
    * @param numbers input
    * @return largest number
    */
   public static int largest(int... numbers)
   {
      boolean first = true;
      int highest = 0;
      for (int n : numbers)
      {
         if (first)
         {
            highest = n;
            first = false;
            continue;
         }
         if (n > highest)
         {
            highest = n;
         }
      }
      return highest;
   }

   /**
    * Gets the smallest number in the given numbers
    *
    * @param numbers input
    * @return smallest number
    */
   public static int smallest(int... numbers)
   {
      boolean first = true;
      int lowest = 0;
      for (int n : numbers)
      {
         if (first)
         {
            lowest = n;
            first = false;
         }
         else if (n < lowest)
         {
            lowest = n;
         }
      }
      return lowest;
   }
}
