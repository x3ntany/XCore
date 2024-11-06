package me.xentany.xcore.util.structure;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class Result<T, E> {

  private final T value;
  private final E error;
  private final boolean isSuccess;

  private Result(final T value, final E error, final boolean isSuccess) {
    this.value = value;
    this.error = error;
    this.isSuccess = isSuccess;
  }

  @Contract(value = "_ -> new", pure = true)
  public static <T, E> @NotNull Result<T, E> success(final T value) {
    return new Result<>(value, null, true);
  }

  @Contract(value = "_ -> new", pure = true)
  public static <T, E> @NotNull Result<T, E> error(final E error) {
    return new Result<>(null, error, false);
  }

  public boolean isSuccess() {
    return this.isSuccess;
  }

  public T getValue() {
    if (!this.isSuccess) {
      throw new IllegalStateException("Result does not contain a value");
    } else {
      return this.value;
    }
  }

  public E getError() {
    if (this.isSuccess) {
      throw new IllegalStateException("Result does not contain an error");
    } else {
      return this.error;
    }
  }

  @Contract(pure = true)
  @Override
  public @NotNull String toString() {
    return this.isSuccess ?
        "Success{" + "value=" + this.value + '}' :
        "Error{" + "error=" + this.error + '}';
  }
}