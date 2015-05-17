package jpower.core;

/**
 * Operating System Utilities
 */
public final class OperatingSystem {
   private static final OperatingSystem current = forName(System.getProperty("os.name"));

   private final String name;

   private OperatingSystem(String name) {
      this.name = name.toLowerCase();
   }

   /**
    * Get the current Operating System.
    *
    * @return Current OS
    */
   public static OperatingSystem current() {
      return current;
   }

   /**
    * Get the specified Operating System.
    *
    * @param name OS Name
    * @return Operating System
    */
   public static OperatingSystem forName(String name) {
      return new OperatingSystem(name);
   }

   /**
    * Check if the Operating System is Windows.
    *
    * @return true if Windows, otherwise false
    */
   public boolean isWindows() {
      return name.contains("win");
   }

   /**
    * Check if the Operating System is Mac OS X.
    *
    * @return true if Mac OSX, otherwise false
    */
   public boolean isMac() {
      return name.contains("mac");
   }

   /**
    * Check if the Operating System is Unix-based.
    *
    * @return true if Unix, otherwise false
    */
   public boolean isUnix() {
      return name.contains("nix") || name.contains("nux") || name.contains("aix");
   }

   /**
    * Check if the Operating System is Solaris.
    *
    * @return true if Solaris, otherwise false
    */
   public boolean isSolaris() {
      return name.contains("sunos");
   }

   /**
    * Check if the Operating System could not be determined.
    *
    * @return true if not determined, otherwise false
    */
   public boolean isUnknown() {
      return !(isUnix() || isSolaris() || isMac() || isWindows());
   }

   /**
    * Check if the Operating System is case-sensitive.
    *
    * @return true if case-sensitive, otherwise false
    */
   public boolean isCaseSensitive() {
      return isUnix();
   }

   /**
    * Check if this Operating System is equal to the current one.
    *
    * @return true if the current os, otherwise false
    */
   public boolean isCurrent() {
      return current().name.equals(name);
   }

   /**
    * Get the Operating System version.
    *
    * @return OS Version if the instance is the Current OS, otherwise "unknown"
    */
   public String version() {
      return isCurrent() ? System.getProperty("os.version") : "unknown";
   }

   /**
    * Get the line separator for the operating system.
    *
    * @return Line Separator
    */
   public String lineSeparator() {
      if (isUnix() || isMac()) {
         return "\n";
      } else if (isWindows()) {
         return "\r\n";
      } else {
         return "\n";
      }
   }

   /**
    * Get the Operating System name.
    *
    * @return OS Name
    */
   public String getName() {
      return name;
   }
}
