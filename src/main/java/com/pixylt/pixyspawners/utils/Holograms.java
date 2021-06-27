package com.pixylt.pixyspawners.utils;


import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.pixylt.pixyspawners.PixySpawners;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Holograms {
    public static PixySpawners Plugin() {
        return JavaPlugin.getPlugin(PixySpawners.class);
    }

    static Plugin plugin = Plugin();
    static Console console = new Console();

    public static void placeSpawner(Location loc, String text, ItemStack is) {
        loc = loc.add(0.5, 2.5, 0.5);
        Hologram hologram = HologramsAPI.createHologram(plugin, loc);
        if(is != null) {
            hologram.appendItemLine(is);
            hologram.appendTextLine("");
        }
        hologram.appendTextLine(text);
    }

    public static void removeSpawner(Location loc) {
        loc = loc.add(0.5, 2.5, 0.5);
        Double locX = loc.getX();
        Double locY = loc.getY();
        Double locZ = loc.getZ();
        for(Hologram hologram : HologramsAPI.getHolograms(plugin)) {
            if(hologram.getX() == locX && hologram.getY() == locY && hologram.getZ() == locZ) {
                hologram.delete();
                return;
            }
        }
    }

    public static void updateSpawner(Location loc, String text, ItemStack is) {
        loc = loc.add(0.5, 2.5, 0.5);
        Double locX = loc.getX();
        Double locY = loc.getY();
        Double locZ = loc.getZ();

        for(Hologram hologram : HologramsAPI.getHolograms(plugin)) {
            if(hologram.getX() == locX && hologram.getY() == locY && hologram.getZ() == locZ) {
                hologram.clearLines();
                if(is != null) {
                    hologram.appendItemLine(is);
                    hologram.appendTextLine("");
                }
                hologram.appendTextLine(text);
                return;
            }
        }
    }

    public static Boolean exists(Location loc) {
        loc = loc.add(0.5, 2.5, 0.5);
        Double locX = loc.getX();
        Double locY = loc.getY();
        Double locZ = loc.getZ();
        for(Hologram hologram : HologramsAPI.getHolograms(plugin)) {
            if(hologram.getX() == locX && hologram.getY() == locY && hologram.getZ() == locZ) {
                return true;
            }
        }
        return false;
    }

    public static void removeAllHolos() {
        for(Hologram hologram : HologramsAPI.getHolograms(plugin)) {
            hologram.delete();
        }
    }
}
