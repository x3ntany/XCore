package me.xentany.xcore.util.cache;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public final class Memoizer<K, V> {

  private final Map<K, V> cache = new ConcurrentHashMap<>();

  @Contract(pure = true)
  public @NotNull Function<K, V> memoize(final @NotNull Function<K, V> function) {
    return input -> this.cache.computeIfAbsent(input, function);
  }
}