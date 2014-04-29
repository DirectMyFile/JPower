package jpower.core.test;

import jpower.core.MultiMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MultiMapTest
{
   private MultiMap<String, String> multiMap;

   @Before
   public void prepare()
   {
      multiMap = new MultiMap<>();
   }

   @Test
   public void testBasics()
   {
      multiMap.add("Tests", "Test1");
      multiMap.add("Tests", "Test2");
      assertEquals(2, multiMap.getAll("Tests").size());
      assertTrue(multiMap.contains("Tests", "Test1"));
      assertTrue(multiMap.contains("Tests", "Test2"));
      assertFalse(multiMap.isEmpty());
      assertFalse(multiMap.isEmpty("Tests"));
   }
}
