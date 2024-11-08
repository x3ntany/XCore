package me.xentany.xcore.util.structure;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record Pair<A, B>(A first, B second) {

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (!(object instanceof final Pair<?, ?> pair)) {
      return false;
    }

    return Objects.equals(this.first, pair.first) && Objects.equals(this.second, pair.second);
  }

  @Contract(pure = true)
  @Override
  public @NotNull String toString() {
    return "Pair{" + "first=" + this.first + ", second=" + this.second + '}';
  }
}