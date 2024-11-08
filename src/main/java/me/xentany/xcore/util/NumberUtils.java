package me.xentany.xcore.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class NumberUtils {

  private static final String[] ROMAN_NUMERALS = {
      "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
  };
  private static final int[] ARABIC_NUMBERS = {
      1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
  };

  @Contract(pure = true)
  public static @NotNull String toRoman(final int value) {
    if (value <= 0 || value > 1000) {
      throw new IllegalArgumentException("Number must be between 1 and 1000");
    }

    var roman = new StringBuilder();
    int number = value;

    for (int i = 0; i < NumberUtils.ARABIC_NUMBERS.length; i++) {
      while (number >= NumberUtils.ARABIC_NUMBERS[i]) {
        roman.append(NumberUtils.ROMAN_NUMERALS[i]);
        number -= NumberUtils.ARABIC_NUMBERS[i];
      }
    }

    return roman.toString();
  }

  @Contract(pure = true)
  public static @NotNull String toString(final int value) {
    return Integer.toString(value);
  }

  @Contract(pure = true)
  public static @NotNull String toString(final long value) {
    return Long.toString(value);
  }

  @Contract(pure = true)
  public static @NotNull String toString(final double value) {
    return Double.toString(value);
  }

  @Contract(pure = true)
  public static @NotNull String toString(final float value) {
    return Float.toString(value);
  }

  @Contract(pure = true)
  public static @NotNull String toString(final short value) {
    return Short.toString(value);
  }

  @Contract(pure = true)
  public static @NotNull String toString(final byte value) {
    return Byte.toString(value);
  }

  @Contract(pure = true)
  public static @Nullable Integer toInteger(final @NotNull String value) {
    try {
      return Integer.parseInt(value
          .replaceAll("[^0-9.-]", "")
          .replaceAll("\\s+", "")
      );
    } catch (final NumberFormatException e) {
      return null;
    }
  }

  @Contract(pure = true)
  public static @Nullable Long toLong(final @NotNull String value) {
    try {
      return Long.parseLong(value
          .replaceAll("[^0-9.-]", "")
          .replaceAll("\\s+", "")
      );
    } catch (final NumberFormatException e) {
      return null;
    }
  }

  @Contract(pure = true)
  public static @Nullable Double toDouble(final @NotNull String value) {
    try {
      return Double.parseDouble(value
          .replaceAll("[^0-9.-]", "")
          .replaceAll("\\s+", "")
      );
    } catch (final NumberFormatException e) {
      return null;
    }
  }

  @Contract(pure = true)
  public static @Nullable Float toFloat(final @NotNull String value) {
    try {
      return Float.parseFloat(value
          .replaceAll("[^0-9.-]", "")
          .replaceAll("\\s+", "")
      );
    } catch (final NumberFormatException e) {
      return null;
    }
  }

  @Contract(pure = true)
  public static @Nullable Short toShort(final @NotNull String value) {
    try {
      return Short.parseShort(value
          .replaceAll("[^0-9.-]", "")
          .replaceAll("\\s+", "")
      );
    } catch (final NumberFormatException e) {
      return null;
    }
  }

  @Contract(pure = true)
  public static @Nullable Byte toByte(final @NotNull String value) {
    try {
      return Byte.parseByte(value
          .replaceAll("[^0-9.-]", "")
          .replaceAll("\\s+", "")
      );
    } catch (final NumberFormatException e) {
      return null;
    }
  }
}