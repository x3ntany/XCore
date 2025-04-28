package me.xentany.xcore.util.hook

import me.xentany.xcore.util.bukkit.ServiceProvider
import org.black_ixx.playerpoints.PlayerPoints
import org.black_ixx.playerpoints.PlayerPointsAPI
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin


object PlayerPointsProvider : ServiceProvider<PlayerPointsAPI>(PlayerPointsAPI::class.java) {

  override fun hook(plugin: Plugin): Boolean {
    val playerPointsEnabled = Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")

    if (playerPointsEnabled) {
      val api = PlayerPoints.getInstance().api

      if (api != null) {
        val field = this::class.java.superclass.getDeclaredField("_instance")

        field.isAccessible = true
        field.set(this, api)

        plugin.slF4JLogger.info("PlayerPointsAPI detected and hooked (direct)")
        return true
      }
    }

    return false
  }
}