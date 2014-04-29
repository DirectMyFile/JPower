package jpower.core.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils
{
   public static String random(int length)
   {
      char[] chars = new char[length];
      for (int i = 0; i < chars.length; i++)
      {
         chars[i] = CharUtils.randomUppercase();
      }
      return new String(chars);
   }

   public static String[] random(int stringLength, int count)
   {
      String[] strings = new String[count];
      for (int i = 0; i < strings.length; i++)
      {
         strings[i] = random(stringLength);
      }
      return strings;
   }

   public static List<String> tokenizeByDot(String input)
   {
      return tokenize(input, '.');
   }

   public static List<String> words(String input)
   {
      return tokenize(input, ' ');
   }

   public static List<String> tokenize(String input, char by)
   {
      return tokenize(input, by, false);
   }

   public static List<String> tokenize(String input, char by, boolean removeEmpty)
   {
      List<String> parts = new ArrayList<>();
      StringBuilder builder = new StringBuilder();
      char[] chars = input.toCharArray();
      for (int i = 0; i < chars.length; i++) {
         char c = chars[i];
         if (c == by)
         {
            parts.add(builder.toString());
            builder.setLength(0);
            builder.trimToSize();
         } else {
            builder.append(c);
         }
         if (i == chars.length - 1) {
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
