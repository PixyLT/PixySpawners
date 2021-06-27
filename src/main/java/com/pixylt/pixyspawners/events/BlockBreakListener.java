package com.pixylt.pixyspawners.events;

import com.pixylt.pixyspawners.PixySpawners;
import com.pixylt.pixyspawners.classes.Spawner;
import com.pixylt.pixyspawners.compat.GriefPrevention;
import com.pixylt.pixyspawners.lang.en;
import com.pixylt.pixyspawners.utils.Config;
import com.pixylt.pixyspawners.utils.Console;
import com.pixylt.pixyspawners.utils.Holograms;
import com.pixylt.pixyspawners.utils.Permissions;
import com.pixylt.pixyspawners.utils.PlayerUtils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class BlockBreakListener implements Listener {
    Material spawnerMaterial = Material.SPAWNER;
    Console console = new Console();

    PixySpawners plugin;

    public BlockBreakListener(PixySpawners instance) {
        plugin = instance;
    }
    Config config = new Config();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        config.reload();
        if(e.getPlayer() == null) return;
        if(e.getBlock().getType() != spawnerMaterial) return;

        e.setCancelled(true);

        if(GriefPrevention.check()) {
            if(!GriefPrevention.allowBreak(e.getPlayer(), e.getBlock(), e.getBlock().getLocation())) {
                e.getPlayer().sendMessage(en.notAllowedToBreak);
                return;
            }
        }

        if(config.mainGetB("require-silktouch") && !config.mainGetB("can-remove-without-silktouch")) {
            if(e.getPlayer().getInventory().getItemInMainHand() == null
            || !e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                e.getPlayer().sendMessage(en.silktouchRequired);
                return;
            }
        }

        if(!Permissions.mine(e.getPlayer())) {
            e.getPlayer().sendMessage(en.missingPermission("pixyspawners.mine"));
            return;
        }

        int count = PlayerUtils.freeInvSpaces(e.getPlayer());
        if(count == 0) {
            // TODO drop spawner and show message
            e.getPlayer().sendMessage(en.playerInventoryFull);
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
        
        String xyz = e.getBlock().getWorld().getName() + String.valueOf(x) + "-" + String.valueOf(y) + "-" + String.valueOf(z);
        config.reload();
        Spawner cfgSpawner = config.getSpawner(xyz);
        if(cfgSpawner != null) {
            amount = Integer.parseInt(cfgSpawner.getCount());
            if(config.mainGetB("one-owner") && cfgSpawner.getOwner() != player.getName()){
                e.getPlayer().sendMessage(en.oneOwnerError);
            }
        } else {
            amount = 1;
        }
        e.getPlayer().sendMessage(en.getSpawnersRemoved(amount, type.name()));

        config.breakSpawner(xyz);
        String sn = type.name().toLowerCase().replace("_", " ");
        sn = sn.substring(0, 1).toUpperCase() + sn.substring(1);

        // TODO Holo stuff
        Holograms.removeSpawner(e.getBlock().getLocation());

        Player p = player;
        ItemStack i = new ItemStack(spawnerMaterial, amount);
        BlockStateMeta meta = (BlockStateMeta) i.getItemMeta();
        CreatureSpawner spawner = (CreatureSpawner) meta.getBlockState();
        spawner.setSpawnedType(type);
        meta.setBlockState(spawner);
        meta.displayName(en.getSpawnerItemName(sn));
        i.setItemMeta(meta);
        
        p.getInventory().addItem(i);

        // if this spawner has holo
        // if(config.mainGet("hologram-enabled") == "true") {
        //     console.warn("Holograms are not made yet");
        //     e.getPlayer().sendMessage(en.todo);
        //     // TODO: holo.spawn(etc etc)
        // }

    }
}
