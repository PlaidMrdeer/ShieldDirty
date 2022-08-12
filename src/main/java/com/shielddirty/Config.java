package com.shielddirty;

public class Config {
    public static void reloadConfig(ShieldDirty plugin) {
        plugin.reloadConfig();
    }

    public static boolean enablePunish(ShieldDirty plugin, String enable) {
        if (enable.equalsIgnoreCase("on")) {
            plugin.getConfig().set("punish.enable", true);
            plugin.saveConfig();
            return true;
        } else if (enable.equalsIgnoreCase("off")) {
            plugin.getConfig().set("punish.enable", false);
            plugin.saveConfig();
            return true;
        }
        return false;
    }

    public static boolean enableTaboo(ShieldDirty plugin, String enable) {
        if (enable.equalsIgnoreCase("on")) {
            plugin.getConfig().set("punish.taboo.enable", true);
            plugin.saveConfig();
            return true;
        } else if (enable.equalsIgnoreCase("off")) {
            plugin.getConfig().set("punish.taboo.enable", false);
            plugin.saveConfig();
            return true;
        }
        return false;
    }

    public static void setMv(ShieldDirty plugin, int mv) {
        plugin.getConfig().set("punish.max-violation", mv);
        plugin.saveConfig();
    }

    public static void setTime(ShieldDirty plugin, int time) {
        plugin.getConfig().set("punish.taboo.time", time);
        plugin.saveConfig();
    }

    public static boolean getPunish(ShieldDirty plugin) {
        return plugin.getConfig().getBoolean("punish.enable");
    }
    public static int getMv(ShieldDirty plugin) {
        return plugin.getConfig().getInt("punish.max-violation");
    }
    public static int getTime(ShieldDirty plugin) {
        return plugin.getConfig().getInt("punish.taboo.time");
    }
}
