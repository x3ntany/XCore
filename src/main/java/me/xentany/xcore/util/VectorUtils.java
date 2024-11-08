package me.xentany.xcore.util;

import org.bukkit.util.Vector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class VectorUtils {

  public static @Nullable Vector toVector(final @NotNull String stringVector) {
    return VectorUtils.toVector(stringVector, false);
  }

  public static @Nullable Vector toVector(final @NotNull String stringVector, final boolean round) {
    var parts = stringVector
        .replaceAll("[^0-9.,-]", "")
        .replaceAll("\\s+", "")
        .split(",");

    if (parts.length < 3) {
      return null;
    }

    try {
      var x = Double.parseDouble(parts[0]);
      var y = Double.parseDouble(parts[1]);
      var z = Double.parseDouble(parts[2]);

      if (round) {
        x = MathUtils.round(x);
        y = MathUtils.round(y);
        z = MathUtils.round(z);
      }

      return new Vector(x, y, z);
    } catch (final NumberFormatException e) {
      return null;
    }
  }

  public static @NotNull String toString(final @NotNull Vector vector) {
    return VectorUtils.toString(vector, false);
  }

  public static @NotNull String toString(final @NotNull Vector vector, final boolean round) {
    if (round) {
      return MathUtils.round(vector.getX()) + "," +
          MathUtils.round(vector.getY()) + "," +
          MathUtils.round(vector.getZ());
    } else {
      return String.format("%.2f,%.2f,%.2f", vector.getX(), vector.getY(), vector.getZ());
    }
  }

  public static boolean isInside(final @NotNull Vector point,
                                 final @NotNull Vector minimum,
                                 final @NotNull Vector maximum) {
    var minX = Math.min(minimum.getX(), maximum.getX());
    var maxX = Math.max(minimum.getX(), maximum.getX());
    var minY = Math.min(minimum.getY(), maximum.getY());
    var maxY = Math.max(minimum.getY(), maximum.getY());
    var minZ = Math.min(minimum.getZ(), maximum.getZ());
    var maxZ = Math.max(minimum.getZ(), maximum.getZ());
    return MathUtils.isInRange(point.getX(), minX, maxX) &&
        MathUtils.isInRange(point.getY(), minY, maxY) &&
        MathUtils.isInRange(point.getZ(), minZ, maxZ);
  }

  public static double distance(final @NotNull Vector a, final Vector b) {
    return a.clone().subtract(b).length();
  }

  @Contract("_, _ -> new")
  public static @NotNull Vector getDirection(final double yaw, final double pitch) {
    var yawRad = Math.toRadians(yaw);
    var pitchRad = Math.toRadians(pitch);
    var x = -Math.cos(pitchRad) * Math.sin(yawRad);
    var y = -Math.sin(pitchRad);
    var z = Math.cos(pitchRad) * Math.cos(yawRad);
    return new Vector(x, y, z);
  }
}