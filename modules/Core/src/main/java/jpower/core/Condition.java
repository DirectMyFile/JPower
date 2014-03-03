package jpower.core;

public interface Condition {
    boolean check();

    default boolean inverted() {
        return !check();
    }
}
