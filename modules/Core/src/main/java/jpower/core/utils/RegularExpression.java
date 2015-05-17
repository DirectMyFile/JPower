package jpower.core.utils;

import org.intellij.lang.annotations.Language;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regular Expression Utilities
 */
public class RegularExpression {

   private Pattern pattern;

   /**
    * Constructs a new Regular Expression Wrapper
    *
    * @param pattern pattern
    */
   public RegularExpression(Pattern pattern) {
      this.pattern = pattern;
   }

   /**
    * Constructs a new Regular Expression Wrapper
    *
    * @param pattern pattern
    */
   public RegularExpression(@Language("RegExp") String pattern) {
      this.pattern = Pattern.compile(pattern);
   }

   /**
    * Creates a String Scanner
    *
    * @param str input string
    * @return string scanner
    */
   public static StringScanner scanner(String str) {
      return new StringScanner(str);
   }

   /**
    * Checks if a String Matches a Pattern
    *
    * @param pattern pattern
    * @param input   input string
    * @return true if it matches, otherwise false
    */
   public static boolean matches(Pattern pattern, String input) {
      return pattern.matcher(input).matches();
   }

   /**
    * Gets the Capture Groups for the pattern again the input
    *
    * @param pattern pattern
    * @param input   input string
    * @return array of capture groups
    */
   public static String[] captures(Pattern pattern, String input) {
      List<String> captures = new ArrayList<>();
      Matcher matcher = pattern.matcher(input);
      for (int i = 0; i < matcher.groupCount(); i++) {
         captures.add(matcher.group(i));
      }
      return captures.toArray(new String[captures.size()]);
   }

   /**
    * Gets the Capture Groups for the pattern again the input
    *
    * @param pattern pattern
    * @param input   input string
    * @return array of capture groups
    */
   public static String[] captures(@Language("RegExp") String pattern, String input) {
      return captures(Pattern.compile(pattern), input);
   }

   /**
    * Gets the Capture Groups for the pattern again the input
    *
    * @param input input string
    * @return array of capture groups
    */
   public String[] captures(String input) {
      return captures(pattern, input);
   }

   /**
    * Checks if a String Matches the pattern
    *
    * @param input input string
    * @return true if it matches, otherwise false
    */
   public boolean matches(String input) {
      return matches(pattern, input);
   }
}
