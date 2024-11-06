package me.xentany.xcore.util.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Cache<K, V> {

  private final Map<K, V> cache;

  public Cache(final int maxSize) {
    this.cache = new LinkedHashMap<>(maxSize, 0.75f, true) {

      @Override
      protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
        return this.size() > maxSize;
      }
    };
  }

  public V get(final K key) {
    return this.cache.get(key);
  }

  public void put(final K key, final V value) {
    this.cache.put(key, value);
  }

  public boolean containsKey(final K key) {
    return this.cache.containsKey(key);
  }
}