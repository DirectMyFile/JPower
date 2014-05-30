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
      return scanner.next(pattern);
   }

   /**
    * Find the next string matching the pattern
    * @param pattern pattern
    * @return matched
    */
   public String scan(Pattern pattern)
   {
      return scanner.next(pattern);
   }
}
