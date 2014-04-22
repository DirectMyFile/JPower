package jpower.core;

import jpower.core.utils.IOUtils;

import java.util.regex.Pattern;

/**
 * JPower Release Information
 */
public final class JPower
{
   private static final Pattern SPLIT_BY_DOT = Pattern.compile("\\.");
   private static String VERSION;

   public static String getVersion()
   {
      if (VERSION == null)
      {
         String v = IOUtils.getResourceAsString(JPower.class, "release");
         if (v == null || v.equals("${jpowerVersion}"))
         {
            VERSION = "UNKNOWN";
         }
         else
         {
            VERSION = v;
         }
      }
      return VERSION;
   }

   public static String[] getVersionMetadata()
   {
      return SPLIT_BY_DOT.split(getVersion());
   }

   public static void main(String[] args)
   {
      System.out.println("JPower v" + getVersion());
   }
}