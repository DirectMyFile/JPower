package jpower.core;

public interface Condition {
    public boolean check();

    public default boolean inverted() {
        return !check();
    }
}
