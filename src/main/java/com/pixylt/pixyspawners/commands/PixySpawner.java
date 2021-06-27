package com.pixylt.pixyspawners.commands;

import com.pixylt.pixyspawners.lang.en;
import com.pixylt.pixyspawners.utils.Holograms;
import com.pixylt.pixyspawners.utils.Permissions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class PixySpawner implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(!Permissions.help(player)) {
                player.sendMessage(en.missingPermission("pixyspawners.help"));
            } else {
                if(args.length != 0) {
                    switch(args[0].toUpperCase()) {
                        case "ABOUT":
                            player.sendMessage(en.about);
                            break;
                        case "RELOAD":
                            if(Permissions.reload(player)) {
                                // TODO Reload here
                                // TODO Check config
                                player.sendMessage(en.prefix + en.textToComp("Currently reloading config is not needed!"));
                            } else {
                                player.sendMessage(en.missingPermission("pixyspawners.reload"));
                            }
                            break;
                        case "REMOVEHOLOGRAMS":
                            if(Permissions.all(player)) {
                                Holograms.removeAllHolos();
                            } else {
                                player.sendMessage(en.missingPermission("pixyspawners.all"));
                            }
                            break;
                        default:
                            player.sendMessage(en.help);
                            break;
                    }
                } else {
                    sender.sendMessage(en.help);
                }
            }
        } else {
            sender.sendMessage(ChatColor.GREEN + "This plugin is not intended to be used in console yet");
        }

        return true;
    }
}
