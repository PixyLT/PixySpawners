package com.pixylt.pixyspawners.compat;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;


public class GriefPrevention {
    public static boolean check() {
        if(Bukkit.getServer().getPluginManager().getPlugin("GriefPrevention") != null) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean allowBreak(Player player, Block block, Location location) {
        String allowed = me.ryanhamshire.GriefPrevention.GriefPrevention.instance.allowBreak(player, block, location);
        if(allowed == null) return true;
        else return false;
    }
}
