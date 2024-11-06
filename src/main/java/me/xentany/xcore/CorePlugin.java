package me.xentany.xcore;

import org.bukkit.plugin.java.JavaPlugin;

public final class CorePlugin extends JavaPlugin {

  private static CorePlugin instance;

  @Override
  public void onEnable() {
    CorePlugin.instance = this;
  }
  
  @Override
  public void onDisable() {
  }
  
  public static CorePlugin getInstance() {
    return CorePlugin.instance;
  }
}