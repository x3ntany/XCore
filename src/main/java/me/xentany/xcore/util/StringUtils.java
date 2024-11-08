package me.xentany.xcore.util;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

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

  public static @NotNull String format(final @NotNull String message,
                                       final Object @NotNull ... args) {
    var result = new StringBuilder();
    int length = message.length();

    for (int i = 0; i < length; i++) {
      var currentChar = message.charAt(i);

      if (currentChar == '{' && i + 1 < length) {
        int endIndex = message.indexOf('}', i);

        if (endIndex > i) {
          var indexStr = message.substring(i + 1, endIndex);

          try {
            int argIndex = Integer.parseInt(indexStr);

            if (argIndex >= 0 && argIndex < args.length) {
              result.append(Objects.toString(args[argIndex], ""));
            } else {
              result.append("{").append(indexStr).append("}");
            }

            i = endIndex;
            continue;
          } catch (NumberFormatException ignored) {}
        }
      }

      result.append(currentChar);
    }

    return result.toString();
  }
}