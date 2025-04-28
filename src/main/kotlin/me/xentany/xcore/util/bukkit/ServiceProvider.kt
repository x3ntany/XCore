package me.xentany.xcore.util.bukkit

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import java.util.Optional

open class ServiceProvider<T : Any>(private val clazz: Class<T>) {

  @Volatile
  private var _instance: T? = null

  fun get(): T? = this._instance

  fun optional(): Optional<T> = Optional.ofNullable(this._instance)

  open fun hook(plugin: Plugin): Boolean {
    val provider = Bukkit.getServicesManager().getRegistration(clazz)?.provider

    this._instance = provider

    return if (provider != null) {
      plugin.slF4JLogger.info("${clazz.simpleName} detected and hooked")
      true
    } else {
      false
    }
  }
}