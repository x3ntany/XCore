package me.xentany.xcore.util;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Level;

public final class ReflectionUtils {

  public static @Nullable Object invokePrivateMethod(final @NotNull Plugin plugin,
                                                     final @NotNull Object instance,
                                                     final @NotNull String methodName,
                                                     final @NotNull Class<?>[] parameterTypes,
                                                     final @NotNull Object... args) {
    try {
      var method = instance.getClass().getDeclaredMethod(methodName, parameterTypes);
      method.setAccessible(true);
      return method.invoke(instance, args);
    } catch (final Exception e) {
      plugin.getLogger().log(Level.SEVERE, "Failed to invoke method '" + methodName + "' on instance of class "
          + instance.getClass().getName() + ". Reason: " + e.getClass().getSimpleName() + " - " + e.getMessage(), e);
      return null;
    }
  }

  public static @Nullable Object invokePrivateStaticMethod(final @NotNull Plugin plugin,
                                                           final @NotNull Class<?> clazz,
                                                           final @NotNull String methodName,
                                                           final @NotNull Class<?>[] parameterTypes,
                                                           final @NotNull Object... args) {
    try {
      var method = clazz.getDeclaredMethod(methodName, parameterTypes);
      method.setAccessible(true);
      return method.invoke(null, args);
    } catch (final Exception e) {
      plugin.getLogger().log(Level.SEVERE, "Failed to invoke static method '" + methodName + "' on class "
          + clazz.getName() + ". Reason: " + e.getClass().getSimpleName() + " - " + e.getMessage(), e);
      return null;
    }
  }

  @SuppressWarnings("unchecked")
  public static @Nullable <T> T getPrivateField(final @NotNull Plugin plugin,
                                                final @NotNull Object instance,
                                                final @NotNull String fieldName) {
    try {
      var field = instance.getClass().getDeclaredField(fieldName);
      field.setAccessible(true);
      return (T) field.get(instance);
    } catch (final Exception e) {
      plugin.getLogger().log(Level.SEVERE, "Failed to access field '" + fieldName + "' on instance of class "
          + instance.getClass().getName() + ". Reason: " + e.getClass().getSimpleName() + " - " + e.getMessage(), e);
      return null;
    }
  }

  @SuppressWarnings("unchecked")
  public static @Nullable <T> T getPrivateStaticField(final @NotNull Plugin plugin,
                                                      final @NotNull Class<?> clazz,
                                                      final @NotNull String fieldName) {
    try {
      var field = clazz.getDeclaredField(fieldName);
      field.setAccessible(true);
      return (T) field.get(null);
    } catch (final Exception e) {
      plugin.getLogger().log(Level.SEVERE, "Failed to access static field '" + fieldName + "' on class "
          + clazz.getName() + ". Reason: " + e.getClass().getSimpleName() + " - " + e.getMessage(), e);
      return null;
    }
  }

  public static boolean hasMethod(final @NotNull Object instance,
                                  final @NotNull String methodName,
                                  final @NotNull Class<?>... parameterTypes) {
    try {
      instance.getClass().getDeclaredMethod(methodName, parameterTypes);
      return true;
    } catch (final NoSuchMethodException e) {
      return false;
    }
  }
}