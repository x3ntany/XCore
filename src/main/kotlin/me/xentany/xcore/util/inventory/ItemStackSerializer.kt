package me.xentany.xcore.util.inventory

import org.bukkit.inventory.ItemStack
import java.io.*
import java.util.*

object ItemStackSerializer {

  @JvmStatic
  fun serialize(item: ItemStack): String {
    ByteArrayOutputStream().use { byteStream ->
      ObjectOutputStream(byteStream).use { objectStream ->
        objectStream.writeObject(item)
      }

      return Base64.getEncoder().encodeToString(byteStream.toByteArray())
    }
  }

  @JvmStatic
  fun deserialize(data: String): ItemStack {
    val bytes = Base64.getDecoder().decode(data)

    ByteArrayInputStream(bytes).use { byteStream ->
      ObjectInputStream(byteStream).use { objectStream ->
        return objectStream.readObject() as ItemStack
      }
    }
  }
}