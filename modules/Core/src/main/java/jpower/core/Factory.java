package jpower.core;

/**
 * An Object Instance Factory
 * @param <T> Target Object Type
 */
public abstract class Factory<T> {
    /**
     * Creates a New Instance of the Target Object
     * @return New Instance
     */
    public abstract T newInstance();
}
