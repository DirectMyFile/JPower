package jpower.core.test;

import jpower.core.GenericSniffer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class GenericSnifferTest
{
   @Test
   public void testSniff()
   {
      GenericSniffer<List<String>> sniffer = new GenericSniffer<List<String>>()
      {
      };
      System.out.println(sniffer.toString());
      assertEquals(sniffer.sniff()[0].getTypeName(), "java.util.List<java.lang.String>");
   }
}
