package jpower.core.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * String Utilities
 */
public class StringUtils
{
   /**
    * Creates a Random String
    *
    * @param length length of string
    * @return random string
    */
   public static String random(int length)
   {
      char[] chars = new char[length];
      for (int i = 0; i < chars.length; i++)
      {
         chars[i] = CharUtils.randomUppercase();
      }
      return new String(chars);
   }

   /**
    * Creates multiple random strings
    *
    * @param stringLength length of each string
    * @param count        number of strings to generate
    * @return array of output strings
    */
   public static String[] random(int stringLength, int count)
   {
      String[] strings = new String[count];
      for (int i = 0; i < strings.length; i++)
      {
         strings[i] = random(stringLength);
      }
      return strings;
   }

   /**
    * Tokenizes the input by '.'
    *
    * @param input input string
    * @return list of strings
    */
   public static List<String> tokenizeByDot(String input)
   {
      return tokenize(input, '.');
   }

   /**
    * Tokenizes the input by ' ' (aka words)
    *
    * @param input input string
    * @return list of strings
    */
   public static List<String> words(String input)
   {
      return tokenize(input, ' ');
   }

   /**
    * Tokenizes a String by the specified character
    *
    * @param input input string
    * @param by    character to tokenize by
    * @return list of strings
    */
   public static List<String> tokenize(String input, char by)
   {
      return tokenize(input, by, false);
   }

   /**
    * Tokenizes a String by the specified character, removing empty string if specified
    *
    * @param input       input string
    * @param by          character to tokenize by
    * @param removeEmpty should remove empty strings
    * @return list of strings
    */
   public static List<String> tokenize(String input, char by, boolean removeEmpty)
   {
      List<String> parts = new ArrayList<>();
      StringBuilder builder = new StringBuilder();
      char[] chars = input.toCharArray();
      for (int i = 0; i < chars.length; i++)
      {
         char c = chars[i];
         if (c == by)
         {
            parts.add(builder.toString());
            builder.setLength(0);
            builder.trimToSize();
         }
         else
         {
            builder.append(c);
         }
         if (i == chars.length - 1)
         {
            parts.add(builder.toString());
            builder.setLength(0);
            builder.trimToSize();
         }
      }
      if (removeEmpty)
      {
         parts.removeIf(String::isEmpty);
      }
      return parts;
   }
}
