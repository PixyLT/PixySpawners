package com.pixylt.pixyspawners;
import org.bukkit.plugin.java.JavaPlugin;

public class PixySpawners extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}
