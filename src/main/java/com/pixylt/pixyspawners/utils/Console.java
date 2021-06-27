package com.pixylt.pixyspawners.utils;

import com.pixylt.pixyspawners.PixySpawners;
import com.pixylt.pixyspawners.lang.en;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Console {
    public static PixySpawners Plugin() {
        return JavaPlugin.getPlugin(PixySpawners.class);
    }

    ConsoleCommandSender sender;

    public Console(ConsoleCommandSender inputSender) {
        sender = inputSender;
    }
    public Console() {
        sender = Plugin().getCCS();
    }

    public void log(String message) {
        sender.sendMessage(en.prefixv + message);
    }

    public void error(String error) {
        sender.sendMessage(ChatColor.RED + "[ERROR] " + en.prefixv + ChatColor.WHITE + error);
    }

    public void warn(String error) {
        sender.sendMessage(ChatColor.YELLOW + "[WARN] " + en.prefixv + ChatColor.WHITE + error);
    }

    public void debug(String message) {
        if(Plugin().getVersion().contains("dev")) {
            sender.sendMessage(ChatColor.YELLOW + "[DEBUG] " + en.prefixv + ChatColor.WHITE + message);
        }
    }
}
