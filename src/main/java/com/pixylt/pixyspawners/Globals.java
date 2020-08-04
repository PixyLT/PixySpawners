package com.pixylt.pixyspawners;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Globals {
    public static PixySpawners Plugin() {
        return JavaPlugin.getPlugin(PixySpawners.class);
    }

    public static String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PixySpawners" + ChatColor.DARK_GRAY + "] " + ChatColor.BLUE;
    public static String prefixv = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PixySpawners " + Plugin().getVersion() + ChatColor.DARK_GRAY + "] " + ChatColor.BLUE;
}
