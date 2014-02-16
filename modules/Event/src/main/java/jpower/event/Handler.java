package jpower.event;

public interface Handler<T> {
    public void accept(T event);

    @EventHandler
    public default void handle(T event) {
        accept(event);
    }
}
