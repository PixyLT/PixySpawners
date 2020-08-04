package com.pixylt.pixyspawners;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.logging.Level;

public class RightClickListener implements Listener {
    Material t = Material.SPAWNER;

    PixySpawners plugin;

    public RightClickListener(PixySpawners instance) {
        plugin = instance;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getClickedBlock().getType() == t){
                if(e.getPlayer().getInventory().getItemInMainHand().getType() == t){
                    if(Permissions.stack(e.getPlayer())){
                        EntityType type = ((CreatureSpawner) e.getClickedBlock().getState()).getSpawnedType();

                        ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
                        BlockStateMeta metac = (BlockStateMeta) i.getItemMeta();
                        CreatureSpawner spawnerc = (CreatureSpawner) metac.getBlockState();
                        EntityType type2 = spawnerc.getSpawnedType();

                        e.setCancelled(true);
                        if(type != type2){
                            e.getPlayer().sendMessage(LangEn.spawnersDoesntMatch);
                        } else {
                            int amount = 2;
                            e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
                            int x = e.getClickedBlock().getX();
                            int y = e.getClickedBlock().getY();
                            int z = e.getClickedBlock().getZ();
                            String cfg = String.valueOf(x) + "-" + String.valueOf(y) + "-" + String.valueOf(z);

                            if (Config.getHoloConfig().getString(cfg) != null) {
                                int eni = Integer.parseInt(Config.getHoloConfig().getString(cfg));
                                Chunk c = e.getClickedBlock().getChunk();
                                boolean destroyed = false;
                                for (Entity entity : c.getEntities()) {
                                    if (entity.getEntityId() == eni) {
                                        entity.remove();
                                        destroyed = true;
                                        break;
                                    }
                                }
                                if (!destroyed) {
                                    plugin.getLogger().log(Level.SEVERE, "[PixySpawners] Holo wasn't destryoed. Please report bug to plugin owner.");
                                }
                            }

                            if (Config.getSpawnerConfig().getString(cfg) == null) {
                                Config.getSpawnerConfig().set(cfg, 2);
                                if (Config.saveSpawnerConfig()) {
                                    e.getPlayer().sendMessage(LangEn.stackSuccess);
                                } else {
                                    e.getPlayer().sendMessage(LangEn.error);
                                }
                            } else {
                                String a = Config.getSpawnerConfig().getString(cfg);
                                amount = Integer.parseInt(a) + 1;
                                Config.getSpawnerConfig().set(cfg, amount);
                                if (Config.saveSpawnerConfig()) {
                                    e.getPlayer().sendMessage(LangEn.stackSuccess);
                                } else {
                                    e.getPlayer().sendMessage(LangEn.error);
                                }
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

                            Location loc = e.getClickedBlock().getLocation();
                            loc.add(0.5, -1, 0.5);
                            ArmorStand as = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
                            String spawnerName = spawner.getSpawnedType().name().toLowerCase().replace("_", " ");
                            spawnerName = spawnerName.substring(0, 1).toUpperCase() + spawnerName.substring(1);

                            as.setGravity(false);
                            as.setCanPickupItems(false);
                            as.setCustomName(LangEn.getSpawnerName(amount, spawnerName));
                            as.setCustomNameVisible(true);
                            as.setVisible(false);
                            int enid = as.getEntityId();
                            String EnID = Integer.toString(enid);

                            Config.getHoloConfig().set(cfg, EnID);
                            if (!Config.saveHoloConfig()) {
                                e.getPlayer().sendMessage(LangEn.error);
                            }

                            bs.update();
                        }
                    }
                } else if(e.getHand() == EquipmentSlot.HAND) {
                    int amount = 1;
                    int x = e.getClickedBlock().getX();
                    int y = e.getClickedBlock().getY();
                    int z = e.getClickedBlock().getZ();
                    String cfg = String.valueOf(x) + "-" + String.valueOf(y) + "-" + String.valueOf(z);
                    if(Config.getSpawnerConfig().getString(cfg) == null){
                        amount = 1;
                    } else {
                        String a = Config.getSpawnerConfig().getString(cfg);
                        amount = Integer.parseInt(a);
                    }
                    EntityType type = ((CreatureSpawner) e.getClickedBlock().getState()).getSpawnedType();
                    e.getPlayer().sendMessage(LangEn.getRightClickSpawner(amount, type.name()));
                }
            }
        }
    }

}
