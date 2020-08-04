package com.pixylt.pixyspawners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class Config {
    public static PixySpawners Plugin() {
        return JavaPlugin.getPlugin(PixySpawners.class);
    }
    private static File spawnerConfigFile = null;
    private static FileConfiguration spawnerConfig = null;
    private static File holoConfigFile = null;
    private static FileConfiguration holoConfig = null;

    public static void saveDefaultConfig() {
        Plugin().saveDefaultConfig();
    }

    public static FileConfiguration getConfig(){
        return Plugin().getConfig();
    }
    public static void saveConfig(){
        Plugin().saveConfig();
    }
    public static void reloadConfig(){
        Plugin().reloadConfig();
    }
    public static void reloadSpawnersConfig(){
        if (spawnerConfigFile == null) {
            spawnerConfigFile = new File(Plugin().getDataFolder(), "spawners.yml");
        }
        spawnerConfig = YamlConfiguration.loadConfiguration(spawnerConfigFile);
    }
    public static FileConfiguration getSpawnerConfig(){
        if(spawnerConfig == null){
            reloadSpawnersConfig();
        }
        return spawnerConfig;
    }
    public static boolean saveSpawnerConfig(){
        if(spawnerConfig == null || spawnerConfigFile == null){
            return false;
        }
        try {
            getSpawnerConfig().save(spawnerConfigFile);
            return true;
        } catch (IOException ex){
            Plugin().getLogger().log(Level.SEVERE, "Could not save config to " + spawnerConfigFile, ex);
            return false;
        }
    }

    public static void reloadHoloConfig(){
        if (holoConfigFile == null) {
            holoConfigFile = new File(Plugin().getDataFolder(), "holo.yml");
        }
        holoConfig = YamlConfiguration.loadConfiguration(holoConfigFile);
    }
    public static FileConfiguration getHoloConfig(){
        if(holoConfig == null){
            reloadHoloConfig();
        }
        return holoConfig;
    }
    public static boolean saveHoloConfig(){
        if(holoConfig == null || holoConfigFile == null){
            return false;
        }
        try {
            getHoloConfig().save(holoConfigFile);
            return true;
        } catch (IOException ex){
            Plugin().getLogger().log(Level.SEVERE, "Could not save config to " + spawnerConfigFile, ex);
            return false;
        }
    }
    public static void check(Player p){
        FileConfiguration mainConfig = getConfig();
        if(mainConfig.getString("require-silktouch") != "true"){
            if(mainConfig.getString("require-silktouch") != "false"){
                p.sendMessage(Globals.prefix + "\"require-silktouch\" didn\'t return right value!");
            }
        }
        if(mainConfig.getString("can-remove-without-silktouch") != "true"){
            if(mainConfig.getString("can-remove-without-silktouch") != "false"){
                p.sendMessage(Globals.prefix + "\"can-remove-without-silktouch\" didn\'t return right value!");
            }
        }
        if(mainConfig.getString("spawner-can-be-destroyed") != "true"){
            if(mainConfig.getString("spawner-can-be-destroyed") != "false"){
                p.sendMessage(Globals.prefix + "\"spawner-can-be-destroyed\" didn\'t return right value!");
            }
        }
        if(mainConfig.getString("spawner-drop-on-explosion") != "true"){
            if(mainConfig.getString("spawner-drop-on-explosion") != "false"){
                p.sendMessage(Globals.prefix + "\"spawner-drop-on-explosion\" didn\'t return right value!");
            }
        }
    }
}
