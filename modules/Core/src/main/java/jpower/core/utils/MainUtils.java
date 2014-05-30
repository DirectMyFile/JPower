package jpower.core.utils;

import jpower.core.opts.CommandLine;
import jpower.core.opts.CommandLineParser;

/**
 * Utilities for main(String[])
 */
public class MainUtils
{
   public static void println(String line)
   {
      System.out.println(line);
   }

   public static void println()
   {
      System.out.println();
   }

   public static void println(Object obj)
   {
      System.out.println(obj);
   }

   public static void print(String input)
   {
      System.out.print(input);
   }

   public static void print(Object obj)
   {
      System.out.print(obj);
   }

   public static void exit(int code)
   {
      System.exit(code);
   }

   public static void exit()
   {
      exit(0);
   }

   public static CommandLine parseArgs(String[] args)
   {
      return CommandLineParser.parse(args);
   }
}
