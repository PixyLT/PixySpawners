package com.pixylt.pixyspawners;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.logging.Level;

public class EntityExplodeListener implements Listener {
    Material t = Material.SPAWNER;

    PixySpawners plugin;

    public EntityExplodeListener(PixySpawners instance) {
        plugin = instance;
    }

    @EventHandler
    public void onEntityExplore(EntityExplodeEvent e){
        Block block = null;
        if(Config.getConfig().getString("spawner-can-be-destroyed") == "true" && Config.getConfig().getString("spawner-drop-on-explosion") == "false"){
            return;
        } else if(Config.getConfig().getString("spawner-can-be-destroyed") == "false"){
            for(Block b : e.blockList().toArray(new Block[e.blockList().size()])){
                if(b.getType() != t){
                    continue;
                }
                e.blockList().remove(b);
                block = b;
            }
        } else if(Config.getConfig().getString("spawner-can-be-destroyed") == "true" && Config.getConfig().getString("spawner-drop-on-explosion") == "true"){
            Location loc = block.getLocation();
            World world = block.getWorld();
            EntityType type = ((CreatureSpawner) block.getState()).getSpawnedType();
            int amount = 1;
            int x = block.getX();
            int y = block.getY();
            int z = block.getZ();
            String cfg = String.valueOf(x) + "-" + String.valueOf(y) + "-" + String.valueOf(z);
            plugin.getLogger().log(Level.INFO, Globals.prefixv + "Trying to remove holo");
            if(Config.getHoloConfig().getString(cfg) != null){
                int eni = Integer.parseInt(Config.getHoloConfig().getString(cfg));
                Chunk c = block.getChunk();
                for(Entity entity: c.getEntities()){
                    if(entity.getEntityId() == eni){
                        entity.remove();
                        plugin.getLogger().log(Level.INFO, Globals.prefixv + "Sucessfully removed holo");
                        break;
                    }
                }
            }

            if(Config.getSpawnerConfig().getString(cfg) == null){
                amount = 1;
            } else {
                String a = Config.getSpawnerConfig().getString(cfg);
                amount = Integer.parseInt(a);
                Config.getSpawnerConfig().set(cfg, null);
                Config.saveSpawnerConfig();
            }
            if(amount==0){
                amount = 1;
                plugin.getLogger().log(Level.SEVERE, Globals.prefixv + "Spawner bug was triggered by Explosion. (It doesn't mean that player cheats.)");
                plugin.getLogger().log(Level.SEVERE, Globals.prefixv + "Player might not receive his spawner. (Spawner type: " + type.name() + "(1))");
                plugin.getLogger().log(Level.SEVERE, Globals.prefixv + "Please report this bug to plugin owner!");
            }

            ItemStack i = new ItemStack(Material.SPAWNER, amount);
            BlockStateMeta meta = (BlockStateMeta) i.getItemMeta();
            CreatureSpawner spawner = (CreatureSpawner) meta.getBlockState();
            spawner.setSpawnedType(type);
            meta.setBlockState(spawner);
            meta.setDisplayName(LangEn.getSpawnerItemName(type.name()));
            i.setItemMeta(meta);
            block.setType(Material.AIR);
            world.dropItem(loc, i);
        }
    }
}
