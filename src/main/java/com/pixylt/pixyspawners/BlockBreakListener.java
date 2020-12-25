package com.pixylt.pixyspawners;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.logging.Level;

public class BlockBreakListener implements Listener {
    Material t = Material.SPAWNER;

    PixySpawners plugin;

    public BlockBreakListener(PixySpawners instance) {
        plugin = instance;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if(e.getPlayer() == null){
            return;
        }

        if(e.getBlock().getType() != t) {
            return;
        }

        e.setCancelled(true);

        if(GriefPrevention.check()){
            if(!GriefPrevention.allowBreak(e.getPlayer(), e.getBlock(), e.getBlock().getLocation())) {
                e.getPlayer().sendMessage(LangEn.notAllowedToBreak);
                return;
            }
        }

        if(ConfigLegacy.getConfig().getString("require-silktouch") == "true") {
            if(ConfigLegacy.getConfig().getString("can-remove-without-silktouch") == "false") {
                if(e.getPlayer().getInventory().getItemInMainHand() == null || !e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)){
                    e.getPlayer().sendMessage(LangEn.silktouchRequired);
                    return;
                }
            }
        }
        int count = 0;
        for (ItemStack i : e.getPlayer().getInventory().getContents()) {
            if (i == null) {
                count++;
            }
        }
        if (e.getPlayer().getInventory().getHelmet() == null) {
            count--;
        }
        if (e.getPlayer().getInventory().getChestplate() == null) {
            count--;
        }
        if (e.getPlayer().getInventory().getLeggings() == null) {
            count--;
        }
        if (e.getPlayer().getInventory().getBoots() == null) {
            count--;
        }
        if (e.getPlayer().getInventory().getItemInOffHand() != null) {
            count--;
        }
        if(count == 0) {
            e.getPlayer().sendMessage(LangEn.playerInventoryFull);
            return;
        }

        Block block = e.getBlock();
        Player player = e.getPlayer();
        EntityType type = ((CreatureSpawner) block.getState()).getSpawnedType();
        block.setType(Material.AIR);

        int amount = 1;
        int x = e.getBlock().getX();
        int y = e.getBlock().getY();
        int z = e.getBlock().getZ();
        String cfg = String.valueOf(x) + "-" + String.valueOf(y) + "-" + String.valueOf(z);
        Location loc = e.getBlock().getLocation();
        if(ConfigLegacy.getHoloConfig().getString(cfg) != null){
            int eni = Integer.parseInt(ConfigLegacy.getHoloConfig().getString(cfg));
            Chunk c = e.getBlock().getChunk();

            boolean destroyed = false;
            for(Entity entity: c.getEntities()){
                if(entity.getEntityId() == eni){
                    entity.remove();
                    destroyed = true;
                    ConfigLegacy.getHoloConfig().set(cfg, null);
                    if(!ConfigLegacy.saveHoloConfig()){
                        e.getPlayer().sendMessage(LangEn.error);
                    }
                    break;
                }
            }
            if(!destroyed){
                plugin.getLogger().log(Level.SEVERE, "[PixySpawners] Holo wasn't destryoed. Please report bug to plugin owner.");
            }
        }

        if(ConfigLegacy.getSpawnerConfig() == null){
            amount = 1;
            e.getPlayer().sendMessage(LangEn.getSpawnersRemoved(amount, type.name()));
        } else {
//            String a = ConfigLegacy.getSpawnerConfig().getString(cfg);
//            if(ConfigLegacy.getSpawnerConfig().getString(cfg) == null) {
            String a;
            if(!Config.exists(cfg)){
                a = "1";
            } else {
                a = Config.getCount(cfg);
            }
            amount = Integer.parseInt(a);
            ConfigLegacy.getSpawnerConfig().set(cfg, null);
            ConfigLegacy.saveSpawnerConfig();
            e.getPlayer().sendMessage(LangEn.getSpawnersRemoved(amount, type.name()));
        }
        if(amount==0){
            amount = 1;
            plugin.getLogger().log(Level.SEVERE, Globals.prefixv + "Spawner bug was triggered by " + e.getPlayer().getName() + ". (It doesn't mean that player cheats.)");
            plugin.getLogger().log(Level.SEVERE, Globals.prefixv + "Player might not receive his spawner. (Spawner type: " + type.name() + "(1))");
            plugin.getLogger().log(Level.SEVERE, Globals.prefixv + "Please report this bug to plugin owner!");
        }
        String sn = type.name().toLowerCase().replace("_", " ");
        sn = sn.substring(0, 1).toUpperCase() + sn.substring(1);

        Player p = (Player) player;
        ItemStack i = new ItemStack(Material.SPAWNER, amount);
        BlockStateMeta meta = (BlockStateMeta) i.getItemMeta();
        CreatureSpawner spawner = (CreatureSpawner) meta.getBlockState();
        spawner.setSpawnedType(type);
        meta.setBlockState(spawner);
        meta.setDisplayName(LangEn.getSpawnerItemName(sn));
        i.setItemMeta(meta);
        if(ConfigLegacy.getConfig().getString("require-silktouch") == "true" && ConfigLegacy.getConfig().getString("can-remove-without-silktouch") == "true"){
            if(e.getPlayer().getInventory().getItemInMainHand() == null || !e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)){
                return;
            }
        }
        p.getInventory().addItem(i);
    }

}
