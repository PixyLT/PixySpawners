package com.pixylt.pixyspawners;

import org.bukkit.entity.Player;

public class Permissions {
    public static boolean all(Player player){
        return player.hasPermission("pixyspawners.*") || player.isOp();
    }
    public static boolean help(Player player){
        return player.hasPermission("pixyspawners.help") || all(player);
    }
    public static boolean mine(Player player){
        return player.hasPermission("pixyspawners.mine") || all(player);
    }
    public static boolean get(Player player){
        return player.hasPermission("pixyspawners.get") || all(player);
    }
    public static boolean getGUI(Player player){
        return player.hasPermission("pixyspawners.get.gui") || all(player);
    }
    public static boolean give(Player player){
        return player.hasPermission("pixyspawners.give") || all(player);
    }
    public static boolean reload(Player player){
        return player.hasPermission("pixyspawners.reload") || all(player);
    }
    public static boolean check(Player player){
        return player.hasPermission("pixyspawners.check") || all(player);
    }
    public static boolean stack(Player player){
        return player.hasPermission("pixyspawners.stack") || all(player);
    }
}
