package com.pixylt.pixyspawners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class BlockPlaceListener implements Listener {
    Material t = Material.SPAWNER;

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if(e.getBlock().getType() == t){
            if(e.getBlockAgainst().getType() != t){
                ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
                BlockStateMeta metac = (BlockStateMeta) i.getItemMeta();
                CreatureSpawner spawnerc = (CreatureSpawner) metac.getBlockState();
                EntityType type2 = spawnerc.getSpawnedType();

                Block block = e.getBlockPlaced();
                BlockState bs = block.getState();
                CreatureSpawner spawner = (CreatureSpawner) bs;
                spawner.setSpawnedType(type2);
                bs.update();
            }
        }
    }
}
