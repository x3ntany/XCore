package me.xentany.xcore.util.hook

import me.xentany.xcore.util.bukkit.ServiceProvider
import net.milkbowl.vault.economy.Economy

object VaultEconomyProvider : ServiceProvider<Economy>(Economy::class.java)