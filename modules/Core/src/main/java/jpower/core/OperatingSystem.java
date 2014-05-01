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

   public static OperatingSystem current()
   {
      return current;
   }

   public static OperatingSystem forName(String name)
   {
      return new OperatingSystem(name);
   }

   public boolean isWindows()
   {
      return name.contains("win");
   }

   public boolean isMac()
   {
      return name.contains("mac");
   }

   public boolean isUnix()
   {
      return name.contains("nix") || name.contains("nux") || name.contains("aix");
   }

   public boolean isSolaris()
   {
      return name.contains("sunos");
   }

   public boolean isUnknown()
   {
      return !(isUnix() || isSolaris() || isMac() || isWindows());
   }

   public boolean isCaseSensitive()
   {
      return isUnix();
   }

   public boolean isCurrent()
   {
      return current().name.equals(name);
   }

   public String version()
   {
      return isCurrent() ? System.getProperty("os.version") : "unknown";
   }

   public String lineSeparator()
   {
      if (isUnix())
      {
         return "\n";
      } else if (isWindows())
      {
         return "\r\n";
      } else if (isMac())
      {
         return "\r";
      } else
      {
         return "\n";
      }
   }

   public String getName()
   {
      return name;
   }
}