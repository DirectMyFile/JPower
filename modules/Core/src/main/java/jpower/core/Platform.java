package jpower.core;

public class Platform
{

   public static String getProcessorPlatform()
   {
      return System.getProperty("os.arch");
   }

}
