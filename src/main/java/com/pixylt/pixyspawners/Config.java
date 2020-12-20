package com.pixylt.pixyspawners;

import com.google.gson.Gson;
import org.bukkit.Bukkit;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Config {
    public static boolean exists(String xyz){
        return ConfigLegacy.getSpawnerConfig().get(xyz) != null;
    }
    public static String getOwner(String xyz){
        Gson gson = new Gson();
        String json = ConfigLegacy.getSpawnerConfig().get(xyz).toString();
        Spawner spawner = gson.fromJson(json, Spawner.class);
        return spawner.getOwner();
    }
    public static String getCount(String xyz){
        Gson gson = new Gson();
        String json = ConfigLegacy.getSpawnerConfig().get(xyz).toString();
        Spawner spawner = gson.fromJson(json, Spawner.class);
        return spawner.getCount();
    }
    public static String getHolo(String xyz){
        Gson gson = new Gson();
        String json = ConfigLegacy.getSpawnerConfig().get(xyz).toString();
        Spawner spawner = gson.fromJson(json, Spawner.class);
        return spawner.getHolo();
    }
    public static void remove(String xyz){
        ConfigLegacy.getSpawnerConfig().set(xyz, null);
    }
    public static void store(String xyz, String owner, String holo, int count){
        Gson gson = new Gson();
        ConfigLegacy.getSpawnerConfig().set(xyz, gson.toJson(new Spawner(xyz, owner, holo, count)));
        ConfigLegacy.saveSpawnerConfig();
//
//        List spawnerList = new ArrayList<>();
//        spawnerList.add(new Spawner(xyz, owner, holo, count));
//
//        try{
//            String json = gson.toJson(spawnerList);
//
//            FileWriter fw = new FileWriter("Spawners.dat");
//            fw.write(json);
//            fw.close();
//        } catch(IOException ex){
//            Bukkit.getConsoleSender().sendMessage(Globals.prefixv + "Exception trying to save file.. " + ex.getMessage());
//        }
    }
}
