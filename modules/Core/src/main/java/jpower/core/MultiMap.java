package jpower.core;

import java.util.*;

public class MultiMap<K, V> {
    private Map<K, Collection<V>> delegate;

    public void put(K key, Collection<V> values) {
        createMap();
        delegate.put(key, values);
    }

    /**
     * Lazy Allocation of HashMap improves memory usage.
     * <p>See http://www.youtube.com/watch?v=FLcXf9pO27w</p>
     */
    private void createMap() {
        if (delegate == null) {
            delegate = new HashMap<K, Collection<V>>();
        }
    }

    public Collection<V> getAll(K key) {
        if (getAll().containsKey(key)) {
            return getAll().get(key);
        } else {
            return getAll().put(key, new LinkedList<V>());
        }
    }

    public void add(K key, V value) {
        getAll(key).add(value);
    }

    public boolean remove(K key, V value) {
        return getAll(key).remove(value);
    }

    public void clear() {
        /* Reduces Capacity, which can provide us with less memory usage */
        delegate = null;
        createMap();
    }

    public void clear(K key) {
        /* Reduces Capacity, which can provide us with less memory usage */
        put(key, new LinkedList<V>());
    }

    public Map<K, Collection<V>> getAll() {
        createMap();
        return delegate;
    }

    public boolean addAll(K key, Collection<V> values) {
        return getAll(key).addAll(values);
    }

    public boolean isEmpty() {
        return getAll().isEmpty();
    }

    public boolean isEmpty(K key) {
        return getAll(key).isEmpty();
    }

    public boolean contains(K key, V value) {
        return getAll(key).contains(value);
    }
}
