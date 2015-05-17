package jpower.core;

import jpower.core.utils.IOUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JPower Release Information
 */
public final class JPower {
   private static final ReleaseInfo release;

   /**
    * Loads the Release Info
    */
   static {
      String data = IOUtils.getResourceAsString(JPower.class, "release.properties");
      ReleaseInfo relInfo;
      try {
         Pattern pattern = Pattern.compile("(.*)=(.*)");
         Matcher matcher = pattern.matcher(data);
         Map<String, String> info = new HashMap<>();
         while (matcher.find()) {
            info.put(matcher.group(1), matcher.group(2));
         }
         relInfo = new ReleaseInfo(info);
      } catch (Exception ignored) {
         relInfo = null;
      }
      release = relInfo;
   }

   /**
    * Gets the JPower Release Info
    *
    * @return Release Info
    */
   public static ReleaseInfo getReleaseInfo() {
      return release;
   }

   /**
    * Main Entry Point for Console
    *
    * @param args arguments
    */
   public static void main(String[] args) {
      System.out.println("JPower v" + getReleaseInfo().getVersion());
   }

   /**
    * Release Information
    */
   public static class ReleaseInfo {
      /**
       * JPower Version
       */
      private final String version;

      /**
       * Git Commit
       */
      private final String commit;

      /**
       * Creates a new Release Info Instance
       *
       * @param info map of information
       */
      public ReleaseInfo(Map<String, String> info) {
         this.version = info.get("version");
         this.commit = info.get("commit");
      }

      /**
       * Gets the JPower Version
       *
       * @return Version
       */
      public String getVersion() {
         return version;
      }

      /**
       * Gets the Git Commit that this build was built on
       *
       * @return Git Commit
       */
      public String getCommit() {
         return commit;
      }
   }
}
