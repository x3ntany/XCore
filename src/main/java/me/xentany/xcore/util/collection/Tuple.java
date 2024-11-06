package me.xentany.xcore.util.collection;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public final class Tuple {

  private final Object[] elements;

  public Tuple(final Object... elements) {
    this.elements = elements;
  }

  @SuppressWarnings("unchecked")
  public <T> T get(final int index) {
    return (T) this.elements[index];
  }

  public int size() {
    return this.elements.length;
  }

  @Contract(pure = true)
  @Override
  public @NotNull String toString() {
    return "Tuple{" + Arrays.toString(this.elements) + '}';
  }
}