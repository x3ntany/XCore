package me.xentany.xcore.util.structure;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class Either<L, R> {

  private final L left;
  private final R right;

  private Either(final L left, final R right) {
    this.left = left;
    this.right = right;
  }

  @Contract(value = "_ -> new", pure = true)
  public static <L, R> @NotNull Either<L, R> left(final L value) {
    return new Either<>(value, null);
  }

  @Contract(value = "_ -> new", pure = true)
  public static <L, R> @NotNull Either<L, R> right(final R value) {
    return new Either<>(null, value);
  }

  public boolean isLeft() {
    return this.left != null;
  }

  public boolean isRight() {
    return this.right != null;
  }

  public L getLeft() {
    return this.left;
  }

  public R getRight() {
    return this.right;
  }

  @Contract(pure = true)
  @Override
  public @NotNull String toString() {
    if (this.isLeft()) {
      return "Either[left=" + this.left + "]";
    } else if (this.isRight()) {
      return "Either[right=" + this.right + "]";
    } else {
      return "Either[empty]";
    }
  }
}