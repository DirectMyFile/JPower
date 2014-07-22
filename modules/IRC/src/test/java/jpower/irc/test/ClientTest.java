package jpower.irc.test;

import org.junit.Test;
import jpower.irc.PowerIrc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ClientTest
{

   @Test
   public void testConnect()
   {
      PowerIrc irc = new PowerIrc("LoganBot", "LoganBot", "irc.esper.net", 6667);
      irc.connect();
   }
}
