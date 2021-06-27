package com.pixylt.pixyspawners.events;

import com.pixylt.pixyspawners.PixySpawners;
import com.pixylt.pixyspawners.classes.Spawner;
import com.pixylt.pixyspawners.compat.GriefPrevention;
import com.pixylt.pixyspawners.lang.en;
import com.pixylt.pixyspawners.utils.Config;
import com.pixylt.pixyspawners.utils.Console;
import com.pixylt.pixyspawners.utils.Heads;
import com.pixylt.pixyspawners.utils.Holograms;
import com.pixylt.pixyspawners.utils.Permissions;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class RightClickListener implements Listener {
    Material spawnerMaterial = Material.SPAWNER;
    Console console = new Console();

    PixySpawners plugin;

    public RightClickListener(PixySpawners instance) {
        plugin = instance;
    }
    Config config = new Config();

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        config.reload();
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(e.getClickedBlock().getType() != spawnerMaterial) return;
        if(e.getPlayer().getInventory().getItemInMainHand().getType() == spawnerMaterial) {
            if(Permissions.stack(e.getPlayer())) {
                if(GriefPrevention.check() && !GriefPrevention.allowBreak(e.getPlayer(), e.getClickedBlock(), e.getClickedBlock().getLocation())) {
                    e.getPlayer().sendMessage(en.griefPreventionProtected);
                    return;
                }
                EntityType type = ((CreatureSpawner) e.getClickedBlock().getState()).getSpawnedType();

                ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
                BlockStateMeta metaHand = (BlockStateMeta) i.getItemMeta();
                CreatureSpawner spawnerHand = (CreatureSpawner) metaHand.getBlockState();
                EntityType typeHand = spawnerHand.getSpawnedType();

                e.setCancelled(true);
                if(type != typeHand) { e.getPlayer().sendMessage(en.spawnersDoesntMatch); }
                else { 
                    int amount = 2;
                    int x = e.getClickedBlock().getX();
                    int y = e.getClickedBlock().getY();
                    int z = e.getClickedBlock().getZ();
                    String xyz = e.getClickedBlock().getWorld().getName() + String.valueOf(x) + "-" + String.valueOf(y) + "-" + String.valueOf(z);

                    if(!Permissions.stack(e.getPlayer())) {
                        e.getPlayer().sendMessage(en.missingPermission("pixyspawners.stack"));
                        return;
                    }

                    if(config.spawnerExists(xyz)) {
                        Spawner cfgSpawner = config.getSpawner(xyz);
                        try {
                            amount = Integer.parseInt(cfgSpawner.getCount()) + 1;
                        } catch (Exception ex) {
                            amount = 2;
                            //TODO: handle exception
                        }
                    }
                    if(Integer.parseInt(config.mainGet("max-spawners")) <= amount-1) {
                        e.getPlayer().sendMessage(en.maxSpawnersReached(amount-1, Integer.parseInt(config.mainGet("max-spawners"))));
                        return;
                    }
                    if(config.stackSpawner(xyz, e.getPlayer().getName(), amount, type.name())) {
                        e.getPlayer().sendMessage(en.stackSuccess);
                    } else {
                        e.getPlayer().sendMessage(en.error);
                        return;
                    }

                    e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);

                    if(Holograms.exists(e.getClickedBlock().getLocation())) {
                        ItemStack is = Heads.getHead(type.name());
                        Holograms.updateSpawner(e.getClickedBlock().getLocation(), en.getHoloName(amount, type.name()), is);
                    } else {
                        ItemStack is = Heads.getHead(type.name());
                        Holograms.placeSpawner(e.getClickedBlock().getLocation(), en.getHoloName(amount, type.name()), is);
                    }
                    
                    Block block = e.getClickedBlock();
                    BlockState bs = block.getState();
                    CreatureSpawner spawner = (CreatureSpawner) bs;
                    int mbe = (spawner.getMaxNearbyEntities() / (amount - 1)) * amount;
                    int sc = (spawner.getSpawnCount() / (amount - 1)) * amount;
                    int mxsd = (spawner.getMaxSpawnDelay() * (amount - 1)) / amount;
                    int mnsd = (spawner.getMinSpawnDelay() * (amount - 1)) / amount;
                    spawner.setMaxNearbyEntities(mbe);
                    spawner.setSpawnCount(sc);
                    spawner.setMaxSpawnDelay(mxsd);
                    spawner.setMinSpawnDelay(mnsd);

                    bs.update();
                }
            }
        } else if(e.getHand() == EquipmentSlot.HAND) {
            int amount = 1;
            int x = e.getClickedBlock().getX();
            int y = e.getClickedBlock().getY();
            int z = e.getClickedBlock().getZ();
            String xyz = e.getClickedBlock().getWorld().getName() + String.valueOf(x) + "-" + String.valueOf(y) + "-" + String.valueOf(z);
            Spawner cfgSpawner = config.getSpawner(xyz);
            if(cfgSpawner != null) {
                amount = Integer.parseInt(cfgSpawner.getCount());
            } else {
                amount = 1;
            }
            if(!Permissions.check(e.getPlayer())) {
                e.getPlayer().sendMessage(en.missingPermission("pixyspawners.check"));
                return;
            }
            String owner = cfgSpawner.getOwner();
            EntityType type = ((CreatureSpawner) e.getClickedBlock().getState()).getSpawnedType();
            if(config.mainGetB("show-spawner-owner")) {
                e.getPlayer().sendMessage(en.getRightClickSpawner(amount, type.name(), owner, true));
            } else {
                e.getPlayer().sendMessage(en.getRightClickSpawner(amount, type.name(), owner, false));
            }
        }
    }
}
