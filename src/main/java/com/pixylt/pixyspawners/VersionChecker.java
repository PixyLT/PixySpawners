package com.pixylt.pixyspawners;

import org.bukkit.Bukkit;

public class VersionChecker {
    public static boolean Check(String version){
        return version.contains("1.16.4") || version.contains("1.16.3") || version.contains("1.16.2") || version.contains("1.16.1");
    }
}
