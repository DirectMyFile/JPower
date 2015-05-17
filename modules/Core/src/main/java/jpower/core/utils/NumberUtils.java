package jpower.core.utils;

public class NumberUtils {
   public static int sum(int... numbers) {
      int total = 0;
      for (int number : numbers) {
         total += number;
      }
      return total;
   }

   public static double sum(double... numbers) {
      double total = 0.0;
      for (double number : numbers) {
         total += number;
      }
      return total;
   }

   public static long sum(long... numbers) {
      long total = 0;
      for (long number : numbers) {
         total += number;
      }
      return total;
   }

   public static short sum(short... numbers) {
      short total = 0;
      for (short number : numbers) {
         total += number;
      }
      return total;
   }

   public static byte sum(byte... bytes) {
      byte total = 0;
      for (byte b : bytes) {
         total += b;
      }
      return total;
   }
}
