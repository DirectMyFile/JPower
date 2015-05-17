package jpower.core;

/**
 * A simple interface that is applied to
 * anything that is true or false that
 * can be checked as a conditional.
 */
@FunctionalInterface
public interface Condition {
   public boolean check();

   public default boolean inverted() {
      return !check();
   }
}
