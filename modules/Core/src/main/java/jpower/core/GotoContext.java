package jpower.core;

import java.util.HashMap;
import java.util.Map;

public class GotoContext {
   private final Map<String, Runnable> actions = new HashMap<>();

   public static GotoContext make() {
      return new GotoContext();
   }

   public GotoContext define(String name, Runnable action) {
      actions.put(name, action);
      return this;
   }

   public GotoContext run(String name) {
      if (!actions.containsKey(name)) {
         throw new RuntimeException("no such action: " + name);
      }
      actions.get(name).run();
      return this;
   }

   public GotoContext goTo(String name) {
      return run(name);
   }
}
