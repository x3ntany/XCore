package me.xentany.xcore.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.leymooo.antirelog.Antirelog;

public final class AntiRelogUtils {

  private static Antirelog antiRelog;
  private static boolean antiRelogIsAvailable;

  public static void load() {
    var plugin = Bukkit.getPluginManager().getPlugin("Antirelog");

    if (plugin != null && plugin.isEnabled()) {
      AntiRelogUtils.antiRelog = JavaPlugin.getPlugin(Antirelog.class);
      AntiRelogUtils.antiRelogIsAvailable = true;
    } else {
      AntiRelogUtils.antiRelogIsAvailable = false;
    }
  }

  public static void startPvP(final @NotNull Plugin plugin,
                              final @NotNull Player player,
                              final boolean bypassed,
                              final boolean attacker) {
    if (AntiRelogUtils.antiRelogIsAvailable) {
      ReflectionUtils.invokePrivateMethod(plugin,
          AntiRelogUtils.antiRelog.getPvpManager(),
          "startPvp",
          new Class<?>[]{Player.class, boolean.class, boolean.class},
          player, bypassed, attacker
      );
    }
  }

  public static void stopPvP(final @NotNull Player player) {
    AntiRelogUtils.antiRelog.getPvpManager().stopPvP(player);
  }

  public static void setPvPTime(final @NotNull Plugin plugin, final @NotNull Player player, final int time) {
    if (AntiRelogUtils.isInPvP(player)) {
      ReflectionUtils.invokePrivateMethod(plugin,
          AntiRelogUtils.antiRelog.getPvpManager(),
          "updatePvpMode", new Class<?>[]{Player.class, boolean.class, int.class},
          player,
          false,
          time
      );
    }
  }

  public static boolean isInPvP(final @NotNull Player player) {
    return AntiRelogUtils.antiRelog.getPvpManager().isInPvP(player);
  }
}