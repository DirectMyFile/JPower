package jpower.core.utils;

import java.util.Scanner;
import java.util.regex.Pattern;

public class StringScanner
{
   private Scanner scanner;

   public StringScanner(String str)
   {
      scanner = new Scanner(str);
   }

   public String scan(String pattern)
   {
      return scanner.next(pattern);
   }

   public String scan(Pattern pattern)
   {
      return scanner.next(pattern);
   }
}
