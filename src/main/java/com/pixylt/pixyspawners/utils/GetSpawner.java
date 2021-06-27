package com.pixylt.pixyspawners.utils;

import com.pixylt.pixyspawners.PixySpawners;
import com.pixylt.pixyspawners.lang.en;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class GetSpawner {
    public static PixySpawners Plugin() {
        return JavaPlugin.getPlugin(PixySpawners.class);
    }
    static Console console = new Console();

    public static boolean inventory(Player player, String mob, int amount) {
        try {
            EntityType et = EntityType.valueOf(mob.toUpperCase());
            String mobName = mob.toLowerCase().replace("_", " ");
            mobName = mobName.substring(0, 1).toUpperCase() + mobName.substring(1);

            ItemStack i = new ItemStack(Material.SPAWNER, amount);
            BlockStateMeta meta = (BlockStateMeta) i.getItemMeta();
            CreatureSpawner spawner = (CreatureSpawner) meta.getBlockState();
            spawner.setSpawnedType(et);
            meta.setBlockState(spawner);
            meta.displayName(en.getSpawnerItemName(mobName));
            i.setItemMeta(meta);
            player.getInventory().addItem(i);
            return true;
        } catch (Exception e) {
            player.sendMessage(en.error);
            console.error("Error has occured while trying to give spawner to " + player.getName());
            return false;
        }
    }
    public static boolean player(String username, String mob, int amount){
        try{
            EntityType et = EntityType.valueOf(mob.toUpperCase());
            String mobName = mob.toLowerCase().replace("_", " ");
            mobName = mobName.substring(0, 1).toUpperCase() + mobName.substring(1);

            Player p = (Player) Plugin().getServer().getPlayer(username);
            ItemStack i = new ItemStack(Material.SPAWNER, amount);
            BlockStateMeta meta = (BlockStateMeta) i.getItemMeta();
            CreatureSpawner spawner = (CreatureSpawner) meta.getBlockState();
            spawner.setSpawnedType(et);
            meta.setBlockState(spawner);
            meta.displayName(en.getSpawnerItemName(mobName));
            i.setItemMeta(meta);
            p.getInventory().addItem(i);
            return true;
        }
        catch(Exception ex){
            console.error("Error has occured while trying to give spawner");
            return false;
        }
    }
}
