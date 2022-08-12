package com.shielddirty.thread;

import com.shielddirty.Config;
import com.shielddirty.Data;
import com.shielddirty.ShieldDirty;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Taboo {
    private static int time;
    public static void taboo(ShieldDirty plugin, Player p) {
        time = Config.getTime(plugin) * 1200;
        new BukkitRunnable() {
            @Override
            public void run() {
                Data.setDirtyMv(p);
                Data.setDirtyEnable(p, false);
                for (Player p1 : Bukkit.getOnlinePlayers()) {
                    if (p1.equals(Bukkit.getPlayerExact(p.getName()))) {
                        p.sendMessage("§a禁言解除，现在你可以正常聊天了");
                    }
                }
                cancel();
            }
        }.runTaskTimer(plugin, time, 0L);
    }
}
