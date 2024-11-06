package me.xentany.xcore.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public final class Validator<T> {

  private final Predicate<T> predicate;

  public Validator(final Predicate<T> predicate) {
    this.predicate = predicate;
  }

  public boolean validate(final T value) {
    return this.predicate.test(value);
  }

  @Contract(value = "_ -> new", pure = true)
  public static <T> @NotNull Validator<T> of(final Predicate<T> predicate) {
    return new Validator<>(predicate);
  }
}