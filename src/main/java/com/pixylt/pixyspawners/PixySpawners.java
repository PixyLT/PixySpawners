package com.pixylt.pixyspawners;

import java.util.Objects;

import com.pixylt.pixyspawners.commands.PixySpawner;
import com.pixylt.pixyspawners.commands.Spawners;
import com.pixylt.pixyspawners.compat.GriefPrevention;
import com.pixylt.pixyspawners.events.BlockBreakListener;
import com.pixylt.pixyspawners.events.BlockPlaceListener;
import com.pixylt.pixyspawners.events.EntityExplodeListener;
import com.pixylt.pixyspawners.events.RightClickListener;
import com.pixylt.pixyspawners.lang.en;
import com.pixylt.pixyspawners.utils.Config;
import com.pixylt.pixyspawners.utils.Console;
import com.pixylt.pixyspawners.utils.VersionChecker;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public class PixySpawners extends JavaPlugin {

    private Console console = new Console(Bukkit.getConsoleSender());

    public ConsoleCommandSender getCCS() {
        return Bukkit.getConsoleSender();
    }
    public void disablePlugin() {
        this.setEnabled(false);
    }
    public String getServerVersion() {
        return Bukkit.getVersion();
    }

    @Override
    public void onEnable(){
        // Version check
        if(VersionChecker.Check(Bukkit.getVersion())) {
            console.log(en.versionSupported(Bukkit.getVersion()));
        } else {
            console.warn(en.versionNotSupported(Bukkit.getVersion()));
        }

        // Command registration
        try {
            Objects.requireNonNull(this.getCommand("pixyspawners")).setExecutor(new PixySpawner());
            Objects.requireNonNull(this.getCommand("spawners")).setTabCompleter(new Spawners());
            Objects.requireNonNull(this.getCommand("spawner")).setTabCompleter(new Spawners());
            Objects.requireNonNull(this.getCommand("spawners")).setExecutor(new Spawners());
            Objects.requireNonNull(this.getCommand("spawner")).setExecutor(new Spawners());
        } catch(Exception ex) {
            console.error("Command registration: " + ex.getMessage());
        }
        // Event registration
        try {
            getServer().getPluginManager().registerEvents(new RightClickListener(this), this);
            getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
            getServer().getPluginManager().registerEvents(new BlockPlaceListener(this), this);
            getServer().getPluginManager().registerEvents(new EntityExplodeListener(this), this);
        } catch(Exception ex) {
            console.error("Event registration: " + ex.getMessage());
        }

        // Compatibility check
        if(GriefPrevention.check()) {
            console.log(en.hookedIntoGriefPrevention);
        } else {
            console.debug(en.notHookedIntoGriefPrevention);
        }

        // Config check
        Config config = new Config();
        config.startUpCheck();

        if (!Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays") && config.mainGetB("hologram-enabled")) {
            console.warn("*** HolographicDisplays is not installed or not enabled. ***");
            console.warn("*** This plugin will be disabled. ***");
            console.warn("*** Please install HolographicDisplays or set 'hologram-enabled' to 'false' in the config. ***");
            this.setEnabled(false);
            return;
        } else if(Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays") && config.mainGetB("hologram-enabled")) {
            console.log("Hooked into HolographicDisplays");
        }

        console.log("Started!");
    }

    @Override
    public void onDisable() {
        console.log("Stopped!");
    }

    public String getVersion(){
        return this.getDescription().getVersion();
    }
}