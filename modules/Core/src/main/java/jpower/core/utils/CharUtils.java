package jpower.core.utils;

import jpower.core.Alphabet;

import java.util.Random;

public class CharUtils {

   public static char[] reverse(char[] chars) {
      return unwrap(ArrayUtils.reverse(wrap(chars)));
   }

   public static char randomUppercase() {
      return Alphabet.uppercase()[new Random().nextInt(26)];
   }

   public static char randomLowercase() {
      return Alphabet.lowercase()[new Random().nextInt(26)];
   }

   public static Character[] wrap(char[] chars) {
      Character[] Chars = new Character[chars.length];
      for (int i = 0; i < Chars.length; i++) {
         Chars[i] = chars[i];
      }
      return Chars;
   }

   public static char[] unwrap(Character[] Chars) {
      char[] chars = new char[Chars.length];
      for (int i = 0; i < Chars.length; i++) {
         chars[i] = Chars[i];
      }
      return chars;
   }
}
