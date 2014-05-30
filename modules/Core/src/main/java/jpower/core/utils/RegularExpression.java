package jpower.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression
{
   public static StringScanner scanner(String str)
   {
      return new StringScanner(str);
   }

   public static boolean matches(Pattern pattern, String input)
   {
      return pattern.matcher(input).matches();
   }

   public static String[] captures(Pattern pattern, String input)
   {
      List<String> captures = new ArrayList<>();
      Matcher matcher = pattern.matcher(input);
      for (int i = 0; i < matcher.groupCount(); i++)
      {
         captures.add(matcher.group(i));
      }
      return captures.toArray(new String[captures.size()]);
   }

   public static String[] captures(String pattern, String input)
   {
      return captures(Pattern.compile(pattern), input);
   }
}
