package jpower.core.utils;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Scans a String for Patterns
 */
public class StringScanner
{
   private Scanner scanner;

   /**
    * Creates a new String Scanner
    *
    * @param str input string
    */
   public StringScanner(String str)
   {
      scanner = new Scanner(str);
   }

   /**
    * Find the next string matching the pattern
    * @param pattern pattern
    * @return matched
    */
   public String scan(String pattern)
   {
      return scan(Pattern.compile(pattern));
   }

   /**
    * Find the next string matching the pattern
    * @param pattern pattern
    * @return matched
    */
   public String scan(Pattern pattern)
   {
      return scanner.hasNext(pattern) ? scanner.next(pattern) : null;
   }

   /**
    * Skips over the specified pattern
    *
    * @param pattern pattern
    */
   public void skip(Pattern pattern)
   {
      scanner.skip(pattern);
   }

   /**
    * Skips over the specified pattern
    *
    * @param pattern pattern
    */
   public void skip(String pattern)
   {
      scanner.skip(pattern);
   }

   /**
    * Skips over the specified pattern until it no longer matches
    *
    * @param pattern pattern
    */
   @SuppressWarnings("StatementWithEmptyBody")
   public void skipUntil(String pattern)
   {
      while (scan(pattern) != null)
      {
         ;
      }
   }

   /**
    * Skips over the specified pattern until it no longer matches
    *
    * @param pattern pattern
    */
   @SuppressWarnings("StatementWithEmptyBody")
   public void skipUntil(Pattern pattern)
   {
      while (scan(pattern) != null)
      {
         ;
      }
   }
}
