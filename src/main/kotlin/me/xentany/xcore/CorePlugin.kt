package me.xentany.xcore

import me.xentany.xcore.util.text.serializer.TextSerializerVault
import org.bukkit.plugin.java.JavaPlugin

internal class CorePlugin : JavaPlugin() {

  override fun onEnable() {
    this.slF4JLogger.info(
      TextSerializerVault.get(this).toStringMiniMessage(
        "<green>XCore version ${description.version} successfully launched"
      )
    )
  }

  override fun onDisable() {
    TextSerializerVault.clear()
  }
}