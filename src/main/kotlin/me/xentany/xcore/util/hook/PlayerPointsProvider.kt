package me.xentany.xcore.util.hook

import me.xentany.xcore.util.bukkit.ServiceProvider
import org.black_ixx.playerpoints.PlayerPoints

object PlayerPointsProvider : ServiceProvider<PlayerPoints>(PlayerPoints::class.java)