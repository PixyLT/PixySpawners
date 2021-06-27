package com.pixylt.pixyspawners.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerUtils {
    public static int freeInvSpaces(Player player) {

        int count = 0;
        for (ItemStack i : player.getInventory().getContents()) {
            if(i == null) {
                count++;
            }
        }
        if (player.getInventory().getHelmet() == null) {
            count--;
        }
        if (player.getInventory().getChestplate() == null) {
            count--;
        }
        if (player.getInventory().getLeggings() == null) {
            count--;
        }
        if (player.getInventory().getBoots() == null) {
            count--;
        }
        if (player.getInventory().getItemInOffHand() != null) {
            count--;
        }
        return count;
    }
}
