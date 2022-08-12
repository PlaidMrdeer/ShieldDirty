package com.shielddirty;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Data {
    private static File file;
    private static FileConfiguration data;
    public static void loadData(ShieldDirty plugin) {
        file = new File(plugin.getDataFolder(), "data.yml");
        data = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            plugin.saveResource("data.yml", false);
        }
    }

    public static void addDirtyMv(Player p, int dirty) {
        data.set(p.getName() + ".mv", dirty);
        try {
            data.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getDirtyMv(Player p) {
        if (data.contains(p.getName())) {
            return data.getInt(p.getName() + ".mv");
        }
        return 0;
    }

    public static void setDirtyEnable(Player p, boolean dirty) {
        data.set(p.getName() + ".enable", dirty);
        try {
            data.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean getDirtyEnable(Player p) {
        return data.getBoolean(p.getName() + ".enable");
    }

    public static void setDirtyMv(Player p) {
        data.set(p.getName() + ".mv", 0);
        try {
            data.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
