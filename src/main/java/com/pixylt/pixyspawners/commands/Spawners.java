package com.pixylt.pixyspawners.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.pixylt.pixyspawners.PixySpawners;
import com.pixylt.pixyspawners.lang.en;
import com.pixylt.pixyspawners.utils.GetSpawner;
import com.pixylt.pixyspawners.utils.MobList;
import com.pixylt.pixyspawners.utils.Permissions;
import com.pixylt.pixyspawners.utils.SpawnersGUI;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import dev.triumphteam.gui.guis.PaginatedGui;

public class Spawners implements TabExecutor {
    public static PixySpawners Plugin() {
        return JavaPlugin.getPlugin(PixySpawners.class);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("spawner") || command.getName().equalsIgnoreCase("spawners")) {
            if (args.length == 1) {
                List<String> completerList = new ArrayList<String>();
                completerList.add("get");
                completerList.add("give");
                completerList.add("gui");
                Collections.sort(completerList);

                return completerList;
            } else if(args.length == 2) {
                if(args[0].equalsIgnoreCase("GET")) {
                    List<String> entityStrings = MobList.getStringList();
                    Collections.sort(entityStrings);

                    if(args[1].length() > 0) {
                        List<String> removalList = new ArrayList<String>();
                        for (String string : entityStrings) {
                            if(!string.startsWith(args[1].toUpperCase())) {
                                removalList.add(string);
                            }
                        }
                        entityStrings.removeAll(removalList);
                    }
    
                    return entityStrings;
                } else if(args[0].equalsIgnoreCase("GIVE")) {
                    return null;
                }
            } else if(args.length == 3) {
                if(args[0].equalsIgnoreCase("GIVE")) {
                    List<String> entityStrings = MobList.getStringList();
                    Collections.sort(entityStrings);

                    if(args[2].length() > 0) {
                        List<String> removalList = new ArrayList<String>();
                        for (String string : entityStrings) {
                            if(!string.startsWith(args[2].toUpperCase())) {
                                removalList.add(string);
                            }
                        }
                        entityStrings.removeAll(removalList);
                    }
    
                    return entityStrings;
                }
            }
        }
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            List<String> allowedTypes = MobList.getStringList();

            if(args.length != 0) {
                switch (args[0].toUpperCase()) {
                    case "DEBUG":
                        MobList.getStringList();
                        break;
                    case "GUI":
                        if(Permissions.getGUI(player)) {
                            SpawnersGUI sgui = new SpawnersGUI();
                            PaginatedGui gui = sgui.getGui();
                            gui.open(player);
                        } else {
                            player.sendMessage(en.missingPermission("pixyspawners.get.gui"));
                        }
                        break;
                    case "GET":
                        if(args.length != 1 && Permissions.get(player)){
                            if(allowedTypes.contains(args[1].toUpperCase())){
                                int amount = 1;
                                if(args.length != 2){
                                    try {
                                        amount = Integer.parseInt(args[2]);
                                    } catch(NumberFormatException nfe){
                                        amount = 1;
                                    }
                                }
                                String spawnerType = args[1].toLowerCase().replace("_", " ");
                                spawnerType = spawnerType.substring(0, 1).toUpperCase() + spawnerType.substring(1);
                                if(amount >= 1){
                                    if(GetSpawner.inventory(player, args[1], amount)){
                                        player.sendMessage(en.getGivingSpawners(amount, spawnerType));
                                    } else {
                                        player.sendMessage(en.error);
                                    }
                                } else {
                                    amount = 1;
                                    if(GetSpawner.inventory(player, args[1], amount)){
                                        player.sendMessage(en.getGivingSpawners(amount, spawnerType));
                                    } else {
                                        player.sendMessage(en.error);
                                    }
                                }
                            } else {
                                player.sendMessage(en.invalidMob);
                            }
                        } else if(Permissions.get(player)) {
                            player.sendMessage(en.spawnersGetUsage);
                        } else {
                            player.sendMessage(en.missingPermission("pixyspawners.get"));
                        }
                        break;
                    case "GIVE":
                            if(Permissions.give(player)){
                                String spawnerType = "";
                                switch(args.length){
                                case 3:
                                        if(Plugin().getServer().getPlayer(args[1]) == null){
                                            player.sendMessage(en.playerNotFound);
                                            break;
                                        }
                                        if(!allowedTypes.contains(args[2].toUpperCase())){
                                            player.sendMessage(en.invalidMob);
                                            break;
                                        }
                                        spawnerType = args[2].toLowerCase().replace("_", " ");
                                        spawnerType = spawnerType.substring(0, 1).toUpperCase() + spawnerType.substring(1);
                                        if(GetSpawner.player(args[1], args[2], 1)){
                                            player.sendMessage(en.getSpawnerGive(args[1], 1, spawnerType));
                                            Plugin().getServer().getPlayer(args[1]).sendMessage(en.getSpawnerGiveReceiver(player.getName(), 1, spawnerType));
                                        } else {
                                            player.sendMessage(en.error);
                                        }
                                        break;
                                case 4:
                                        int amount = 1;
                                        try {
                                            amount = Integer.parseInt(args[3]);
                                        } catch (NumberFormatException nfe) {
                                            player.sendMessage(en.error);
                                            break;
                                        }
                                        if(Plugin().getServer().getPlayer(args[1]) == null){
                                            player.sendMessage(en.playerNotFound);
                                            break;
                                        }
                                        if(!allowedTypes.contains(args[2].toUpperCase())){
                                            player.sendMessage(en.invalidMob);
                                            break;
                                        }
                                        spawnerType = args[2].toLowerCase().replace("_", " ");
                                        spawnerType = spawnerType.substring(0, 1).toUpperCase() + spawnerType.substring(1);
                                        if(GetSpawner.player(args[1], args[2], amount)){
                                            player.sendMessage(en.getSpawnerGive(args[1], amount, spawnerType));
                                            Plugin().getServer().getPlayer(args[1]).sendMessage(en.getSpawnerGiveReceiver(player.getName(), amount, spawnerType));
                                        } else {
                                            player.sendMessage(en.error);
                                        }
                                        break;
                                default:
                                        player.sendMessage(en.spawnersGiveUsage);
                                        break;
                            }
                        } else {
                                player.sendMessage(en.missingPermission("pixyspawners.give"));
                            }
                        break;
                    default:
                        if(Permissions.get(player)){
                            player.sendMessage(en.spawnersGetUsage);
                        }
                        if(Permissions.give(player)){
                            player.sendMessage(en.spawnersGiveUsage);
                        }
                        if(Permissions.getGUI(player)){
                            player.sendMessage(en.spawnersGUIUsage);
                        }
                        if(!Permissions.get(player) && !Permissions.give(player) && !Permissions.getGUI(player)){
                            player.sendMessage(en.missingPermission("pixyspawners.get || pixyspawners.give || pixyspawners.get.gui"));
                        }
                        break;
                }
            } else {
                if(Permissions.get(player)){
                    player.sendMessage(en.spawnersGetUsage);
                }
                if(Permissions.give(player)){
                    player.sendMessage(en.spawnersGiveUsage);
                }
                if(Permissions.getGUI(player)){
                    player.sendMessage(en.spawnersGUIUsage);
                }
                if(!Permissions.get(player) && !Permissions.give(player) && !Permissions.getGUI(player)){
                    player.sendMessage(en.missingPermission("pixyspawners.get || pixyspawners.give || pixyspawners.get.gui"));
                }
            }
        } else {
            sender.sendMessage(en.noConsole);
        }
        return true;
    }
}
