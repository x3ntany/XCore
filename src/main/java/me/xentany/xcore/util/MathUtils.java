package me.xentany.xcore.util;

public final class MathUtils {

  public static double toRadians(final double degrees) {
    return degrees * Math.PI / 180.0;
  }

  public static double toDegrees(final double radians) {
    return radians * 180.0 / Math.PI;
  }

  public static double normalizeAngle(final double angle) {
    var normalizedAngle = angle % 360;

    if (normalizedAngle < 0) {
      normalizedAngle += 360;
    }

    return normalizedAngle;
  }

  public static long round(final double value) {
    return (long) (value + (value > 0 ? 0.5 : -0.5));
  }

  public static long roundToPlaces(final double value, final int places) {
    if (places < 0) {
      throw new IllegalArgumentException("Decimal places must be non-negative");
    }

    var factor = (long) Math.pow(10, places);
    return MathUtils.round(value * factor) / factor;
  }

  public static int randomInt(final int minimum, final int maximum) {
    return (int) (Math.random() * (maximum - minimum + 1)) + minimum;
  }

  public static double randomDouble(final double minimum, final double maximum) {
    return minimum + (Math.random() * (maximum - minimum));
  }

  public static boolean isInRange(final double value, final double minimum, final double maximum) {
    return value >= minimum && value <= maximum;
  }

  public static double lerp(final double start, final double end, final double alpha) {
    return start + alpha * (end - start);
  }
}