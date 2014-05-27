package jpower.core.test;

import jpower.core.Alphabet;
import org.junit.Test;

import static org.junit.Assert.*;

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

   @Test
   public void testLength()
   {
      assertEquals(26, Alphabet.length());
   }

   @Test(expected = IndexOutOfBoundsException.class)
   public void testRangeSingleOutOfBounds()
   {
      Alphabet.getRange(-1);
   }

   @Test(expected = IndexOutOfBoundsException.class)
   public void testRangeDoubleOutOfBounds()
   {
      Alphabet.getRange(-1, -5);
   }

   @Test
   public void testRangeAll()
   {
      char[] range = Alphabet.getRange(0);
      assertEquals(26, range.length);
   }

   @Test
   public void testRangeSingle()
   {
      char[] range = Alphabet.getRange(25);
      assertEquals(1, range.length);
   }

   @Test
   public void testRangeDouble()
   {
      char[] range = Alphabet.getRange(0, 3);
      assertEquals(3, range.length);
   }
}
