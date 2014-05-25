package jpower.core.test;

import jpower.core.JPower;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReleaseInfoTest
{
   @Test
   public void testVersionNotNull()
   {
       assertNotNull(JPower.getReleaseInfo().getVersion());
   }

   @Test
   public void testVersionFormat()
   {
      assertTrue(JPower.getReleaseInfo().getVersion().contains("."));
      assertEquals(3, JPower.getReleaseInfo().getVersion().split("\\.").length);
   }

   @Test
   public void testCommitNotNull()
   {
      assertNotNull(JPower.getReleaseInfo().getCommit());
   }

   @Test
   public void testCommitValid()
   {
      assertTrue(JPower.getReleaseInfo().getCommit().length() > 15);
   }
}
