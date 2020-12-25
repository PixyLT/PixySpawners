package com.pixylt.pixyspawners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public class PixySpawners extends JavaPlugin {
    @Override
    public void onEnable(){
        if(VersionChecker.Check(Bukkit.getVersion())){
            Bukkit.getConsoleSender().sendMessage(LangEn.versionSupported(Bukkit.getVersion()));
        } else {
            Bukkit.getConsoleSender().sendMessage(LangEn.versionNotSupported(Bukkit.getVersion()));
        }
        Logger logger = this.getLogger();
        new UpdateChecker(this, 75396).getVersion(version -> {
            if(this.getDescription().getVersion().equalsIgnoreCase(version)) {
                Bukkit.getConsoleSender().sendMessage(LangEn.runningLatestVersion);
            } else {
                Bukkit.getConsoleSender().sendMessage(LangEn.runningOutdatedVersion);
            }
        });
        ConfigLegacy.saveDefaultConfig();
        Metrics metrics = new Metrics(this, 8725);
        try {
            Objects.requireNonNull(this.getCommand("pixyspawners")).setExecutor(new PixySpawnersCommand());
            Objects.requireNonNull(this.getCommand("spawners")).setExecutor(new SpawnersCommand());
        } catch(Exception ex){
            Bukkit.getConsoleSender().sendMessage(LangEn.error);
        }
        if(GriefPrevention.check()){
            Bukkit.getConsoleSender().sendMessage(LangEn.hookedIntoGriefPrevention);
        } else {
            Bukkit.getConsoleSender().sendMessage(LangEn.notHookedIntoGriefPrevention);
        }
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
