package com.pixylt.pixyspawners;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PixySpawnersCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!Permissions.help(player)){
                player.sendMessage(LangEn.missingPerm);
            } else {
                if(args.length != 0){
                    switch(args[0].toUpperCase()){
                        case "ABOUT":
                            player.sendMessage(LangEn.about);
                            break;
                        case "RELOAD":
                            if(Permissions.reload(player)){
                                player.sendMessage(LangEn.configReloaded);
                                ConfigLegacy.reloadConfig();
                            } else {
                                player.sendMessage(LangEn.missingPerm);
                            }
                            break;
                        case "CHECK":
                            ConfigLegacy.check(player);
                            break;
                        default:
                            player.sendMessage(LangEn.help);
                            break;
                    }
                } else {
                    player.sendMessage(LangEn.help);
                }
            }
        } else {
            sender.sendMessage(ChatColor.GREEN + "This plugin is not intended to work in console yet!");
        }

        return true;
    }
}
