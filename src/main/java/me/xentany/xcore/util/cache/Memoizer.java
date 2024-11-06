package me.xentany.xcore.util.cache;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public final class Memoizer<T, R> {

  private final Map<T, R> cache = new ConcurrentHashMap<>();

  @Contract(pure = true)
  public @NotNull Function<T, R> memoize(final Function<T, R> function) {
    return input -> this.cache.computeIfAbsent(input, function);
  }
}