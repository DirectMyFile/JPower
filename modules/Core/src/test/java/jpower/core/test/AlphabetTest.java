package jpower.core.test;

import jpower.core.Alphabet;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class AlphabetTest
{
   @Test
   public void testUppercase()
   {
      char[] uppercase = Alphabet.uppercase();
      for (char letter : uppercase)
      {
         assertFalse(Character.isLowerCase(letter));
      }
   }

   @Test
   public void testLowercase()
   {
      char[] lowercase = Alphabet.lowercase();
      for (char letter : lowercase)
      {
         assertFalse(Character.isUpperCase(letter));
      }
   }
}
