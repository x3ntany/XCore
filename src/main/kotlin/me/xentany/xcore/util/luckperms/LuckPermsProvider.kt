package me.xentany.xcore.util.luckperms

import net.luckperms.api.LuckPerms
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import java.util.Optional

object LuckPermsProvider {

  @Volatile
  private var _instance: LuckPerms? = null

  fun get(): LuckPerms? {
    return _instance
  }

  @JvmStatic
  fun optional(): Optional<LuckPerms> {
    return Optional.ofNullable(_instance)
  }

  internal fun hook(plugin: Plugin): Boolean {
    val provider = Bukkit.getServicesManager().getRegistration(LuckPerms::class.java)?.provider

    _instance = provider

    if (provider != null) {
      plugin.slF4JLogger.info("LuckPerms detected and hooked")
      return true
    } else {
      return false
    }
  }

  internal fun unhook() {
    _instance = null
  }
}