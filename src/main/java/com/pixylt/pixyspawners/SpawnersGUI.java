package com.pixylt.pixyspawners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpawnersGUI {
    public static void gui1(CommandSender cs) {
        Player p = (Player)cs;
        Inventory gui = Bukkit.getServer().createInventory(p, 54, "Page 1 | Select spawner type");

        String[] allowedTypesString = {"BAT", "BEE", "BLAZE", "CAT", "CAVE_SPIDER", "CHICKEN", "COD", "COW", "CREEPER", "DOLPHIN", "DONKEY", "DROWNED", "ELDER_GUARDIAN", "ENDER_DRAGON", "ENDERMAN", "ENDERMITE", "EVOKER", "FOX", "GHAST", "GIANT", "GUARDIAN", "HOGLIN", "HORSE", "ILLUSIONER", "IRON_GOLEM", "LLAMA", "MAGMA_CUBE", "MULE", "MUSHROOM_COW", "OCELOT", "PANDA", "PARROT", "PHANTOM", "PIG", "PIGLIN", "PILLAGER", "POLAR_BEAR", "PUFFERFISH", "RABBIT", "RAVAGER", "SALMON", "SHEEP", "SILVERFISH", "SKELETON", "SKELETON_HORSE", "SLIME", "SNOWMAN", "SPIDER", "SQUID", "STRAY", "STRIDER", "TRADER_LLAMA", "TROPICAL_FISH", "TURTLE", "VEX", "VILLAGER", "VINDICATOR", "WANDERING_TRADER", "WITCH", "WITHER", "WITHER_SKELETON", "WOLF", "ZOGLIN", "ZOMBIE", "ZOMBIE_HORSE", "ZOMBIE_VILLAGER", "ZOMBIEFIED_PIGLIN"};
        Material[] allowedTypesIconsString = {Material.BAT_SPAWN_EGG, Material.BEE_SPAWN_EGG, Material.BLAZE_SPAWN_EGG, Material.CAT_SPAWN_EGG, Material.CAVE_SPIDER_SPAWN_EGG, Material.CHICKEN_SPAWN_EGG, Material.COD_SPAWN_EGG, Material.COW_SPAWN_EGG, Material.CREEPER_SPAWN_EGG, Material.DOLPHIN_SPAWN_EGG, Material.DONKEY_SPAWN_EGG, Material.DROWNED_SPAWN_EGG, Material.ELDER_GUARDIAN_SPAWN_EGG, Material.DRAGON_EGG, Material.ENDERMAN_SPAWN_EGG, Material.ENDERMITE_SPAWN_EGG, Material.EVOKER_SPAWN_EGG, Material.FOX_SPAWN_EGG, Material.GHAST_SPAWN_EGG, Material.ZOMBIE_HEAD, Material.GUARDIAN_SPAWN_EGG, Material.HOGLIN_SPAWN_EGG, Material.HORSE_SPAWN_EGG, Material.TIPPED_ARROW, Material.IRON_BLOCK, Material.LLAMA_SPAWN_EGG, Material.MAGMA_CUBE_SPAWN_EGG, Material.MULE_SPAWN_EGG, Material.BROWN_MUSHROOM, Material.OCELOT_SPAWN_EGG, Material.PANDA_SPAWN_EGG, Material.PARROT_SPAWN_EGG, Material.PHANTOM_SPAWN_EGG, Material.PIG_SPAWN_EGG, Material.PIGLIN_SPAWN_EGG, Material.PILLAGER_SPAWN_EGG, Material.POLAR_BEAR_SPAWN_EGG, Material.PUFFERFISH_SPAWN_EGG, Material.RABBIT_SPAWN_EGG, Material.RAVAGER_SPAWN_EGG, Material.SALMON_SPAWN_EGG, Material.SHEEP_SPAWN_EGG, Material.SILVERFISH_SPAWN_EGG, Material.SKELETON_SPAWN_EGG, Material.SKELETON_HORSE_SPAWN_EGG, Material.SLIME_SPAWN_EGG, Material.SNOWBALL, Material.SPIDER_SPAWN_EGG, Material.SQUID_SPAWN_EGG, Material.STRAY_SPAWN_EGG, Material.STRIDER_SPAWN_EGG, Material.TRADER_LLAMA_SPAWN_EGG, Material.TROPICAL_FISH_SPAWN_EGG, Material.TURTLE_SPAWN_EGG, Material.VEX_SPAWN_EGG, Material.VILLAGER_SPAWN_EGG, Material.VINDICATOR_SPAWN_EGG, Material.WANDERING_TRADER_SPAWN_EGG, Material.WITCH_SPAWN_EGG, Material.WITHER_SKELETON_SKULL, Material.WITHER_SKELETON_SPAWN_EGG, Material.WOLF_SPAWN_EGG, Material.ZOGLIN_SPAWN_EGG, Material.ZOMBIE_SPAWN_EGG, Material.ZOMBIE_HORSE_SPAWN_EGG, Material.ZOMBIE_VILLAGER_SPAWN_EGG, Material.ZOMBIFIED_PIGLIN_SPAWN_EGG};
        List<String> allowedTypes = new ArrayList();
        List<Material> allowedTypesIcons = new ArrayList();
        allowedTypes.addAll(Arrays.asList(allowedTypesString));
        allowedTypesIcons.addAll(Arrays.asList(allowedTypesIconsString));

        for(int i = 0; i<45; i++) {
            String spawnerType = allowedTypes.get(i).toLowerCase().replace("_", " ");
            spawnerType = spawnerType.substring(0, 1).toUpperCase() + spawnerType.substring(1);
            ItemStack spawnerItem = new ItemStack(allowedTypesIcons.get(i));
            ItemMeta spawnerItemMeta = spawnerItem.getItemMeta();
            ArrayList<String> spawnerItemLore = new ArrayList<String>();
            spawnerItemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            spawnerItem.setItemMeta(spawnerItemMeta);
            spawnerItemMeta.setLore(spawnerItemLore);
            spawnerItemMeta.setDisplayName(LangEn.getSpawnerItemName(spawnerType));
            spawnerItem.setItemMeta(spawnerItemMeta);

            gui.setItem(i, spawnerItem);
        }

        ItemStack spawnerItem = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        ItemMeta spawnerItemMeta = spawnerItem.getItemMeta();
        ArrayList<String> spawnerItemLore = new ArrayList<String>();
        spawnerItemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        spawnerItem.setItemMeta(spawnerItemMeta);
        spawnerItemMeta.setLore(spawnerItemLore);
        spawnerItemMeta.setDisplayName(ChatColor.GREEN + "Back");
        spawnerItem.setItemMeta(spawnerItemMeta);

        gui.setItem(45, spawnerItem);

        spawnerItem = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        spawnerItemMeta = spawnerItem.getItemMeta();
        spawnerItemLore = new ArrayList<String>();
        spawnerItemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        spawnerItem.setItemMeta(spawnerItemMeta);
        spawnerItemMeta.setLore(spawnerItemLore);
        spawnerItemMeta.setDisplayName(ChatColor.GREEN + "Next");
        spawnerItem.setItemMeta(spawnerItemMeta);

        gui.setItem(53, spawnerItem);
        p.openInventory(gui);
    }
    public static void gui2(CommandSender cs) {
        Player p = (Player)cs;
        Inventory gui = Bukkit.getServer().createInventory(p, 54, "Page 2 | Select spawner type");

        String[] allowedTypesString = {"BAT", "BEE", "BLAZE", "CAT", "CAVE_SPIDER", "CHICKEN", "COD", "COW", "CREEPER", "DOLPHIN", "DONKEY", "DROWNED", "ELDER_GUARDIAN", "ENDER_DRAGON", "ENDERMAN", "ENDERMITE", "EVOKER", "FOX", "GHAST", "GIANT", "GUARDIAN", "HOGLIN", "HORSE", "ILLUSIONER", "IRON_GOLEM", "LLAMA", "MAGMA_CUBE", "MULE", "MUSHROOM_COW", "OCELOT", "PANDA", "PARROT", "PHANTOM", "PIG", "PIGLIN", "PILLAGER", "POLAR_BEAR", "PUFFERFISH", "RABBIT", "RAVAGER", "SALMON", "SHEEP", "SILVERFISH", "SKELETON", "SKELETON_HORSE", "SLIME", "SNOWMAN", "SPIDER", "SQUID", "STRAY", "STRIDER", "TRADER_LLAMA", "TROPICAL_FISH", "TURTLE", "VEX", "VILLAGER", "VINDICATOR", "WANDERING_TRADER", "WITCH", "WITHER", "WITHER_SKELETON", "WOLF", "ZOGLIN", "ZOMBIE", "ZOMBIE_HORSE", "ZOMBIE_VILLAGER", "ZOMBIEFIED_PIGLIN"};
        Material[] allowedTypesIconsString = {Material.BAT_SPAWN_EGG, Material.BEE_SPAWN_EGG, Material.BLAZE_SPAWN_EGG, Material.CAT_SPAWN_EGG, Material.CAVE_SPIDER_SPAWN_EGG, Material.CHICKEN, Material.COD_SPAWN_EGG, Material.COW_SPAWN_EGG, Material.CREEPER_SPAWN_EGG, Material.DOLPHIN_SPAWN_EGG, Material.DONKEY_SPAWN_EGG, Material.DROWNED_SPAWN_EGG, Material.ELDER_GUARDIAN_SPAWN_EGG, Material.DRAGON_EGG, Material.ENDERMAN_SPAWN_EGG, Material.ENDERMITE_SPAWN_EGG, Material.EVOKER_SPAWN_EGG, Material.FOX_SPAWN_EGG, Material.GHAST_SPAWN_EGG, Material.ZOMBIE_HEAD, Material.GUARDIAN_SPAWN_EGG, Material.HOGLIN_SPAWN_EGG, Material.HORSE_SPAWN_EGG, Material.TIPPED_ARROW, Material.IRON_BLOCK, Material.LLAMA_SPAWN_EGG, Material.MAGMA_CUBE_SPAWN_EGG, Material.MULE_SPAWN_EGG, Material.BROWN_MUSHROOM, Material.OCELOT_SPAWN_EGG, Material.PANDA_SPAWN_EGG, Material.PARROT_SPAWN_EGG, Material.PHANTOM_SPAWN_EGG, Material.PIG_SPAWN_EGG, Material.PIGLIN_SPAWN_EGG, Material.PILLAGER_SPAWN_EGG, Material.POLAR_BEAR_SPAWN_EGG, Material.PUFFERFISH_SPAWN_EGG, Material.RABBIT_SPAWN_EGG, Material.RAVAGER_SPAWN_EGG, Material.SALMON_SPAWN_EGG, Material.SHEEP_SPAWN_EGG, Material.SILVERFISH_SPAWN_EGG, Material.SKELETON_SPAWN_EGG, Material.SKELETON_HORSE_SPAWN_EGG, Material.SLIME_SPAWN_EGG, Material.SNOWBALL, Material.SPIDER_SPAWN_EGG, Material.SQUID_SPAWN_EGG, Material.STRAY_SPAWN_EGG, Material.STRIDER_SPAWN_EGG, Material.TRADER_LLAMA_SPAWN_EGG, Material.TROPICAL_FISH_SPAWN_EGG, Material.TURTLE_SPAWN_EGG, Material.VEX_SPAWN_EGG, Material.VILLAGER_SPAWN_EGG, Material.VINDICATOR_SPAWN_EGG, Material.WANDERING_TRADER_SPAWN_EGG, Material.WITCH_SPAWN_EGG, Material.WITHER_SKELETON_SKULL, Material.WITHER_SKELETON_SPAWN_EGG, Material.WOLF_SPAWN_EGG, Material.ZOGLIN_SPAWN_EGG, Material.ZOMBIE_SPAWN_EGG, Material.ZOMBIE_HORSE_SPAWN_EGG, Material.ZOMBIE_VILLAGER_SPAWN_EGG, Material.ZOMBIFIED_PIGLIN_SPAWN_EGG};
        List<String> allowedTypes = new ArrayList();
        List<Material> allowedTypesIcons = new ArrayList();
        allowedTypes.addAll(Arrays.asList(allowedTypesString));
        allowedTypesIcons.addAll(Arrays.asList(allowedTypesIconsString));

        for(int i = 45; i<allowedTypes.size(); i++) {
            String spawnerType = allowedTypes.get(i).toLowerCase().replace("_", " ");
            spawnerType = spawnerType.substring(0, 1).toUpperCase() + spawnerType.substring(1);
            ItemStack spawnerItem = new ItemStack(allowedTypesIcons.get(i));
            ItemMeta spawnerItemMeta = spawnerItem.getItemMeta();
            ArrayList<String> spawnerItemLore = new ArrayList<String>();
            spawnerItemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            spawnerItem.setItemMeta(spawnerItemMeta);
            spawnerItemMeta.setLore(spawnerItemLore);
            spawnerItemMeta.setDisplayName(LangEn.getSpawnerItemName(spawnerType));
            spawnerItem.setItemMeta(spawnerItemMeta);

            gui.setItem(i-45, spawnerItem);
        }

        ItemStack spawnerItem = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        ItemMeta spawnerItemMeta = spawnerItem.getItemMeta();
        ArrayList<String> spawnerItemLore = new ArrayList<String>();
        spawnerItemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        spawnerItem.setItemMeta(spawnerItemMeta);
        spawnerItemMeta.setLore(spawnerItemLore);
        spawnerItemMeta.setDisplayName(ChatColor.GREEN + "Back");
        spawnerItem.setItemMeta(spawnerItemMeta);

        gui.setItem(45, spawnerItem);

        spawnerItem = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        spawnerItemMeta = spawnerItem.getItemMeta();
        spawnerItemLore = new ArrayList<String>();
        spawnerItemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        spawnerItem.setItemMeta(spawnerItemMeta);
        spawnerItemMeta.setLore(spawnerItemLore);
        spawnerItemMeta.setDisplayName(ChatColor.GREEN + "Next");
        spawnerItem.setItemMeta(spawnerItemMeta);

        gui.setItem(53, spawnerItem);
        p.openInventory(gui);

    }
}
