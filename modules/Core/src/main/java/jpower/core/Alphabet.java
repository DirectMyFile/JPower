package jpower.core;

import java.util.Arrays;

/**
 * Represents the English alphabet.
 * Contains various utilities to manipulate it.
 */
public class Alphabet {

   private static final char[] ALPHABET =
           {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                   'w', 'x', 'y', 'z'};

   private static char[] UPPERCASE;

   /**
    * Gets the Alphabet
    *
    * @return alphabet
    */
   public static char[] get() {
      return ALPHABET;
   }

   /**
    * Gets all letter as Uppercase
    *
    * @return uppercase alphabet
    */
   public static char[] uppercase() {
      /* Lazy Initialize Uppercase Letters */
      if (UPPERCASE == null) {
         char[] uppercase = new char[ALPHABET.length];
         for (int i = 0; i < ALPHABET.length; i++) {
            uppercase[i] = Character.toUpperCase(ALPHABET[i]);
         }
         UPPERCASE = uppercase;
      }
      return UPPERCASE;
   }

   /**
    * Gets all Letters as Lowercase
    *
    * @return lowercase alphabet
    */
   public static char[] lowercase() {
      return get();
   }

   /**
    * Gets the letter at the specified index
    *
    * @param index index
    * @return letter
    */
   public static char get(int index) {
      if (index > length() || index < length()) {
         throw new IndexOutOfBoundsException();
      }
      return ALPHABET[index];
   }

   /**
    * Gets the length of the Alphabet (always 26)
    *
    * @return length of alphabet
    */
   public static int length() {
      return ALPHABET.length;
   }

   /**
    * Gets a range of letters starting at the specified index
    *
    * @param index index
    * @return range
    */
   public static char[] getRange(int index) {
      if (index < 0 || index > ALPHABET.length) {
         throw new IndexOutOfBoundsException();
      }
      return Arrays.copyOfRange(ALPHABET, index, ALPHABET.length);
   }

   /**
    * Gets a range of letters
    *
    * @param firstIndex first index
    * @param lastIndex  last index
    * @return range
    */
   public static char[] getRange(int firstIndex, int lastIndex) {
      if (firstIndex < 0 || firstIndex > ALPHABET.length || lastIndex < 0 || lastIndex > ALPHABET.length) {
         throw new IndexOutOfBoundsException();
      }
      return Arrays.copyOfRange(ALPHABET, firstIndex, lastIndex);
   }
}
