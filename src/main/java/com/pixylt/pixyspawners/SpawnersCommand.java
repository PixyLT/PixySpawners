package com.pixylt.pixyspawners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpawnersCommand implements CommandExecutor {
    public static PixySpawners Plugin() {
        return JavaPlugin.getPlugin(PixySpawners.class);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            String[] allowedTypesString = {"BAT", "BEE", "BLAZE", "CAT", "CAVE_SPIDER", "CHICKEN", "COD", "COW", "CREEPER", "DOLPHIN", "DONKEY", "DROWNED", "ELDER_GUARDIAN", "ENDER_DRAGON", "ENDERMAN", "ENDERMITE", "EVOKER", "FOX", "GHAST", "GIANT", "GUARDIAN", "HOGLIN", "HORSE", "HUSH", "ILLUSIONER", "IRON_GOLEM", "LLAMA", "MAGMA_CUBE", "MULE", "MUSHROOM_COW", "OCELOT", "PANDA", "PARROT", "PHANTOM", "PIG", "PIGLIN", "PILLAGER", "POLAR_BEAR", "PUFFERFISH", "RABBIT", "RAVAGER", "SALMON", "SHEEP", "SILVERFISH", "SKELETON", "SKELETON_HORSE", "SLIME", "SNOWMAN", "SPIDER", "SQUID", "STRAY", "STRIDER", "TRADER_LLAMA", "TROPICAL_FISH", "TURTLE", "VEX", "VILLAGER", "VINDICATOR", "WANDERING_TRADER", "WITCH", "WITHER", "WOLF", "ZOGLIN", "ZOMBIE", "ZOMBIE_HORSE", "ZOMBIE_VILLAGER", "ZOMBIEFIED_PIGLIN"};
            List allowedTypes = new ArrayList();
            allowedTypes.addAll(Arrays.asList(allowedTypesString));

            if(args.length != 0){
                switch(args[0].toUpperCase()){
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
                                        player.sendMessage(LangEn.getGivingSpawners(amount, spawnerType));
                                    } else {
                                        player.sendMessage(LangEn.error);
                                    }
                                } else {
                                    amount = 1;
                                    if(GetSpawner.inventory(player, args[1], amount)){
                                        player.sendMessage(LangEn.getGivingSpawners(amount, spawnerType));
                                    } else {
                                        player.sendMessage(LangEn.error);
                                    }
                                }
                            } else {
                                player.sendMessage(LangEn.invalidMob);
                            }
                        } else if(Permissions.get(player)) {
                            player.sendMessage(LangEn.spawnersGetUsage);
                        } else {
                            player.sendMessage(LangEn.missingPerm);
                        }
                        break;
                    case "GIVE":
                        if(Permissions.give(player)){
                            String spawnerType = "";
                            switch(args.length){
                                case 3:
                                    if(Plugin().getServer().getPlayer(args[1]) == null){
                                        player.sendMessage(LangEn.playerNotFound);
                                        break;
                                    }
                                    if(!allowedTypes.contains(args[2].toUpperCase())){
                                        player.sendMessage(LangEn.invalidMob);
                                        break;
                                    }
                                    spawnerType = args[2].toLowerCase().replace("_", " ");
                                    spawnerType = spawnerType.substring(0, 1).toUpperCase() + spawnerType.substring(1);
                                    if(GetSpawner.player(args[1], args[2], 1)){
                                        player.sendMessage(LangEn.getSpawnerGive(args[1], 1, spawnerType));
                                        Plugin().getServer().getPlayer(args[1]).sendMessage(LangEn.getSpawnerGiveReceiver(player.getName(), 1, spawnerType));
                                    } else {
                                        player.sendMessage(LangEn.error);
                                    }
                                    break;
                                case 4:
                                    int amount = 1;
                                    try {
                                        amount = Integer.parseInt(args[3]);
                                    } catch (NumberFormatException nfe) {
                                        player.sendMessage(LangEn.error);
                                        break;
                                    }
                                    if(Plugin().getServer().getPlayer(args[1]) == null){
                                        player.sendMessage(LangEn.playerNotFound);
                                        break;
                                    }
                                    if(!allowedTypes.contains(args[2].toUpperCase())){
                                        player.sendMessage(LangEn.invalidMob);
                                        break;
                                    }
                                    spawnerType = args[2].toLowerCase().replace("_", " ");
                                    spawnerType = spawnerType.substring(0, 1).toUpperCase() + spawnerType.substring(1);
                                    if(GetSpawner.player(args[1], args[2], 1)){
                                        player.sendMessage(LangEn.getSpawnerGive(args[1], amount, spawnerType));
                                        Plugin().getServer().getPlayer(args[1]).sendMessage(LangEn.getSpawnerGiveReceiver(player.getName(), 1, spawnerType));
                                    } else {
                                        player.sendMessage(LangEn.error);
                                    }
                                    break;
                                default:
                                    player.sendMessage(LangEn.spawnersGiveUsage);
                                    break;
                            }
                        } else {
                            player.sendMessage(LangEn.missingPerm);
                        }
                        break;
                    case "GUI":
                        if(Permissions.getGUI(player)){
                            player.sendMessage(LangEn.openingGUI);
                            SpawnersGUI.gui1(sender);
                        } else {
                            player.sendMessage(LangEn.missingPerm);
                        }
                        break;
                    default:
                        if(Permissions.get(player)){
                            player.sendMessage(LangEn.spawnersGetUsage);
                        }
                        if(Permissions.give(player)){
                            player.sendMessage(LangEn.spawnersGiveUsage);
                        }
                        if(Permissions.getGUI(player)){
                            player.sendMessage(LangEn.spawnersGUIUsage);
                        }
                        if(!Permissions.get(player) && !Permissions.give(player) && !Permissions.getGUI(player)){
                            player.sendMessage(LangEn.missingPerm);
                        }
                        break;
                }
            } else {
                if(Permissions.get(player)){
                    player.sendMessage(LangEn.spawnersGetUsage);
                }
                if(Permissions.give(player)){
                    player.sendMessage(LangEn.spawnersGiveUsage);
                }
                if(Permissions.getGUI(player)){
                    player.sendMessage(LangEn.spawnersGUIUsage);
                }
                if(!Permissions.get(player) && !Permissions.give(player) && !Permissions.getGUI(player)){
                    player.sendMessage(LangEn.missingPerm);
                }
            }
        } else {
            sender.sendMessage(LangEn.noConsole);
        }

        return true;
    }
}
