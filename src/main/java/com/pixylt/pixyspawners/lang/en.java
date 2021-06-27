package com.pixylt.pixyspawners.lang;

import java.awt.Color;

import com.pixylt.pixyspawners.PixySpawners;
import com.pixylt.pixyspawners.utils.VersionChecker;

import org.bukkit.plugin.java.JavaPlugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.md_5.bungee.api.ChatColor;

public class en {
    public static PixySpawners Plugin() {
        return JavaPlugin.getPlugin(PixySpawners.class);
    }
    private static double[] linear(double from, double to, int max) {
        final double[] res = new double[max];
        for (int i = 0; i < max; i++) {
            res[i] = from + i * ((to - from) / (max - 1));
        }
        return res;
    }
    private static String rgbGradient(String str, Color from, Color to) {

        // interpolate each component separately
        final double[] red = linear(from.getRed(), to.getRed(), str.length());
        final double[] green = linear(from.getGreen(), to.getGreen(), str.length());
        final double[] blue = linear(from.getBlue(), to.getBlue(), str.length());
    
        final StringBuilder builder = new StringBuilder();
    
        // create a string that matches the input-string but has
        // the different color applied to each char
        for (int i = 0; i < str.length(); i++) {
            builder.append(ChatColor.of(new Color(
                    (int) Math.round(red[i]),
                    (int) Math.round(green[i]),
                    (int) Math.round(blue[i]))))
                    .append(str.charAt(i));
        }
    
        return builder.toString();
    }

