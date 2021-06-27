package com.pixylt.pixyspawners.utils;

public class VersionChecker {
    public static boolean Check(String version){
        return version.contains("1.16.5") || version.contains("1.16.4") || version.contains("1.16.3") || version.contains("1.16.2") || version.contains("1.16.1");
    }
    public static boolean RGB(String version) {
        return version.contains("1.16") || version.contains("1.16.1") || version.contains("1.16.2") || version.contains("1.16.3") || version.contains("1.16.4") || version.contains("1.16.5");
    }
}
