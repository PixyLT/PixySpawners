package com.pixylt.pixyspawners;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class LangEn {
    public static PixySpawners Plugin() {
        return JavaPlugin.getPlugin(PixySpawners.class);
    }

    public static String versionSupported(String version){
        return Globals.prefixv + version + " is supported!";
    }
    public static String versionNotSupported(String version){
        return Globals.prefixv + version + " is not supported!";
    }

    public static String help = Globals.prefix + "Help: \n"+
            ChatColor.LIGHT_PURPLE + "/pixyspawners " + ChatColor.GRAY + "-" + ChatColor.BLUE + " help command.\n" +
            ChatColor.LIGHT_PURPLE + "/pixyspawners about " + ChatColor.GRAY + "-" + ChatColor.BLUE + " about plugin.\n" +
            ChatColor.LIGHT_PURPLE + "/pixyspawners reload " + ChatColor.GRAY + "-" + ChatColor.BLUE + " reload config.\n" +
            ChatColor.LIGHT_PURPLE + "/spawners get " + ChatColor.GRAY + "-" + ChatColor.BLUE + " gives spawner.\n" +
            ChatColor.LIGHT_PURPLE + "/spawners give " + ChatColor.GRAY + "-" + ChatColor.BLUE + " gives spawner to other player.";
    public static String about = Globals.prefix + "About: \n"+
            ChatColor.LIGHT_PURPLE + "Plugin made by TitasLT\n" +
            ChatColor.LIGHT_PURPLE + "Version: " + Plugin().getVersion() + "\n" +
            ChatColor.LIGHT_PURPLE + "Report bugs via Discord: Titas#5726 or email titas@pixylt.com";
    public static String missingPerm = Globals.prefix + "Missing permission!";
    public static String noConsole = Globals.prefix + "This command isn't allowed on console.";
    public static String spawnersGetUsage = Globals.prefix + "/spawner get <type> <count>";
    public static String spawnersGiveUsage = Globals.prefix + "/spawner give <player> <type> <count>";
    public static String spawnersGUIUsage = Globals.prefix + "/spawner gui";
    public static String invalidMob = Globals.prefix + "Invalid mob.";
    public static String getGivingSpawners(int amount, String spawnerType){
        if(amount == 1){
            return Globals.prefix + "Giving " + ChatColor.LIGHT_PURPLE + amount + " " + spawnerType + ChatColor.BLUE + " spawner.";
        } else {
            return Globals.prefix + "Giving " + ChatColor.LIGHT_PURPLE + amount + " " + spawnerType + ChatColor.BLUE + " spawners.";
        }
    }
    public static String getSpawnerName(int amount, String spawnerName){
        if(amount == 1){
            return ChatColor.LIGHT_PURPLE + "" + amount + " " + spawnerName + ChatColor.BLUE + " Spawner";
        } else {
            return ChatColor.LIGHT_PURPLE + "" + amount + " " + spawnerName + ChatColor.BLUE + " Spawners";
        }
    }
    public static String getSpawnerItemName(String spawnerName){
        return ChatColor.BLUE + spawnerName + ChatColor.LIGHT_PURPLE + " Spawner";
    }
    public static String getRightClickSpawner(int amount, String spawnerType){
        String spawnerName = spawnerType.toLowerCase().replace("_", " ");
        spawnerName = spawnerName.substring(0, 1).toUpperCase() + spawnerName.substring(1);
        if(amount == 1){
            return Globals.prefix + ChatColor.LIGHT_PURPLE + "" + amount + " " + spawnerName + ChatColor.BLUE + " Spawner";
        } else {
            return Globals.prefix + ChatColor.LIGHT_PURPLE + "" + amount + " " + spawnerName + ChatColor.BLUE + " Spawners";
        }
    }
    public static String spawnersDoesntMatch = Globals.prefix + "Spawners doesn't match!";
    public static String error = Globals.prefix + "Error occured.";
    public static String stackSuccess = Globals.prefix + "Spawner was stacked successfully!";
    public static String silktouchRequired = Globals.prefix + "You need silk touch to remove spawners!";
    public static String getSpawnersRemoved(int amount, String spawnerType){
        String spawnerName = spawnerType.toLowerCase().replace("_", " ");
        spawnerName = spawnerName.substring(0, 1).toUpperCase() + spawnerName.substring(1);
        if(amount == 1){
            return Globals.prefix + ChatColor.LIGHT_PURPLE + "" + amount + " " + spawnerName + ChatColor.BLUE + " Spawner was removed.";
        } else {
            return Globals.prefix + ChatColor.LIGHT_PURPLE + "" + amount + " " + spawnerName + ChatColor.BLUE + " Spawners were removed.";
        }
    }
    public static String configReloaded = Globals.prefixv + "Config was reloaded!";
    public static String openingGUI = Globals.prefix + "Opening gui...";
    public static String getSpawnerGive(String receiver, int amount, String spawnerType){
        if(amount == 1){
            return Globals.prefix + "Giving " + ChatColor.LIGHT_PURPLE + receiver + " " + amount + " " + spawnerType + ChatColor.BLUE + " spawner.";
        } else {
            return Globals.prefix + "Giving " + ChatColor.LIGHT_PURPLE + receiver + " " + amount + " " + spawnerType + ChatColor.BLUE + " spawners.";
        }
    }
    public static String getSpawnerGiveReceiver(String sender, int amount, String spawnerType){
        if(amount == 1){
            return Globals.prefix + ChatColor.LIGHT_PURPLE + sender + ChatColor.BLUE + " gave you " + ChatColor.LIGHT_PURPLE + amount + " " + spawnerType + ChatColor.BLUE + " spawner.";
        } else {
            return Globals.prefix + ChatColor.LIGHT_PURPLE + sender + ChatColor.BLUE + " gave you " + ChatColor.LIGHT_PURPLE + amount + " " + spawnerType + ChatColor.BLUE + " spawners.";
        }
    }
//    ChatColor.of(hex);
    public static String playerInventoryFull = Globals.prefix + "Your inventory is full. Action cancelled.";
    public static String playerNotFound = Globals.prefix + "Player was not found.";
    public static String runningLatestVersion = Globals.prefixv + "You are running latest version.";
    public static String runningOutdatedVersion = Globals.prefixv + "There is a new update available.";
    public static String hookedIntoGriefPrevention = Globals.prefix + "Hooked into GriefPrevention.";
    public static String notHookedIntoGriefPrevention = Globals.prefix + "GriefPrevention not found (This is not an error).";
    public static String notAllowedToBreak = Globals.prefix + "You are not allowed to break the spawner.";
}
