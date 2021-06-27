package com.pixylt.pixyspawners.events;

import com.pixylt.pixyspawners.PixySpawners;
import com.pixylt.pixyspawners.classes.Spawner;
import com.pixylt.pixyspawners.lang.en;
import com.pixylt.pixyspawners.utils.Config;
import com.pixylt.pixyspawners.utils.Holograms;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class EntityExplodeListener implements Listener {
    Material spawnerMaterial = Material.SPAWNER;

    PixySpawners plugin;
    Config config = new Config();

    public EntityExplodeListener(PixySpawners instance) {
        plugin = instance;
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        Block block = null;
        if(config.mainGetB("spawner-can-be-destroyed") && !config.mainGetB("spawner-drop-on-explosion")) {
            return;
        } else if(!config.mainGetB("spawner-can-be-destroyed")) {
            for(Block b : e.blockList().toArray(new Block[e.blockList().size()])) {
                if(b.getType() != spawnerMaterial) continue;
                e.blockList().remove(b);
                block = b;
            }
        } else if(config.mainGetB("spawner-can-be-destroyed") && config.mainGetB("spawner-drop-on-explosion")) {
            for(Block b : e.blockList().toArray(new Block[e.blockList().size()])) {
                if(b.getType() != spawnerMaterial) continue;
                block = b;
                Location loc = block.getLocation();
                World world = block.getWorld();
                EntityType type = ((CreatureSpawner) block.getState()).getSpawnedType();
                int amount = 1;
                int x = block.getX();
                int y = block.getY();
                int z = block.getZ();
                String xyz = block.getWorld().getName() + String.valueOf(x) + "-" + String.valueOf(y) + "-" + String.valueOf(z);
                config.reload();
                Spawner configSpawner = config.getSpawner(xyz);
                if(configSpawner != null) {
                    amount = Integer.parseInt(configSpawner.getCount());
                    config.breakSpawner(xyz);
                }
                Holograms.removeSpawner(b.getLocation());

                ItemStack i = new ItemStack(Material.SPAWNER, amount);
                BlockStateMeta meta = (BlockStateMeta) i.getItemMeta();
                CreatureSpawner spawner = (CreatureSpawner) meta.getBlockState();
                spawner.setSpawnedType(type);
                meta.setBlockState(spawner);
                String sn = type.name().toLowerCase().replace("_", " ");
                sn = sn.substring(0, 1).toUpperCase() + sn.substring(1);
                meta.displayName(en.getSpawnerItemName(sn));
                i.setItemMeta(meta);
                block.setType(Material.AIR);
                world.dropItem(loc, i);
            }
        }
    }
}
