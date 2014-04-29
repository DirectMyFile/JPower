package jpower.core.test;

import jpower.core.OperatingSystem;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class OperatingSystemTest
{
   @Test
   public void testForUnknownOperatingSystems()
   {
      assertFalse(OperatingSystem.current().isUnknown());
   }
}
