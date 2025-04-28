package me.xentany.xcore.util.inventory

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionEffect
import kotlin.math.abs

class ItemStackComparator {

  private var checkName = false
  private var checkLore = false
  private var checkAmount = false
  private var checkDamage = false
  private var damageTolerance = 0
  private var damagePercentTolerance = 0.0

  fun checks() = this.Checks()

  inner class Checks {
    fun name() = apply { this@ItemStackComparator.checkName = true }
    fun lore() = apply { this@ItemStackComparator.checkLore = true }
    fun amount() = apply { this@ItemStackComparator.checkAmount = true }
    fun damage() = apply { this@ItemStackComparator.checkDamage = true }
    fun and() = this@ItemStackComparator
  }

  fun withDamageTolerance(tolerance: Int) = apply { this.damageTolerance = tolerance }
  fun withDamagePercentTolerance(percent: Double) = apply { this.damagePercentTolerance = percent }

  fun equals(firstItemStack: ItemStack?, secondItemStack: ItemStack?): Boolean {
    if (firstItemStack == null || secondItemStack == null) return false
    if (firstItemStack.type != secondItemStack.type) return false
    if (this.checkAmount && firstItemStack.amount != secondItemStack.amount) return false

    if (this.checkDamage) {
      fun getDamage(item: ItemStack?) = (item?.itemMeta as? Damageable)?.damage

      val firstDamage = getDamage(firstItemStack)
      val secondDamage = getDamage(secondItemStack)

      if (firstDamage != null && secondDamage != null) {
        val different = abs(firstDamage - secondDamage)
        val maxDurability = firstItemStack.type.maxDurability.takeIf { it > 0 } ?: 1
        val percentDifferent = different.toDouble() / maxDurability

        if (this.damageTolerance in 1..<different) return false
        if (this.damagePercentTolerance > 0.0 && percentDifferent > this.damagePercentTolerance) return false
        if (this.damageTolerance == 0 && this.damagePercentTolerance == 0.0 && firstDamage != secondDamage) return false
      } else if (firstDamage != secondDamage) return false
    }

    if (firstItemStack.enchantments != secondItemStack.enchantments) return false

    val firstItemMeta = firstItemStack.itemMeta
    val secondItemMeta = secondItemStack.itemMeta

    if (firstItemMeta == null && secondItemMeta == null) return true
    if (firstItemMeta == null || secondItemMeta == null) return false

    fun stripMeta(itemMeta: ItemMeta): ItemMeta {
      val itemMetaCopy = itemMeta.clone()

      if (!this.checkName) itemMetaCopy.displayName(null)
      if (!this.checkLore) itemMetaCopy.lore(null)
      if (!this.checkDamage && itemMetaCopy is Damageable) itemMetaCopy.damage = 0

      return itemMetaCopy
    }

    if (firstItemMeta is PotionMeta && secondItemMeta is PotionMeta) {
      if (firstItemMeta.basePotionData != secondItemMeta.basePotionData) return false

      fun PotionEffect.key(): String =
        "${this.type.name}:${this.amplifier}:${this.duration}:${this.isAmbient}:${this.hasParticles()}:${this.hasIcon()}"

      val firstEffects = firstItemMeta.customEffects.sortedBy { it.key() }
      val secondEffects = secondItemMeta.customEffects.sortedBy { it.key() }

      if (firstEffects.size != secondEffects.size) return false

      fun PotionEffect.effectEquals(other: PotionEffect) =
        this.type == other.type &&
            this.amplifier == other.amplifier &&
            this.duration == other.duration &&
            this.isAmbient == other.isAmbient &&
            this.hasParticles() == other.hasParticles() &&
            this.hasIcon() == other.hasIcon()

      for (index in firstEffects.indices)
        if (!firstEffects[index].effectEquals(secondEffects[index])) return false

      if (firstItemMeta.hasColor() || secondItemMeta.hasColor())
        if (firstItemMeta.color != secondItemMeta.color) return false

      return stripMeta(firstItemMeta) == stripMeta(secondItemMeta)
    }

    return stripMeta(firstItemMeta) == stripMeta(secondItemMeta)
  }
}