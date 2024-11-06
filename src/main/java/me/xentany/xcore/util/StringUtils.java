package me.xentany.xcore.util;

import org.jetbrains.annotations.NotNull;

public final class StringUtils {

  public static boolean containsColorCodes(final @NotNull String value) {
    return value.matches(".*[&§][0-9A-FK-ORa-fk-or].*");
  }

  public static @NotNull String truncate(final @NotNull String value, final int maxLength) {
    return value.length() <= maxLength ? value : value.substring(0, maxLength - 3) + "...";
  }

  public static int countOccurrences(final @NotNull String value, final @NotNull String sub) {
    int count = 0;
    int index = 0;

    while ((index = value.indexOf(sub, index)) != -1) {
      count++;
      index += sub.length();
    }

    return count;
  }
}