package jpower.core.test;

import jpower.core.OperatingSystem;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OperatingSystemTest
{
   @Test
   public void testForUnknownOperatingSystems()
   {
      assertFalse(OperatingSystem.current().isUnknown());
   }

   @Test
   public void testWindowsDetection() {
      assertTrue(OperatingSystem.forName("Windows").isWindows());
   }

   @Test
   public void testLinuxDetection() {
      assertTrue(OperatingSystem.forName("Linux").isUnix());
   }

   @Test
   public void testUnixDetection() {
      assertTrue(OperatingSystem.forName("Unix").isUnix());
   }

   @Test
   public void testMacDetection() {
      assertTrue(OperatingSystem.forName("Mac OSX").isMac());
   }

   @Test
   public void testSolarisDetection() {
      assertTrue(OperatingSystem.forName("SunOS").isSolaris());
   }

   @Test
   public void testCaseSensitiveDetection() {
      assertTrue(OperatingSystem.forName("Unix").isCaseSensitive());
      assertFalse(OperatingSystem.forName("Windows").isCaseSensitive());
   }

   @Test
   public void testLineSeparatorDetection() {
      assertTrue(OperatingSystem.forName("Unix").lineSeparator().equals("\n"));
      assertTrue(OperatingSystem.forName("Windows").lineSeparator().equals("\r\n"));
      assertTrue(OperatingSystem.forName("Mac OSX").lineSeparator().equals("\r"));
   }
}
