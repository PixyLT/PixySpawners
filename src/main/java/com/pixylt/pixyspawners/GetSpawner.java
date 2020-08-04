package com.pixylt.pixyspawners;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class GetSpawner {
    public static PixySpawners Plugin() {
        return JavaPlugin.getPlugin(PixySpawners.class);
    }
    public static boolean inventory(Player player, String mob, int amount){
        try{
            EntityType et = EntityType.valueOf(mob.toUpperCase());
            String e = mob.toLowerCase().replace("_", " ");
            e = e.substring(0, 1).toUpperCase() + e.substring(1);

            Player p = player;
            ItemStack i = new ItemStack(Material.SPAWNER, amount);
            BlockStateMeta meta = (BlockStateMeta) i.getItemMeta();
            CreatureSpawner spawner = (CreatureSpawner) meta.getBlockState();
            spawner.setSpawnedType(et);
            meta.setBlockState(spawner);
            meta.setDisplayName(LangEn.getSpawnerItemName(e));
            i.setItemMeta(meta);
            p.getInventory().addItem(i);
            return true;
        }
        catch(Exception ex){
            player.sendMessage(Globals.prefix + "Error has occured :(");
            Plugin().getLogger().log(Level.SEVERE, "[PixySpawners] Error has occured :(. " + ex.getMessage());
            return false;
        }
    }
    public static boolean player(String username, String mob, int amount){
        try{
            EntityType et = EntityType.valueOf(mob.toUpperCase());
            String e = mob.toLowerCase().replace("_", " ");
            e = e.substring(0, 1).toUpperCase() + e.substring(1);

            Player p = (Player) Plugin().getServer().getPlayer(username);
            ItemStack i = new ItemStack(Material.SPAWNER, amount);
            BlockStateMeta meta = (BlockStateMeta) i.getItemMeta();
            CreatureSpawner spawner = (CreatureSpawner) meta.getBlockState();
            spawner.setSpawnedType(et);
            meta.setBlockState(spawner);
            meta.setDisplayName(LangEn.getSpawnerItemName(e));
            i.setItemMeta(meta);
            p.getInventory().addItem(i);
            return true;
        }
        catch(Exception ex){
            Plugin().getLogger().log(Level.SEVERE, "[PixySpawners] Error has occured :(. " + ex.getMessage());
            return false;
        }
    }
}
