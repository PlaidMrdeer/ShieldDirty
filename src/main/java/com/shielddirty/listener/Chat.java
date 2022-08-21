package com.shielddirty.listener;

import com.shielddirty.Config;
import com.shielddirty.Data;
import com.shielddirty.Dirty;
import com.shielddirty.ShieldDirty;
import com.shielddirty.thread.Taboo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class Chat implements Listener {
    List<String> dirty = new ArrayList<>();
    String mes;
    String mes1;
    String s;
    Player p;
    boolean punish;
    int dirtyMv;
    int mv;
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        p = e.getPlayer();
        punish = Config.getPunish(ShieldDirty.getPlugin());
        dirtyMv = Data.getDirtyMv(p);
        mv = Config.getMv(ShieldDirty.getPlugin());
        mes = e.getMessage();
        mes1 = mes;
        dirty = Dirty.getDirty();
        s = "";
        if (punish) {
            if (mv == dirtyMv) {
                if (!Data.getDirtyEnable(p)) {
                    Taboo.taboo(ShieldDirty.getPlugin(), p);
                }
                Data.setDirtyEnable(p, true);
                p.sendMessage("§c你已被禁言 §e" + Config.getTime(ShieldDirty.getPlugin()) + "分钟");
                e.setCancelled(true);
                return;
            }
        }
        for (int i = 0; i <= dirty.size() - 1; i++) {
            for(int j = 0;j < dirty.get(i).length(); j++) {
                s += "*";
            }
            mes = mes.replaceAll(dirty.get(i), s);
        }
        if (punish) {
            if (!mes.equalsIgnoreCase(mes1)) {
                Data.addDirtyMv(p, Data.getDirtyMv(p) + 1);
                p.sendMessage("§c检测到你说脏话，已自动屏蔽脏话内容，警告一次");
                p.sendMessage("§e---------------------------------------");
                p.sendMessage("§c被警告多次后，你将受到惩罚");
            }
        }
        e.setMessage(mes);
    }
}