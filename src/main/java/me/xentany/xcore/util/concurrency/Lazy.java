package me.xentany.xcore.util.concurrency;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public final class Lazy<T> {

  private Supplier<T> supplier;
  private T value;

  private Lazy(final Supplier<T> supplier) {
    this.supplier = supplier;
  }

  @Contract(value = "_ -> new", pure = true)
  public static <T> @NotNull Lazy<T> of(final Supplier<T> supplier) {
    return new Lazy<>(supplier);
  }

  public T get() {
    if (this.supplier != null) {
      this.value = this.supplier.get();
      this.supplier = null;
    }

    return this.value;
  }
}