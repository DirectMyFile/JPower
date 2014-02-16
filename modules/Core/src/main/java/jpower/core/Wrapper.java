package jpower.core;

public class Wrapper<T> {
    private T value;

    public Wrapper(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public T set(T value) {
        T old = get();
        this.value = value;
        return old;
    }
}
