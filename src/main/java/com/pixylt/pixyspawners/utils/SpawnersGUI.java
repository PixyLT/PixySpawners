package com.pixylt.pixyspawners.utils;

import java.util.List;

import com.pixylt.pixyspawners.lang.en;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.md_5.bungee.api.ChatColor;

public class SpawnersGUI implements Listener {

    public SpawnersGUI() {
        initializeItems();
    }
    public void initializeItems() {
        // String[] allowedTypesString = {"BAT", "BEE", "BLAZE", "CAT", "CAVE_SPIDER", "CHICKEN", "COD", "COW", "CREEPER", "DOLPHIN", "DONKEY", "DROWNED", "ELDER_GUARDIAN", "ENDER_DRAGON", "ENDERMAN", "ENDERMITE", "EVOKER", "FOX", "GHAST", "GIANT", "GUARDIAN", "HOGLIN", "HORSE", "ILLUSIONER", "IRON_GOLEM", "LLAMA", "MAGMA_CUBE", "MULE", "MUSHROOM_COW", "OCELOT", "PANDA", "PARROT", "PHANTOM", "PIG", "PIGLIN", "PILLAGER", "POLAR_BEAR", "PUFFERFISH", "RABBIT", "RAVAGER", "SALMON", "SHEEP", "SILVERFISH", "SKELETON", "SKELETON_HORSE", "SLIME", "SNOWMAN", "SPIDER", "SQUID", "STRAY", "STRIDER", "TRADER_LLAMA", "TROPICAL_FISH", "TURTLE", "VEX", "VILLAGER", "VINDICATOR", "WANDERING_TRADER", "WITCH", "WITHER", "WITHER_SKELETON", "WOLF", "ZOGLIN", "ZOMBIE", "ZOMBIE_HORSE", "ZOMBIE_VILLAGER", "ZOMBIEFIED_PIGLIN"};
        // Material[] allowedTypesIconsString = {Material.BAT_SPAWN_EGG, Material.BEE_SPAWN_EGG, Material.BLAZE_SPAWN_EGG, Material.CAT_SPAWN_EGG, Material.CAVE_SPIDER_SPAWN_EGG, Material.CHICKEN_SPAWN_EGG, Material.COD_SPAWN_EGG, Material.COW_SPAWN_EGG, Material.CREEPER_SPAWN_EGG, Material.DOLPHIN_SPAWN_EGG, Material.DONKEY_SPAWN_EGG, Material.DROWNED_SPAWN_EGG, Material.ELDER_GUARDIAN_SPAWN_EGG, Material.DRAGON_EGG, Material.ENDERMAN_SPAWN_EGG, Material.ENDERMITE_SPAWN_EGG, Material.EVOKER_SPAWN_EGG, Material.FOX_SPAWN_EGG, Material.GHAST_SPAWN_EGG, Material.ZOMBIE_HEAD, Material.GUARDIAN_SPAWN_EGG, Material.HOGLIN_SPAWN_EGG, Material.HORSE_SPAWN_EGG, Material.TIPPED_ARROW, Material.IRON_BLOCK, Material.LLAMA_SPAWN_EGG, Material.MAGMA_CUBE_SPAWN_EGG, Material.MULE_SPAWN_EGG, Material.BROWN_MUSHROOM, Material.OCELOT_SPAWN_EGG, Material.PANDA_SPAWN_EGG, Material.PARROT_SPAWN_EGG, Material.PHANTOM_SPAWN_EGG, Material.PIG_SPAWN_EGG, Material.PIGLIN_SPAWN_EGG, Material.PILLAGER_SPAWN_EGG, Material.POLAR_BEAR_SPAWN_EGG, Material.PUFFERFISH_SPAWN_EGG, Material.RABBIT_SPAWN_EGG, Material.RAVAGER_SPAWN_EGG, Material.SALMON_SPAWN_EGG, Material.SHEEP_SPAWN_EGG, Material.SILVERFISH_SPAWN_EGG, Material.SKELETON_SPAWN_EGG, Material.SKELETON_HORSE_SPAWN_EGG, Material.SLIME_SPAWN_EGG, Material.SNOWBALL, Material.SPIDER_SPAWN_EGG, Material.SQUID_SPAWN_EGG, Material.STRAY_SPAWN_EGG, Material.STRIDER_SPAWN_EGG, Material.TRADER_LLAMA_SPAWN_EGG, Material.TROPICAL_FISH_SPAWN_EGG, Material.TURTLE_SPAWN_EGG, Material.VEX_SPAWN_EGG, Material.VILLAGER_SPAWN_EGG, Material.VINDICATOR_SPAWN_EGG, Material.WANDERING_TRADER_SPAWN_EGG, Material.WITCH_SPAWN_EGG, Material.WITHER_SKELETON_SKULL, Material.WITHER_SKELETON_SPAWN_EGG, Material.WOLF_SPAWN_EGG, Material.ZOGLIN_SPAWN_EGG, Material.ZOMBIE_SPAWN_EGG, Material.ZOMBIE_HORSE_SPAWN_EGG, Material.ZOMBIE_VILLAGER_SPAWN_EGG, Material.ZOMBIFIED_PIGLIN_SPAWN_EGG};
        // List<String> allowedTypes = new ArrayList<String>();
        // List<Material> allowedTypesIcons = new ArrayList<Material>();
        // allowedTypes.addAll(Arrays.asList(allowedTypesString));
        // allowedTypesIcons.addAll(Arrays.asList(allowedTypesIconsString));
    }

