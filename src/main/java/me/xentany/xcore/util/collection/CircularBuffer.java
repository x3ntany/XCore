package me.xentany.xcore.util.collection;

public final class CircularBuffer<T> {

  private final Object[] buffer;
  private int head = 0;
  private int tail = 0;
  private int count = 0;

  public CircularBuffer(final int size) {
    this.buffer = new Object[size];
  }

  public void add(final T value) {
    this.buffer[this.tail] = value;
    this.tail = (this.tail + 1) % this.buffer.length;

    if (this.count == this.buffer.length) {
      this.head = (this.head + 1) % this.buffer.length;
    } else {
      this.count++;
    }
  }

  @SuppressWarnings("unchecked")
  public T get(final int index) {
    if (index < 0 || index >= this.count) {
      throw new IndexOutOfBoundsException();
    }

    return (T) this.buffer[(this.head + index) % this.buffer.length];
  }
}