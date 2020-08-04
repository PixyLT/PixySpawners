package com.pixylt.pixyspawners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class HoloProtectionListener implements Listener {
    @EventHandler
    public void manipulate(PlayerArmorStandManipulateEvent e){
        if(!e.getRightClicked().isVisible()){
            e.setCancelled(true);
        }
    }
}
