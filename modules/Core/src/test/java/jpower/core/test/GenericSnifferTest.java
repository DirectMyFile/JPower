package jpower.core.test;

import jpower.core.GenericSniffer;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GenericSnifferTest
{
   @Test
   public void testSniff()
   {
      Type type = new GenericSniffer<List<String>>()
      {
      }.sniff();
      assertEquals(type.getTypeName(), "java.util.List<java.lang.String>");
   }
}
