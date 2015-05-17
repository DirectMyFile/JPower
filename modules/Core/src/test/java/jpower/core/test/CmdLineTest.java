package jpower.core.test;

import jpower.core.opts.CommandLine;
import jpower.core.opts.CommandLineParser;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CmdLineTest {
   @Test
   public void testBasicHelp() {
      CommandLine cmdline = CommandLineParser.parse("--help");
      System.out.println(cmdline.opts());
      assertNotNull(cmdline.opt("help"));
      assertTrue(cmdline.bool("help"));
   }
}