    public PaginatedGui getGui() {
        PaginatedGui gui = Gui.paginated()
            .title(en.guiName)
            .rows(6)
            .create();
        List<String> allowedTypes = MobList.getStringList();

        for(int i = 0; i<45; i++) {
            String spawnerType = allowedTypes.get(i).toLowerCase().replace("_", " ");
            spawnerType = spawnerType.substring(0, 1).toUpperCase() + spawnerType.substring(1);
            ItemStack is = MobList.getMobIcon(allowedTypes.get(i));
            final ItemMeta meta = is.getItemMeta();
            meta.displayName(en.getSpawnerItemName(spawnerType));
            is.setItemMeta(meta);
            GuiItem guiItem = ItemBuilder.from(is).asGuiItem();
            gui.addItem(guiItem);
        }

        gui.setItem(6, 3, ItemBuilder.from(Material.PAPER).name(en.textToComp(ChatColor.LIGHT_PURPLE + "Previous")).asGuiItem(event -> { gui.previous(); event.setCancelled(true); }));
        gui.setItem(6, 7, ItemBuilder.from(Material.PAPER).name(en.textToComp(ChatColor.LIGHT_PURPLE + "Next")).asGuiItem(event -> { gui.next(); event.setCancelled(true); }));

        GuiItem spacerGuiItem = ItemBuilder.from(Material.GRAY_STAINED_GLASS_PANE).name(en.textToComp(" ")).asGuiItem();

        gui.setItem(6, 1, spacerGuiItem);
        gui.setItem(6, 2, spacerGuiItem);
        gui.setItem(6, 4, spacerGuiItem);
        gui.setItem(6, 5, spacerGuiItem);
        gui.setItem(6, 6, spacerGuiItem);
        gui.setItem(6, 8, spacerGuiItem);
        gui.setItem(6, 9, spacerGuiItem);

        for(int i = 45; i<allowedTypes.size(); i++) {
            String spawnerType = allowedTypes.get(i).toLowerCase().replace("_", " ");
            spawnerType = spawnerType.substring(0, 1).toUpperCase() + spawnerType.substring(1);
            ItemStack is = MobList.getMobIcon(allowedTypes.get(i));
            final ItemMeta meta = is.getItemMeta();
            meta.displayName(en.getSpawnerItemName(spawnerType));
            is.setItemMeta(meta);
            GuiItem guiItem = ItemBuilder.from(is).asGuiItem();
            gui.addItem(guiItem);
        }

        gui.setDefaultClickAction(e -> {
            e.setCancelled(true);
            if(e.getRawSlot() < 45 && e.getClickedInventory().getItem(e.getRawSlot()).getItemMeta().displayName().toString().contains("Spawner")) {
                if(e.getClickedInventory().getItem(e.getRawSlot()).getType() == Material.AIR) return;
                Component comp = e.getInventory().getItem(e.getRawSlot()).getItemMeta().displayName();
                String ptcs = GsonComponentSerializer.colorDownsamplingGson().serialize(comp);
                EntityType et = MobList.getMobEntityType(ptcs);
                String sn = et.name().toLowerCase().replace("_", " ");
                sn = sn.substring(0, 1).toUpperCase() + sn.substring(1);
                ItemStack i = new ItemStack(Material.SPAWNER);
                BlockStateMeta meta = (BlockStateMeta) i.getItemMeta();
                CreatureSpawner spawner = (CreatureSpawner) meta.getBlockState();
                spawner.setSpawnedType(et);
                meta.setBlockState(spawner);
                meta.displayName(en.getSpawnerItemName(sn));
                i.setItemMeta(meta);
                
                // TODO permission check
                if(Permissions.getGUI((Player)e.getWhoClicked())) {
                    e.getWhoClicked().getInventory().addItem(i);
                    e.getWhoClicked().sendMessage(en.getGiveSpawners(1, sn));
                } else {
                    e.getWhoClicked().sendMessage(en.missingPermission("pixyspawners.get.gui"));
                }
            }
        });

        return gui;
    }
}