    public static String prefix = prefixNew();
    //  = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PixySpawners" + ChatColor.DARK_GRAY + "] " + ChatColor.BLUE;
    public static String prefixv = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PixySpawners " + Plugin().getVersion() + ChatColor.DARK_GRAY + "] " + ChatColor.BLUE;

    public static String prefixNew() {
        if(VersionChecker.RGB(Plugin().getServerVersion())) {
            return ChatColor.DARK_GRAY + "[" + rgbGradient("PixySpawners", new Color(128, 214, 255), new Color(66, 165, 245)) + ChatColor.DARK_GRAY + "] " + ChatColor.of("#9BE7FF");
        } else {
            return ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PixySpawners" + ChatColor.DARK_GRAY + "] " + ChatColor.BLUE;
        }
    }
    // OLD: Light Purple and Blue
    // Prefix: #80d6ff -> #42a5f5
    // NEW: #9be7ff: #64b5f6

    public static ChatColor primary() {
        if(VersionChecker.RGB(Plugin().getServerVersion())) {
            return ChatColor.of(new Color(155, 231, 255));
        } else {
            return ChatColor.LIGHT_PURPLE;
        }
    }
    public static ChatColor secondary() {
        if(VersionChecker.RGB(Plugin().getServerVersion())) {
            return ChatColor.of(new Color(100, 181, 246));
        } else {
            return ChatColor.BLUE;
        }
    }

    public static String todo = "TODO";
    public static String help = prefix + "Help: \n"+
            primary() + "/pixyspawners " + ChatColor.GRAY + "-" + secondary() + " help command.\n" +
            primary() + "/pixyspawners about " + ChatColor.GRAY + "-" + secondary() + " about plugin.\n" +
            primary() + "/spawners gui " + ChatColor.GRAY + "-" + secondary() + " opens spawners gui.\n" +
            primary() + "/spawners get " + ChatColor.GRAY + "-" + secondary() + " gives spawner.\n" +
            primary() + "/spawners give " + ChatColor.GRAY + "-" + secondary() + " gives spawner to other player.";
    public static String about = prefix + "About: \n"+
            primary() + "Plugin made by TitasLT\n" +
            primary() + "Version: " + Plugin().getVersion() + "\n" +
            primary() + "Report bugs via Discord: Titas#5726 or email titas@pixylt.com";

    public static String versionSupported(String version) {
        return version + " is supported!";
    }
    public static String versionNotSupported(String version) {
        return version + " is not supported!";
    }
    public static String spawnersGetUsage = prefix + "/spawner get <type> <count>";
    public static String spawnersGiveUsage = prefix + "/spawner give <player> <type> <count>";
    public static String spawnersGUIUsage = prefix + "/spawner gui";
    public static String invalidMob = prefix + "Invalid mob.";
    public static String hookedIntoGriefPrevention = "Hooked into GriefPrevention.";
    public static String notHookedIntoGriefPrevention = "GriefPrevention not found (This is not an error).";

    public static String oneOwnerError = prefix + ChatColor.RED + "You are not allowed to break this spawner cause you don't own it!";
    public static String griefPreventionProtected = prefix + ChatColor.RED + "You are not allowed to stack spawners here!";

    public static String notAllowedToBreak = prefix + "You are not allowed to break the spawner.";
    public static String stackSuccess = prefix + "Spawner was stacked successfully!";
    public static String placeSuccess = prefix + "Spawner was placed successfully!";
    public static String silktouchRequired = prefix + "You need silk touch to remove spawners!";
    public static String playerInventoryFull = prefix + "Your inventory is full. Action cancelled.";
    public static String spawnersDoesntMatch = prefix + "Spawners doesn't match!";
    public static String playerNotFound = prefix + "Player was not found.";
    public static String runningLatestVersion = "You are running latest version.";
    public static String runningOutdatedVersion = "There is a new update available.";
    public static String error = prefix + "Error occured.";
    public static String noConsole = prefix + "This command isn't allowed on console.";
    public static String missingPermission(String permission) {
        return prefix + ChatColor.RED + "Missing permissions! (" + permission + ")";
    }
    public static String getGiveSpawners(int amount, String spawnerType) {
        if(amount == 1){
            return prefix + primary() + ChatColor.BOLD + "" + amount + " " + spawnerType + secondary() + " Spawner was added to your inventory.";
        } else {
            return prefix + primary() + ChatColor.BOLD + "" + amount + " " + spawnerType + secondary() + " Spawners were added to your inventory.";
        }    
    }
    public static String getSpawnersRemoved(int amount, String spawnerType){
        String spawnerName = spawnerType.toLowerCase().replace("_", " ");
        spawnerName = spawnerName.substring(0, 1).toUpperCase() + spawnerName.substring(1);
        if(amount == 1){
            return prefix + primary() + ChatColor.BOLD + "" + amount + " " + spawnerName + secondary() + " Spawner was removed.";
        } else {
            return prefix + primary() + ChatColor.BOLD + "" + amount + " " + spawnerName + secondary() + " Spawners were removed.";
        }
    }
    public static Component getSpawnerItemName(String spawnerName){
        final TextComponent textComponent = Component.text(spawnerName)
            .color(NamedTextColor.BLUE)
            .decoration(TextDecoration.BOLD, true)
            .append(
                Component.text(" Spawner")
                    .color(NamedTextColor.BLUE)   
            );
        // Component.text("" + ChatColor.BLUE + ChatColor.BOLD + spawnerName + ChatColor.BLUE + " Spawner");
        return textComponent;
    }
    public static String getRightClickSpawner(int amount, String spawnerType, String owner, Boolean showOwner){
        String spawnerName = spawnerType.toLowerCase().replace("_", " ");
        spawnerName = spawnerName.substring(0, 1).toUpperCase() + spawnerName.substring(1);
        if(amount == 1){
            if(showOwner) {
                return prefix + primary() + ChatColor.BOLD + amount + " " + spawnerName + secondary() + " Spawner | Owned by: " + primary() + ChatColor.BOLD + owner;
            } else {
                return prefix + primary() + ChatColor.BOLD + amount + " " + spawnerName + secondary() + " Spawner";
            }
        } else {
            if(showOwner) {
                return prefix + primary() + ChatColor.BOLD + amount + " " + spawnerName + secondary() + " Spawners | Owned by: " + primary() + ChatColor.BOLD + owner;
            } else {
                return prefix + primary() + ChatColor.BOLD + amount + " " + spawnerName + secondary() + " Spawners";
            }
        }
    }
    public static String getHoloName(int amount, String spawnerType){
        String spawnerName = spawnerType.toLowerCase().replace("_", " ");
        spawnerName = spawnerName.substring(0, 1).toUpperCase() + spawnerName.substring(1);
        if(amount == 1){
            return "" + primary() + ChatColor.BOLD + amount + " " + spawnerName + secondary() + " Spawner";
        } else {
            return "" + primary() + ChatColor.BOLD + amount + " " + spawnerName + secondary() + " Spawners";
        }
    }
    public static Component guiName = Component.text("" + ChatColor.BLUE + ChatColor.BOLD + "Select spawner type");
    public static Component textToComp(String text) {
        final TextComponent textComponent = Component.text(text);
        return textComponent;
    }
    public static String getSpawnerGive(String receiver, int amount, String spawnerType){
        if(amount == 1){
            return prefix + "Giving " + primary() + ChatColor.BOLD + receiver + " " + amount + " " + spawnerType + secondary() + " spawner.";
        } else {
            return prefix + "Giving " + primary() + ChatColor.BOLD + receiver + " " + amount + " " + spawnerType + secondary() + " spawners.";
        }
    }
    public static String getSpawnerGiveReceiver(String sender, int amount, String spawnerType){
        if(amount == 1){
            return prefix + primary() + ChatColor.BOLD + sender + secondary() + " gave you " + primary() + ChatColor.BOLD + amount + " " + spawnerType + secondary() + " spawner.";
        } else {
            return prefix + primary() + ChatColor.BOLD + sender + secondary() + " gave you " + primary() + ChatColor.BOLD + amount + " " + spawnerType + secondary() + " spawners.";
        }
    }
    public static String getGivingSpawners(int amount, String spawnerType){
        if(amount == 1){
            return prefix + "Giving " + primary() + ChatColor.BOLD + amount + " " + spawnerType + secondary() + " spawner.";
        } else {
            return prefix + "Giving " + primary() + ChatColor.BOLD + amount + " " + spawnerType + secondary() + " spawners.";
        }
    }
    public static String maxSpawnersReached(int amount, int maxAmount) {
        return prefix + "Max amount of spawners reached! (" + amount + "/" + maxAmount + ")";
    }
}
