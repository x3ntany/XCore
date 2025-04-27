package me.xentany.xcore.util.inventory

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionEffect

class ItemStackComparator {

  private var checkName = false
  private var checkLore = false
  private var checkAmount = false
  private var checkDamage = false
  private var damageTolerance = 0
  private var damagePercentTolerance = 0.0

  fun checks(): Checks {
    return Checks(this)
  }

  class Checks(private val parent: ItemStackComparator) {

    fun name(): Checks {
      this.parent.checkName = true
      return this
    }

    fun lore(): Checks {
      this.parent.checkLore = true
      return this
    }

    fun amount(): Checks {
      this.parent.checkAmount = true
      return this
    }

    fun damage(): Checks {
      this.parent.checkDamage = true
      return this
    }

    fun and(): ItemStackComparator {
      return this.parent
    }
  }

  fun withDamageTolerance(tolerance: Int): ItemStackComparator {
    this.damageTolerance = tolerance
    return this
  }

  fun withDamagePercentTolerance(percent: Double): ItemStackComparator {
    this.damagePercentTolerance = percent
    return this
  }

  fun equals(firstItemStack: ItemStack?, secondItemStack: ItemStack?): Boolean {
    if (firstItemStack == null || secondItemStack == null) {
      return false
    }

    if (firstItemStack.type != secondItemStack.type) {
      return false
    }

    if (this.checkAmount && firstItemStack.amount != secondItemStack.amount) {
      return false
    }

    if (this.checkDamage) {
      fun getDamage(item: ItemStack?): Int? {
        return (item?.itemMeta as? Damageable)?.damage
      }

      val firstDamage = getDamage(firstItemStack)
      val secondDamage = getDamage(secondItemStack)

      if (firstDamage != null && secondDamage != null) {
        val different = kotlin.math.abs(firstDamage - secondDamage)
        val maxDurability = firstItemStack.type.maxDurability.takeIf { it > 0 } ?: 1
        val percentDifferent = different.toDouble() / maxDurability

        if (this.damageTolerance in 1..<different) {
          return false
        }

        if (this.damagePercentTolerance > 0.0 && percentDifferent > this.damagePercentTolerance) {
          return false
        }

        if (this.damageTolerance == 0 && this.damagePercentTolerance == 0.0 && firstDamage != secondDamage) {
          return false
        }
      } else if (firstDamage != secondDamage) {
        return false
      }
    }

    if (firstItemStack.enchantments != secondItemStack.enchantments) {
      return false
    }

    val firstItemMeta = firstItemStack.itemMeta
    val secondItemMeta = secondItemStack.itemMeta

    if (firstItemMeta == null && secondItemMeta == null) {
      return true
    }

    if (firstItemMeta == null || secondItemMeta == null) {
      return false
    }

    if (firstItemMeta is PotionMeta && secondItemMeta is PotionMeta) {
      if (firstItemMeta.basePotionData != secondItemMeta.basePotionData) {
        return false
      }

      val firstEffects = firstItemMeta.customEffects.sortedBy {
        it.key()
      }

      val secondEffects = secondItemMeta.customEffects.sortedBy {
        it.key()
      }

      if (firstEffects.size != secondEffects.size) {
        return false
      }

      for (index in firstEffects.indices) {
        if (!firstEffects[index].effectEquals(secondEffects[index])) {
          return false
        }
      }

      if (firstItemMeta.hasColor() || secondItemMeta.hasColor()) {
        if (firstItemMeta.color != secondItemMeta.color) {
          return false
        }
      }

      return this.stripMeta(firstItemMeta) == this.stripMeta(secondItemMeta)
    }

    return this.stripMeta(firstItemMeta) == this.stripMeta(secondItemMeta)
  }

  private fun stripMeta(itemMeta: ItemMeta): ItemMeta {
    val itemMetaCopy = itemMeta.clone()

    if (!this.checkName) {
      itemMetaCopy.displayName(null)
    }

    if (!this.checkLore) {
      itemMetaCopy.lore(null)
    }

    if (!this.checkDamage && itemMetaCopy is Damageable) {
      itemMetaCopy.damage = 0
    }

    return itemMetaCopy
  }

  private fun PotionEffect.key(): String {
    return "${this.type.name}:${this.amplifier}:${this.duration}:${this.isAmbient}:${this.hasParticles()}:${this.hasIcon()}"
  }

  private fun PotionEffect.effectEquals(other: PotionEffect): Boolean {
    return this.type == other.type &&
        this.amplifier == other.amplifier &&
        this.duration == other.duration &&
        this.isAmbient == other.isAmbient &&
        this.hasParticles() == other.hasParticles() &&
        this.hasIcon() == other.hasIcon()
  }
}