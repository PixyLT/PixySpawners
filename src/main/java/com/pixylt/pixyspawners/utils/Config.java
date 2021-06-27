package com.pixylt.pixyspawners.utils;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;
import com.pixylt.pixyspawners.PixySpawners;
import com.pixylt.pixyspawners.classes.Spawner;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {
    public static PixySpawners Plugin() {
        return JavaPlugin.getPlugin(PixySpawners.class);
    }
    
    FileConfiguration mainConfig;
    FileConfiguration spawnersConfig;
    File mainConfigFile;
    File spawnersConfigFile;
    Console console = new Console();

    public Config() {

        mainConfigFile = new File(Plugin().getDataFolder(), "config.yml");
        spawnersConfigFile = new File(Plugin().getDataFolder(), "spawners.yml");

        if(!mainConfigFile.exists()) {
            Plugin().saveDefaultConfig();
        }
        mainConfig = YamlConfiguration.loadConfiguration(mainConfigFile);
        spawnersConfig = YamlConfiguration.loadConfiguration(spawnersConfigFile);
    }
    public void startUpCheck() {
        String configVal1 = mainGet("require-silktouch");
        String configVal2 = mainGet("can-remove-without-silktouch");
        String configVal3 = mainGet("spawner-can-be-destroyed");
        String configVal4 = mainGet("spawner-drop-on-explosion");
        String configVal5 = mainGet("max-spawners");
        String configVal6 = mainGet("show-spawner-owner");
        String configVal7 = mainGet("one-owner");
        String configVal8 = mainGet("hologram-enabled");
        Boolean errored = false;
        if(configVal1 != "true" && configVal1 != "false") {
            errored = true;
            console.error("'require-silktouch' value is incorrect! (Valid values: 'true', 'false')");
        }
        if(configVal2 != "true" && configVal2 != "false") {
            errored = true;
            console.error("'can-remove-without-silktouch' value is incorrect! (Valid values: 'true', 'false')");
        }
        if(configVal3 != "true" && configVal3 != "false") {
            errored = true;
            console.error("'spawner-can-be-destroyed' value is incorrect! (Valid values: 'true', 'false')");
        }
        if(configVal4 != "true" && configVal4 != "false") {
            errored = true;
            console.error("'spawner-drop-on-explosion' value is incorrect! (Valid values: 'true', 'false')");
        }
        try {
            Integer.parseInt(configVal5);
        } catch (Exception e) {
            errored = true;
            console.error("'max-spawners' value is incorrect! (Valid values: Integer(number))");
        }
        if(configVal6 != "true" && configVal6 != "false") {
            errored = true;
            console.error("'show-spawner-owner' value is incorrect! (Valid values: 'true', 'false')");
        }
        if(configVal7 != "true" && configVal7 != "false") {
            errored = true;
            console.error("'one-owner' value is incorrect! (Valid values: 'true', 'false')");
        }
        if(configVal8 != "true" && configVal8 != "false") {
            errored = true;
            console.error("'hologram-enabled' value is incorrect! (Valid values: 'true', 'false')");
        }
        if(!errored) {
            console.log("Config check passed!");
        } else {
            console.error("Plugin will be disabled!");
            Plugin().disablePlugin();
        }
    }
    public void check() {
        // TODO
    }
    public String mainGet(String get) {
        reload();
        return mainConfig.getString(get);
    }
    public Boolean mainGetB(String get) {
        reload();
        return mainConfig.getBoolean(get);
    }
    
    public Spawner getSpawner(String xyz) {
        reload();

        if(spawnersConfig.get(xyz) != null) {
            Gson gson = new Gson();
            String json = spawnersConfig.get(xyz).toString();
            Spawner spawner = gson.fromJson(json, Spawner.class);
            return spawner;
        } else {
            return null;
        }
    }

    public void breakSpawner(String xyz){
        reload();
        spawnersConfig.set(xyz, null);
        try {
            spawnersConfig.save(spawnersConfigFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean spawnerExists(String xyz) {
        reload();
        if(spawnersConfig.get(xyz) != null) return true;
        else return false;
    }

    public void reload() {
        mainConfig = YamlConfiguration.loadConfiguration(mainConfigFile);
        spawnersConfig = YamlConfiguration.loadConfiguration(spawnersConfigFile);
    }
    public boolean stackSpawner(String xyz, String owner, int amount, String type) {
        reload();
        Gson gson = new Gson();
        spawnersConfig.set(xyz, gson.toJson(new Spawner(xyz, owner, amount, type)));
        try {
            spawnersConfig.save(spawnersConfigFile);
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}
