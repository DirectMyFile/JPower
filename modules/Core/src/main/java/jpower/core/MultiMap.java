package jpower.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MultiMap<K, V>
{
   private final Map<K, Collection<V>> delegate;

   public MultiMap()
   {
      delegate = new HashMap<>();
   }

   public void put(K key, Collection<V> values)
   {
      delegate.put(key, values);
   }

   public Collection<V> getAll(K key)
   {
      if (getAll().containsKey(key))
      {
         return getAll().get(key);
      }
      else
      {
         put(key, new ArrayList<>());
         return delegate.get(key);
      }
   }

   public void add(K key, V value)
   {
      getAll(key).add(value);
   }

   public boolean remove(K key, V value)
   {
      return getAll(key).remove(value);
   }

   public void clear()
   {
      delegate.clear();
   }

   public void clear(K key)
   {
      /* Reduces Capacity, which can provide us with less memory usage */
      put(key, new ArrayList<>());
   }

   public Map<K, Collection<V>> getAll()
   {
      return delegate;
   }

   public boolean addAll(K key, Collection<V> values)
   {
      return getAll(key).addAll(values);
   }

   public boolean isEmpty()
   {
      return getAll().isEmpty();
   }

   public boolean isEmpty(K key)
   {
      return getAll(key).isEmpty();
   }

   public boolean contains(K key, V value)
   {
      return getAll(key).contains(value);
   }
}
