package me.xentany.xcore.util.hook.luckperms

import me.xentany.xcore.util.bukkit.ServiceProvider
import net.luckperms.api.LuckPerms

object LuckPermsProvider : ServiceProvider<LuckPerms>(LuckPerms::class.java)