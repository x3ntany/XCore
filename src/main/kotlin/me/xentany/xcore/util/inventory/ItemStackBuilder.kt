package me.xentany.xcore.util.inventory

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.Plugin

class ItemStackBuilder(material: Material, amount: Int = 1) {

  private val item = ItemStack(material, amount)
  private val meta: ItemMeta = this.item.itemMeta

  fun amount(amount: Int) = apply { this.item.amount = amount }

  fun name(component: Component) = apply { this.meta.displayName(component) }

  fun lore(vararg lines: Component) = apply { this.meta.lore(lines.map { it }) }

  fun lore(lines: Collection<Component>) = apply { this.meta.lore(lines.toList()) }

  fun enchant(enchant: Enchantment, level: Int, ignoreLevelRestriction: Boolean = true) = apply {
    this.meta.addEnchant(enchant, level, ignoreLevelRestriction)
  }

  fun flag(vararg flags: ItemFlag) = apply { this.meta.addItemFlags(*flags) }

  fun unbreakable(flag: Boolean = true) = apply { this.meta.isUnbreakable = flag }

  fun customModelData(data: Int) = apply { this.meta.setCustomModelData(data) }

  fun <T, Z : Any> persistent(
    plugin: Plugin,
    key: String,
    type: PersistentDataType<T, Z>,
    value: Z
  ) = apply {
    this.meta.persistentDataContainer.set(NamespacedKey(plugin, key), type, value)
  }

  fun meta(block: ItemMeta.() -> Unit) = apply { this.meta.block() }

  fun build(): ItemStack {
    this.item.itemMeta = this.meta

    return this.item
  }
}