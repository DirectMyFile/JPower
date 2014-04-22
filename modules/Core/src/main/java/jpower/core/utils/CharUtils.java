package jpower.core.utils;

import jpower.core.Alphabet;

import java.util.Random;

public class CharUtils
{
   public static char randomUppercase()
   {
      return Alphabet.uppercase()[new Random().nextInt(26)];
   }

   public static char randomLowercase()
   {
      return Alphabet.lowercase()[new Random().nextInt(26)];
   }
}
