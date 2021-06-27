package com.pixylt.pixyspawners.events;

import com.pixylt.pixyspawners.PixySpawners;
import com.pixylt.pixyspawners.lang.en;
import com.pixylt.pixyspawners.utils.Config;
import com.pixylt.pixyspawners.utils.Console;
import com.pixylt.pixyspawners.utils.Heads;
import com.pixylt.pixyspawners.utils.Holograms;

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
    Material spawnerMaterial = Material.SPAWNER;
    Console console = new Console();

    PixySpawners plugin;

    public BlockPlaceListener(PixySpawners instance) {
        plugin = instance;
    }
    Config config = new Config();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if(e.getBlock().getType() == spawnerMaterial){
            if(e.getBlockAgainst().getType() != spawnerMaterial){
                ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
                BlockStateMeta metac = (BlockStateMeta) i.getItemMeta();
                CreatureSpawner spawnerc = (CreatureSpawner) metac.getBlockState();
                EntityType type2 = spawnerc.getSpawnedType();

                Block block = e.getBlockPlaced();
                BlockState bs = block.getState();
                CreatureSpawner spawner = (CreatureSpawner) bs;
                spawner.setSpawnedType(type2);
                bs.update();
                int x = e.getBlock().getX();
                int y = e.getBlock().getY();
                int z = e.getBlock().getZ();
                String xyz = e.getBlock().getWorld().getName() + String.valueOf(x) + "-" + String.valueOf(y) + "-" + String.valueOf(z);

                if(config.stackSpawner(xyz, e.getPlayer().getName(), 1, type2.name())) {
                    e.getPlayer().sendMessage(en.placeSuccess);
                } else {
                    e.getPlayer().sendMessage(en.error);
                }
                if(Holograms.exists(e.getBlock().getLocation())) {
                    ItemStack is = Heads.getHead(type2.name());
                    Holograms.updateSpawner(e.getBlock().getLocation(), en.getHoloName(1, type2.name()), is);
                } else {
                    ItemStack is = Heads.getHead(type2.name());
                    Holograms.placeSpawner(e.getBlock().getLocation(), en.getHoloName(1, type2.name()), is);
                }
            }
        }
    }
}
