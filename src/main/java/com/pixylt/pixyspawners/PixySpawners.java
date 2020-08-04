package com.pixylt.pixyspawners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PixySpawners extends JavaPlugin {
    @Override
    public void onEnable(){
        Config.saveDefaultConfig();
        this.getCommand("pixyspawners").setExecutor(new PixySpawnersCommand());
        this.getCommand("spawners").setExecutor(new SpawnersCommand());
        getServer().getPluginManager().registerEvents(new RightClickListener(this), this);
        getServer().getPluginManager().registerEvents(new EntityExplodeListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new HoloProtectionListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);


        Bukkit.getConsoleSender().sendMessage(Globals.prefix + "Is ready!");
    }
    @Override
    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage(Globals.prefix + "Stopped...");
    }

    public String getVersion(){
        return this.getDescription().getVersion();
    }
}
