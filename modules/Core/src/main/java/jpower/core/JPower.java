package jpower.core;

import jpower.core.utils.IOUtils;
import jpower.core.utils.StringUtils;

import java.util.List;

/**
 * JPower Release Information
 */
public final class JPower
{
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

   public static List<String> getVersionMetadata()
   {
      return StringUtils.tokenizeByDot(getVersion());
   }

   public static void main(String[] args)
   {
      System.out.println("JPower v" + getVersion());
   }
}
