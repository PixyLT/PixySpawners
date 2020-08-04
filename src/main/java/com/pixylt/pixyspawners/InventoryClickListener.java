package com.pixylt.pixyspawners;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        p.sendMessage("Title = " + e.getView().getTitle() + " " + e.getSlot());

        if(e.getView().getTitle().equals("Page 1 | Select spawner type")){
            e.setCancelled(true);
            EntityType et = EntityType.PIG;
            String en = "--";
            ItemStack i = new ItemStack(Material.SPAWNER);
            BlockStateMeta meta = (BlockStateMeta) i.getItemMeta();
            CreatureSpawner spawner = (CreatureSpawner) meta.getBlockState();

            EntityType[] allowedTypesArray = {EntityType.BAT, EntityType.BEE, EntityType.BLAZE, EntityType.CAT, EntityType.CAVE_SPIDER, EntityType.CHICKEN, EntityType.COD, EntityType.COW, EntityType.CREEPER, EntityType.DOLPHIN, EntityType.DONKEY, EntityType.DROWNED, EntityType.ELDER_GUARDIAN, EntityType.ENDER_DRAGON, EntityType.ENDERMAN, EntityType.ENDERMITE, EntityType.EVOKER, EntityType.FOX, EntityType.GHAST, EntityType.GIANT, EntityType.GUARDIAN, EntityType.HOGLIN, EntityType.HORSE, EntityType.ILLUSIONER, EntityType.IRON_GOLEM, EntityType.LLAMA, EntityType.MAGMA_CUBE, EntityType.MULE, EntityType.MUSHROOM_COW, EntityType.OCELOT, EntityType.PANDA, EntityType.PARROT, EntityType.PHANTOM, EntityType.PIG, EntityType.PIGLIN, EntityType.PILLAGER, EntityType.POLAR_BEAR, EntityType.PUFFERFISH, EntityType.RABBIT, EntityType.RAVAGER, EntityType.SALMON, EntityType.SHEEP, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SKELETON_HORSE};
            List<EntityType> allowedTypes = new ArrayList();
            allowedTypes.addAll(Arrays.asList(allowedTypesArray));

            if(e.getSlot() == 53){
                p.closeInventory();
                SpawnersGUI.gui2((CommandSender)p);
            } else if(e.getSlot() == 45){
                en = en;
            } else {
                et = allowedTypes.get(e.getSlot());
                en = et.name();
            }
            if(en != "--"){
                String sn = et.name().toLowerCase().replace("_", " ");
                sn = sn.substring(0, 1).toUpperCase() + sn.substring(1);
                i = new ItemStack(Material.SPAWNER);
                meta = (BlockStateMeta) i.getItemMeta();
                spawner = (CreatureSpawner) meta.getBlockState();
                spawner.setSpawnedType(et);
                meta.setBlockState(spawner);
                meta.setDisplayName(LangEn.getSpawnerItemName(sn));
                i.setItemMeta(meta);
                if(Permissions.get(p)){
                    p.getInventory().addItem(i);
                } else {
                    p.sendMessage(LangEn.missingPerm);
                }
                p.closeInventory();
            }
        } else if(e.getView().getTitle().equals("Page 2 | Select spawner type")){
            e.setCancelled(true);
            EntityType et = EntityType.PIG;
            String en = "--";
            ItemStack i = new ItemStack(Material.SPAWNER);
            BlockStateMeta meta = (BlockStateMeta) i.getItemMeta();
            CreatureSpawner spawner = (CreatureSpawner) meta.getBlockState();

            EntityType[] allowedTypesArray = {EntityType.SLIME, EntityType.SNOWMAN, EntityType.SPIDER, EntityType.SQUID, EntityType.STRAY, EntityType.STRIDER, EntityType.TRADER_LLAMA, EntityType.TROPICAL_FISH, EntityType.TURTLE, EntityType.VEX, EntityType.VILLAGER, EntityType.VINDICATOR, EntityType.WANDERING_TRADER, EntityType.WITCH, EntityType.WITHER, EntityType.WITHER_SKELETON, EntityType.WOLF, EntityType.ZOGLIN, EntityType.ZOMBIE, EntityType.ZOMBIE_HORSE, EntityType.ZOMBIE_VILLAGER, EntityType.ZOMBIFIED_PIGLIN};
            List<EntityType> allowedTypes = new ArrayList();
            allowedTypes.addAll(Arrays.asList(allowedTypesArray));

            if(e.getSlot() == 53){
                en = en;
            } else if(e.getSlot() == 45){
                p.closeInventory();
                SpawnersGUI.gui1((CommandSender)p);
            } else {
                et = allowedTypes.get(e.getSlot());
                en = et.name();
            }
            if(en != "--"){
                String sn = et.name().toLowerCase().replace("_", " ");
                sn = sn.substring(0, 1).toUpperCase() + sn.substring(1);
                i = new ItemStack(Material.SPAWNER);
                meta = (BlockStateMeta) i.getItemMeta();
                spawner = (CreatureSpawner) meta.getBlockState();
                spawner.setSpawnedType(et);
                meta.setBlockState(spawner);
                meta.setDisplayName(LangEn.getSpawnerItemName(sn));
                i.setItemMeta(meta);
                if(Permissions.get(p)){
                    p.getInventory().addItem(i);
                } else {
                    p.sendMessage(LangEn.missingPerm);
                }
                p.closeInventory();
            }
        }
    }
}
