package jpower.core;

import java.util.function.Consumer;

public class Repeater {
   private final Consumer<Integer> task;

   public Repeater(Consumer<Integer> task) {
      this.task = task;
   }

   public void run(int times) {
      int c = times + 1; // Make sure it runs the last one
      for (int i = 1; i != c; i++) {
         task.accept(i);
      }
   }
}
