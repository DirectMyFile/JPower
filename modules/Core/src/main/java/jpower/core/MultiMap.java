package jpower.core;

import java.util.*;

public class MultiMap<K, V> {
    private final Map<K, List<V>> delegate = new HashMap<K, List<V>>();

    public void put(K key, List<V> values) {
        delegate.put(key, values);
    }

    Collection<V> getAll(K key) {
        if (delegate.containsKey(key)) {
            return delegate.get(key);
        } else {
            return delegate.put(key, new ArrayList<V>());
        }
    }

    public void add(K key, V value) {
        getAll(key).add(value);
    }
}
