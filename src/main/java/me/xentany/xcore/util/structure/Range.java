package me.xentany.xcore.util.structure;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record Range<T extends Comparable<T>>(T start, T end) {

  public Range {
    if (start.compareTo(end) > 0) {
      throw new IllegalArgumentException("Start cannot be greater than end");
    }
  }

  public boolean contains(final T value) {
    return this.start.compareTo(value) <= 0 && this.end.compareTo(value) >= 0;
  }

  @Contract(pure = true)
  @Override
  public @NotNull String toString() {
    return "Range{" + "start=" + start + ", end=" + end + '}';
  }
}