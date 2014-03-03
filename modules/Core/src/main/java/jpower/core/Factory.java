package jpower.core;

/**
 * An Object Instance Factory
 *
 * @param <T> Target Object Type
 */
public interface Factory<T> {
    /**
     * Creates a New Instance of the Object
     *
     * @return New Instance
     */
    T create();
}
