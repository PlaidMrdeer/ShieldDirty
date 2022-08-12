package com.shielddirty;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dirty {
    private static File file;
    private static FileConfiguration dirtyMes;
    private static List<String> dirtyList = new ArrayList<>();
    public static void loadDirty(ShieldDirty plugin) {
        file = new File(plugin.getDataFolder(), "dirty.yml");
        dirtyMes = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            plugin.saveResource("dirty.yml", false);
        }
    }

    public static List<String> getDirty() {
        return dirtyMes.getStringList("dirty");
    }

    public static void addDirty(String dirty) {
        dirtyList = getDirty();
        dirtyList.add(dirty);
        dirtyMes.set("dirty", dirtyList);
        try {
            dirtyMes.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeDirty(String dirty) {
        dirtyList = getDirty();
        dirtyList.remove(dirty);
        dirtyMes.set("dirty", dirtyList);
        try {
            dirtyMes.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean inspectDirty(String dirty) {
        for (int i = 0; i <= dirtyMes.getStringList("dirty").size() - 1; i++) {
            String Dirty = dirtyMes.getStringList("dirty").get(i);
            if (Dirty.equalsIgnoreCase(dirty)) {
                return true;
            }
        }
        return false;
    }
}
