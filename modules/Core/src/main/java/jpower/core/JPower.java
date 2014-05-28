package jpower.core;

import jpower.core.utils.IOUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JPower Release Information
 */
public final class JPower
{
   private static final ReleaseInfo release;

   static
   {
      String data = IOUtils.getResourceAsString(JPower.class, "release.properties");
      try
      {
         Pattern pattern = Pattern.compile("(.*)=(.*)");
         Matcher matcher = pattern.matcher(data);
         Map<String, String> info = new HashMap<>();
         while (matcher.find())
         {
            info.put(matcher.group(1), matcher.group(2));
         }
         release = new ReleaseInfo(info);
      } catch (Exception ignored)
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

   public static class ReleaseInfo
   {
      private final String version;
      private final String commit;

      public ReleaseInfo(Map<String, String> info)
      {
         this.version = info.get("version");
         this.commit = info.get("commit");
      }

      public String getVersion()
      {
         return version;
      }

      public String getCommit()
      {
         return commit;
      }
   }
}
