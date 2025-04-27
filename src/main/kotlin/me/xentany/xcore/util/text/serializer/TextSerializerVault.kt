package me.xentany.xcore.util.text.serializer

import me.xentany.xcore.util.text.TextFormatter
import org.bukkit.plugin.java.JavaPlugin
import java.util.concurrent.ConcurrentHashMap

object TextSerializerVault {

  private val map = ConcurrentHashMap<JavaPlugin, TextSerializer>()

  @JvmStatic
  fun get(any: Any): TextSerializer {
    val plugin = JavaPlugin.getProvidingPlugin(any.javaClass)

    return this.map.computeIfAbsent(plugin) {
      println(
        TextFormatter.format(
          "[XCore#TextSerializerVault] New instance created for plugin: {plugin}", mapOf("plugin" to plugin.name)
        )
      )

      TextSerializer()
    }
  }

  internal fun clear() {
    this.map.clear()
  }
}