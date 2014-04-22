package jpower.core;

import java.util.Arrays;

/**
 * Represents the English alphabet.
 * Contains various utilities to manipulate it.
 */
public class Alphabet
{

   private static final char[] ALPHABET =
      {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
       'w', 'x', 'y', 'z'};

   public static char[] get()
   {
      return ALPHABET;
   }

   public static char[] uppercase()
   {
      char[] uppercase = new char[ALPHABET.length];
      for (int i = 0; i < ALPHABET.length; i++)
      {
         uppercase[i] = Character.toUpperCase(ALPHABET[i]);
      }
      return uppercase;
   }

   public static char[] lowercase()
   {
      return get();
   }

   public static char get(int index)
   {
      return ALPHABET[index];
   }

   public static int length()
   {
      return ALPHABET.length;
   }

   public static char[] getRange(int index)
   {
      if (index < 0 || index > ALPHABET.length)
      {
         throw new IndexOutOfBoundsException();
      }
      return Arrays.copyOfRange(ALPHABET, index, ALPHABET.length);
   }

   public static char[] getRange(int firstIndex, int lastIndex)
   {
      if (firstIndex < 0 || firstIndex > ALPHABET.length || lastIndex < 0 || lastIndex > ALPHABET.length)
      {
         throw new IndexOutOfBoundsException();
      }
      return Arrays.copyOfRange(ALPHABET, firstIndex, lastIndex);
   }
}