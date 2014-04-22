package jpower.core.test;

import jpower.core.utils.ArrayUtils;
import jpower.core.utils.ThreadUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ThreadUtilsTest
{
   @Test
   public void testGetAllThreads()
   {
      List<Thread> threads = ArrayUtils.toList(ThreadUtils.getAllThreads());
      System.out.println("There are " + threads.size() + " threads.");
      assertFalse(threads.isEmpty());
      assertTrue(threads.contains(Thread.currentThread()));
   }
}
