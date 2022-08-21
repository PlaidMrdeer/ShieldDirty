package com.shielddirty;

import com.shielddirty.command.CmdTab;
import com.shielddirty.command.MainCmd;
import com.shielddirty.listener.Chat;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ShieldDirty extends JavaPlugin {
    private static ShieldDirty plugin;

    public static ShieldDirty getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();

        Dirty.loadDirty(getPlugin());

        Data.loadData(getPlugin());

        say("§e[§a" + getDescription().getName() + "§e]§a==================================");
        say("§e[§a" + getDescription().getName() + "§e]§a     作者: §b" + getDescription().getAuthors());
        say("§e[§a" + getDescription().getName() + "§e]§a     版本: §b" + getDescription().getVersion());
        say("§e[§a" + getDescription().getName() + "§e]§a     QQ交流群: §b759010920");
        say("§e[§a" + getDescription().getName() + "§e]§a==================================");

        getServer().getPluginManager().registerEvents(new Chat(), this);

        Objects.requireNonNull(getServer().getPluginCommand("sd")).setExecutor(new MainCmd());

        Objects.requireNonNull(getServer().getPluginCommand("sd")).setTabCompleter(new CmdTab());

        int pluginId = 16212;
        Metrics metrics = new Metrics(this, pluginId);
    }

    private void say(String say) {
        getServer().getConsoleSender().sendMessage(say);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§e[§a" + getDescription().getName() + "§e]§b已卸载");
    }
}
