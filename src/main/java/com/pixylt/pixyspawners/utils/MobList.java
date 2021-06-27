package com.pixylt.pixyspawners.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class MobList {
    static Console console = new Console();

    static public List<String> getStringList() {
        List<String> allowedTypes = new ArrayList<String>();
        for(EntityType et : EntityType.values()) {
            if(et.isAlive()) {
                if(et.name() == "PLAYER") continue;
                if(et.name() == "ARMOR_STAND") continue;
                // console.debug("Mob: " + et.name());
                allowedTypes.add(et.name());
            }
        }
        
        return allowedTypes;
    }

    static public ItemStack getMobIcon(String mobName) {
        // switch (mobName.toUpperCase()) {
        //     case "ZOMBIE":
        //         return Material.ZOMBIE_HEAD;
        //     default:
        //         return Material.STRING;
        // }
        return Heads.getHeadForGUI(mobName.toUpperCase());
    }

    static public EntityType getMobEntityType(String mobName) {
        mobName = mobName.substring(13, mobName.length() - 12);
        mobName = mobName.toUpperCase().replace(" ", "_");
        mobName = mobName.strip();
        for(EntityType et : EntityType.values()) {
            if(et.name().hashCode() == mobName.hashCode()) {
                return et;
            }
        }
        return null;
    }
}
