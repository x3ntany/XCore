package me.xentany.xcore.util;

public final class Stopwatch {

  private long startTime;

  public void start() {
    this.startTime = System.nanoTime();
  }

  public long stop() {
    return System.nanoTime() - this.startTime;
  }

  public double stopInMillis() {
    return (System.nanoTime() - this.startTime) / 1_000_000.0;
  }
}