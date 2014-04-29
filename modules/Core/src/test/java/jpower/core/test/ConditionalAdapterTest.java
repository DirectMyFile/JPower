package jpower.core.test;

import jpower.core.ConditionalAdapter;
import jpower.core.Wrapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConditionalAdapterTest
{
   private ConditionalAdapter<String> adapter;

   @Before
   public void prepare() {
      adapter = new ConditionalAdapter<>("Hello World");
   }

   @Test
   public void testBasicWhen() {
      Wrapper<Boolean> worked = Wrapper.of(false);
      adapter.when("Hello World", () -> worked.set(true));
      assertTrue(worked.set(false));
      adapter.not("Hello", () -> worked.set(true));
      assertTrue(worked.set(false));
   }
}
