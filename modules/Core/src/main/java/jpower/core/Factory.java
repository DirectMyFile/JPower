package jpower.core;

/**
 * An Object Instance Factory
 * @param <T> Target Object Type
 */
abstract class Factory<T> {
    /**
     * Creates a New Instance of the Object
     * @return New Instance
     */
    public abstract T newInstance();
}
