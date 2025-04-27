package me.xentany.xcore.util.text.serializer

import me.xentany.xcore.util.text.TextFormatter
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

class TextSerializer(
  var miniMessage: MiniMessage = fetchMiniMessageInstance(),
  var legacy: LegacyComponentSerializer = LegacyComponentSerializer.legacySection(),
  val plain: PlainTextComponentSerializer = PlainTextComponentSerializer.plainText(),
  var gson: GsonComponentSerializer = GsonComponentSerializer.builder().downsampleColors().build()
) {

  fun configure(
    miniMessage: MiniMessage = this.miniMessage,
    legacy: LegacyComponentSerializer = this.legacy,
    gson: GsonComponentSerializer = this.gson
  ) {
    this.miniMessage = miniMessage
    this.legacy = legacy
    this.gson = gson
  }

  fun fromMiniMessage(component: Component): String =
    this.miniMessage.serialize(component)

  fun toMiniMessage(string: String, values: Map<String, Any?> = emptyMap()): Component =
    this.miniMessage.deserialize(TextFormatter.format(string, values))

  fun fromLegacy(component: Component): String =
    this.legacy.serialize(component)

  fun toLegacy(string: String, values: Map<String, Any?> = emptyMap()): Component =
    this.legacy.deserialize(TextFormatter.format(string, values))

  fun fromPlain(component: Component): String =
    this.plain.serialize(component)

  fun toPlain(string: String, values: Map<String, Any?> = emptyMap()): Component =
    this.plain.deserialize(TextFormatter.format(string, values))

  fun fromJson(component: Component): String =
    this.gson.serialize(component)

  fun toJson(string: String, values: Map<String, Any?> = emptyMap()): Component =
    this.gson.deserialize(TextFormatter.format(string, values))

  fun toStringMiniMessage(string: String, values: Map<String, Any?> = emptyMap()): String =
    this.fromLegacy(this.toMiniMessage(string, values))

  companion object {

    private fun fetchMiniMessageInstance(): MiniMessage {
      return try {
        val method = MiniMessage::class.java.getDeclaredMethod("get")

        method.invoke(null) as MiniMessage
      } catch (exception: NoSuchMethodException) {
        MiniMessage.miniMessage()
      }
    }
  }
}