package me.xentany.xcore

import me.xentany.xcore.util.hook.PlayerPointsProvider
import me.xentany.xcore.util.hook.VaultEconomyProvider
import me.xentany.xcore.util.hook.luckperms.LuckPermsProvider
import me.xentany.xcore.util.text.TextSerializer
import org.bukkit.plugin.java.JavaPlugin

internal class CorePlugin : JavaPlugin() {

  override fun onEnable() {
    LuckPermsProvider.hook(this)
    PlayerPointsProvider.hook(this)
    VaultEconomyProvider.hook(this)
  }

  override fun onDisable() {
    TextSerializer.clear()
  }
}