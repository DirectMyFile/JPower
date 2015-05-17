package jpower.core.opts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Accessor for Command Line Options and Arguments
 */
public class CommandLine {
   private final Map<String, String> opts = new HashMap<>();

   private final List<String> arguments = new ArrayList<>();

   public String opt(String key) {
      return opts.get(key);
   }

   protected void set(String key, String value) {
      opts.put(key, value);
   }

   public String opt(String key, String defaultValue) {
      return opts.getOrDefault(key, defaultValue);
   }

   public Map<String, String> opts() {
      return opts;
   }

   public List<String> arguments() {
      return arguments;
   }

   public boolean bool(String key) {
      return opt(key) != null && opt(key).equals("true");
   }

   public boolean help() {
      return opts.containsKey("help");
   }
}
