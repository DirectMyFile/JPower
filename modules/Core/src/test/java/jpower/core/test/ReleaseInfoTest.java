package jpower.core.test;

import jpower.core.JPower;
import jpower.core.utils.RuntimeUtils;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeFalse;

public class ReleaseInfoTest
{
   @Test
   public void testVersionNotNull()
   {
      assumeFalse(RuntimeUtils.classExists("com.intellij.rt.execution.application.AppMain"));
      assertNotNull(JPower.getReleaseInfo().getVersion());
   }

   @Test
   public void testVersionFormat()
   {
      assumeFalse(RuntimeUtils.classExists("com.intellij.rt.execution.application.AppMain"));
      assertTrue(JPower.getReleaseInfo().getVersion().contains("."));
      assertEquals(3, JPower.getReleaseInfo().getVersion().split("\\.").length);
   }

   @Test
   public void testCommitNotNull()
   {
      assumeFalse(RuntimeUtils.classExists("com.intellij.rt.execution.application.AppMain"));
      // assertNotNull(JPower.getReleaseInfo().getCommit());
   }

   @Test
   public void testCommitValid()
   {
      assumeFalse(RuntimeUtils.classExists("com.intellij.rt.execution.application.AppMain"));
      // assertTrue(JPower.getReleaseInfo().getCommit().length() > 15);
   }
}
