package me.xentany.xcore.util.concurrency;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public final class Retry<T> {

  private final int attempts;
  private final long delay;

  public Retry(final int attempts, final long delay) {
    this.attempts = attempts;
    this.delay = delay;
  }

  public T execute(final @NotNull CheckedSupplier<T> task) throws Exception {
    for (int i = 0; i < this.attempts; i++) {
      try {
        return task.get();
      } catch (final Exception e) {
        if (i == this.attempts - 1) {
          throw e;
        }

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
          try {
            TimeUnit.MILLISECONDS.sleep(this.delay);
          } catch (InterruptedException ignoredE) {
            Thread.currentThread().interrupt();
          }
        });

        future.join();
      }
    }

    throw new IllegalStateException("Retry failed");
  }

  @FunctionalInterface
  public interface CheckedSupplier<T> {
    T get() throws Exception;
  }
}