package jpower.event;

public interface Handler<T> {
   void accept(T event);

   @EventHandler
   default void handle(T event) {
      accept(event);
   }
}
