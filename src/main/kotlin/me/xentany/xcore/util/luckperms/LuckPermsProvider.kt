package me.xentany.xcore.util.luckperms

import net.luckperms.api.LuckPerms
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import java.util.Optional

object LuckPermsProvider {

  @Volatile
  private var _instance: LuckPerms? = null

  fun get(): LuckPerms? = this._instance

  @JvmStatic
  fun optional(): Optional<LuckPerms> = Optional.ofNullable(this._instance)

  internal fun hook(plugin: Plugin): Boolean {
    val provider = Bukkit.getServicesManager().getRegistration(LuckPerms::class.java)?.provider

    this._instance = provider

    return if (provider != null) {
      plugin.slF4JLogger.info("LuckPerms detected and hooked")
      true
    } else {
      false
    }
  }

  internal fun unhook() {
    this._instance = null
  }
}