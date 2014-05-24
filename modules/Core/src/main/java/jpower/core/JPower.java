package jpower.core;

import jpower.core.utils.IOUtils;
import jpower.core.utils.StringUtils;

import java.util.List;
import java.util.Properties;
import java.util.Map;
import java.util.HashMap;

import java.util.regex.*;

/**
 * JPower Release Information
 */
public final class JPower
{
   private static ReleaseInfo release;

   static {
      String data = IOUtils.getResourceAsString(JPower.class, "release.properties");
      try
      {
         Pattern pattern = Pattern.compile("^(.*)=(.*)$");
         Matcher matcher = pattern.matcher(data);
         Map<String, String> info = new HashMap<>();
         while (matcher.find())
         {
            info.put(matcher.group(1), matcher.group(2));
         }
         release = new ReleaseInfo(info);
      }
      catch (Exception ignored)
      {
      }
   }

   public static ReleaseInfo getReleaseInfo()
   {
      return release;
   }

   public static void main(String[] args)
   {
      System.out.println("JPower v" + getReleaseInfo().getVersion());
   }

   public static class ReleaseInfo {
      private String version;

      public ReleaseInfo(Map<String, String> info)
      {
         this.version = info.get("version");
      }

      public String getVersion()
      {
         return version;
      }
   }
}
