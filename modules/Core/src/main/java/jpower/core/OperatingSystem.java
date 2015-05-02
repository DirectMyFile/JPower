package jpower.core;

/**
 * Operating System Utilities
 */
public final class OperatingSystem
{
   private static final OperatingSystem current = forName(System.getProperty("os.name"));

   private final String name;

   private OperatingSystem(String name)
   {
      this.name = name.toLowerCase();
   }

   /**
    * Gets the Current Operating System
    *
    * @return Current OS
    */
   public static OperatingSystem current()
   {
      return current;
   }

   /**
    * Gets the specified Operating System
    * @param name OS Name
    * @return Operating System
    */
   public static OperatingSystem forName(String name)
   {
      return new OperatingSystem(name);
   }

   /**
    * Checks if the Current OS is Windows
    * @return true if Windows, otherwise false
    */
   public boolean isWindows()
   {
      return name.contains("win");
   }

   /**
    * Checks if the Current OS is Mac OSX
    * @return true if Mac OSX, otherwise false
    */
   public boolean isMac()
   {
      return name.contains("mac");
   }

   /**
    * Checks if the Current OS is Unix
    * @return true if Unix, otherwise false
    */
   public boolean isUnix()
   {
      return name.contains("nix") || name.contains("nux") || name.contains("aix");
   }

   /**
    * Checks if the Current OS is Solaris
    * @return true if Solaris, otherwise false
    */
   public boolean isSolaris()
   {
      return name.contains("sunos");
   }

   /**
    * Checks if the OS could not be determined
    * @return true if not determined, otherwise false
    */
   public boolean isUnknown()
   {
      return !(isUnix() || isSolaris() || isMac() || isWindows());
   }

   /**
    * Checks if the OS is Case Sensitive
    * @return true if case sensitive, otherwise false
    */
   public boolean isCaseSensitive()
   {
      return isUnix();
   }

   /**
    * Checks if the Operating System is the same as the Current Operating System
    * @return true if the current os, otherwise false
    */
   public boolean isCurrent()
   {
      return current().name.equals(name);
   }

   /**
    * Gets the OS Version
    * @return OS Version if the instance is the Current OS, otherwise "unknown"
    */
   public String version()
   {
      return isCurrent() ? System.getProperty("os.version") : "unknown";
   }

   /**
    * Gets the Line Separator for the OS
    * @return Line Separator
    */
   public String lineSeparator()
   {
      if (isUnix() || isMac())
      {
         return "\n";
      }
      else if (isWindows())
      {
         return "\r\n";
      }
      else
      {
         return "\n";
      }
   }

   /**
    * Gets the OS Name
    * @return OS Name
    */
   public String getName()
   {
      return name;
   }
}
