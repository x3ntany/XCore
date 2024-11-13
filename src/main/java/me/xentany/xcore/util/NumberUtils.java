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
  public static int toInt(final @Nullable String value, final int defaultValue) {
    if (value == null) {
      return defaultValue;
    }

    try {
      var stringNumber = value.trim()
          .replaceAll("[^0-9.-]", "")
          .replaceAll("(\\.)(?=.*\\.)", "");
      var doubleValue = Double.parseDouble(stringNumber);
      return doubleValue < Integer.MIN_VALUE || doubleValue > Integer.MAX_VALUE ? null : (int) MathUtils.round(doubleValue);
    } catch (final NumberFormatException e) {
      return defaultValue;
    }
  }

  @Contract(pure = true)
  public static long toLong(final @Nullable String value, final long defaultValue) {
    if (value == null) {
      return defaultValue;
    }

    try {
      return MathUtils.round(Double.parseDouble(value.trim()
          .replaceAll("[^0-9.-]", "")
          .replaceAll("(\\.)(?=.*\\.)", ""))
      );
    } catch (final NumberFormatException e) {
      return defaultValue;
    }
  }

  @Contract(pure = true)
  public static @NotNull Double toDouble(final @Nullable String value, final double defaultValue) {
    if (value == null) {
      return defaultValue;
    }

    try {
      return Double.parseDouble(value.trim()
          .replaceAll("[^0-9.-]", "")
          .replaceAll("(\\.)(?=.*\\.)", "")
      );
    } catch (final NumberFormatException e) {
      return defaultValue;
    }
  }

  @Contract(pure = true)
  public static @NotNull Float toFloat(final @Nullable String value, final float defaultValue) {
    if (value == null) {
      return defaultValue;
    }

    try {
      return Float.parseFloat(value.trim()
          .replaceAll("[^0-9.-]", "")
          .replaceAll("(\\.)(?=.*\\.)", "")
      );
    } catch (final NumberFormatException e) {
      return defaultValue;
    }
  }

  @Contract(pure = true)
  public static short toShort(final @Nullable String value, final short defaultValue) {
    if (value == null) {
      return defaultValue;
    }

    try {
      var stringNumber = value.trim()
          .replaceAll("[^0-9.-]", "")
          .replaceAll("(\\.)(?=.*\\.)", "");
      var doubleValue = Double.parseDouble(stringNumber);
      return (doubleValue < Short.MIN_VALUE || doubleValue > Short.MAX_VALUE) ? null : (short) MathUtils.round(doubleValue);
    } catch (final NumberFormatException e) {
      return defaultValue;
    }
  }

  @Contract(pure = true)
  public static byte toByte(final @Nullable String value, final byte defaultValue) {
    if (value == null) {
      return defaultValue;
    }

    try {
      var stringNumber = value.trim()
          .replaceAll("[^0-9.-]", "")
          .replaceAll("(\\.)(?=.*\\.)", "");
      var doubleValue = Double.parseDouble(stringNumber);
      return (doubleValue < Byte.MIN_VALUE || doubleValue > Byte.MAX_VALUE) ? null : (byte) MathUtils.round(doubleValue);
    } catch (final NumberFormatException e) {
      return defaultValue;
    }
  }

  @Contract(pure = true)
  public static int toInt(final @Nullable String[] values, final int index, final int defaultValue) {
    if (values == null || index < 0 || index >= values.length) {
      return defaultValue;
    } else {
      return NumberUtils.toInt(values[index], defaultValue);
    }
  }

  @Contract(pure = true)
  public static long toLong(final @Nullable String[] values, final int index, final long defaultValue) {
    if (values == null || index < 0 || index >= values.length) {
      return defaultValue;
    } else {
      return NumberUtils.toLong(values[index], defaultValue);
    }
  }

  @Contract(pure = true)
  public static @NotNull Double toDouble(final @Nullable String[] values, final int index, final double defaultValue) {
    if (values == null || index < 0 || index >= values.length) {
      return defaultValue;
    } else {
      return NumberUtils.toDouble(values[index], defaultValue);
    }
  }

  @Contract(pure = true)
  public static @NotNull Float toFloat(final @Nullable String[] values, final int index, final float defaultValue) {
    if (values == null || index < 0 || index >= values.length) {
      return defaultValue;
    } else {
      return NumberUtils.toFloat(values[index], defaultValue);
    }
  }

  @Contract(pure = true)
  public static short toShort(final @Nullable String[] values, final int index, final short defaultValue) {
    if (values == null || index < 0 || index >= values.length) {
      return defaultValue;
    } else {
      return NumberUtils.toShort(values[index], defaultValue);
    }
  }

  @Contract(pure = true)
  public static byte toByte(final @Nullable String[] values, final int index, final byte defaultValue) {
    if (values == null || index < 0 || index >= values.length) {
      return defaultValue;
    } else {
      return NumberUtils.toByte(values[index], defaultValue);
    }
  }
}