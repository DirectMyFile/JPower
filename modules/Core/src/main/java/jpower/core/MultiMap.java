package jpower.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiMap<K, V> {
    private Map<K, List<V>> delegate = new HashMap<K, List<V>>();

    public void put(K key, List<V> values) {
        delegate.put(key, values);
    }

    public List<V> getAll(K key) {
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
