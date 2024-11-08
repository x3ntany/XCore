package me.xentany.xcore.util.structure;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record Triple<A, B, C>(A first, B second, C third) {

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (!(object instanceof final Triple<?, ?, ?> triple)) {
      return false;
    }

    return Objects.equals(this.first, triple.first) &&
        Objects.equals(this.second, triple.second) &&
        Objects.equals(this.third, triple.third);
  }

  @Contract(pure = true)
  @Override
  public @NotNull String toString() {
    return "Triple{" + "first=" + this.first + ", second=" + this.second + ", 1third=" + this.third + '}';
  }
}