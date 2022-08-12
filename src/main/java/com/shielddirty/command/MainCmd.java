package com.shielddirty.command;

import com.shielddirty.Config;
import com.shielddirty.Dirty;
import com.shielddirty.ShieldDirty;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainCmd implements CommandExecutor {
    Player p;
    List <String> dirty = new ArrayList<>();
    int mv;
    int time;
    CommandSender sender;
    @Override
    public boolean onCommand(@NotNull CommandSender  sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        this.sender = sender;
        if (sender instanceof Player) {
            p = (Player) sender;
        }
        if (p.isOp() || sender == Bukkit.getConsoleSender()) {
            if (cmd.getName().equalsIgnoreCase("sd")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                       Config.reloadConfig(ShieldDirty.getPlugin());
                       if (sender == Bukkit.getConsoleSender()) {
                           Bukkit.getConsoleSender().sendMessage("§a重载成功!");
                       } else {
                           p.sendMessage("§a重载成功!");
                       }
                    } else {
                        tips();
                    }
                } else if (args.length == 2) {
                    subCmd(args[0], args[1], sender);
                } else if (args.length == 3) {
                    subCmd(args[0], args[1], args[2], sender);
                } else if (args.length == 4) {
                    subCmd(args[0], args[1], args[2], args[3], sender);
                } else {
                    tips();
                }
            }
        } else {
            p.sendMessage("§c你没有权限使用此命令!");
        }
        return false;
    }

    private void subCmd(String args1, String args2, String args3, String args4, CommandSender sender) {
        if (args1.equalsIgnoreCase("punish")) {
            if ((args2.equalsIgnoreCase("taboo"))) {
                if (args3.equalsIgnoreCase("time")) {
                    try {
                        time = Integer.parseInt(args4);
                    } catch (NumberFormatException e) {
                        if (sender == Bukkit.getConsoleSender()) {
                            Bukkit.getConsoleSender().sendMessage("§c请输入数字哦!");
                        } else {
                            p.sendMessage("§c请输入数字哦!");
                        }
                        return;
                    }
                    Config.setTime(ShieldDirty.getPlugin(), time);
                    if (sender == Bukkit.getConsoleSender()) {
                        Bukkit.getConsoleSender().sendMessage("§a成功设置禁言时长: §b" + time + "§b分钟");
                    } else {
                        p.sendMessage("§a成功设置禁言时长: §b" + time + "§b分钟");
                    }
                } else {
                    tips();
                }
            } else {
                tips();
            }
        } else {
            tips();
        }
    }

    private void subCmd(String args1, String args2, String args3, CommandSender sender) {
        if (args1.equalsIgnoreCase("dirty")) {
            if (args2.equalsIgnoreCase("add")) {
                if (sender == Bukkit.getConsoleSender()) {
                    if (!Dirty.inspectDirty(args3)) {
                        Dirty.addDirty(args3);
                        Bukkit.getConsoleSender().sendMessage("§a成功添加新违禁词: §c" + args3);
                    } else {
                        Bukkit.getConsoleSender().sendMessage("§c此违禁词已存在!");
                    }
                } else {
                    if (!Dirty.inspectDirty(args3)) {
                        Dirty.addDirty(args3);
                        p.sendMessage("§a成功添加新违禁词: §c" + args3);
                    } else {
                        p.sendMessage("§c此违禁词已存在!");
                    }
                }
            } else if (args2.equalsIgnoreCase("remove")) {
                if (sender == Bukkit.getConsoleSender()) {
                    if (Dirty.inspectDirty(args3)) {
                        Dirty.removeDirty(args3);
                        Bukkit.getConsoleSender().sendMessage("§a成功删除违禁词: §c" + args3);
                    } else {
                        Bukkit.getConsoleSender().sendMessage("§c此违禁词不存在!");
                    }
                } else {
                    if (Dirty.inspectDirty(args3)) {
                        Dirty.removeDirty(args3);
                        p.sendMessage("§a成功删除违禁词: §c" + args3);
                    } else {
                        p.sendMessage("§c此违禁词不存在!");
                    }
                }
            } else {
                tips();
            }
        } else if (args1.equalsIgnoreCase("punish")) {
            if (args2.equalsIgnoreCase("mv")) {
                try {
                    mv = Integer.parseInt(args3);
                } catch (NumberFormatException e) {
                    if (sender == Bukkit.getConsoleSender()) {
                        Bukkit.getConsoleSender().sendMessage("§c请输入数字哦!");
                    } else {
                        p.sendMessage("§c请输入数字哦!");
                    }
                    return;
                }
                Config.setMv(ShieldDirty.getPlugin(), mv);
                if (sender == Bukkit.getConsoleSender()) {
                    Bukkit.getConsoleSender().sendMessage("§a成功设置次数: §b" + args3);
                } else {
                    p.sendMessage("§a成功设置次数: §b" + args3);
                }
            } else if ((args2.equalsIgnoreCase("taboo"))) {
                if (Config.enableTaboo(ShieldDirty.getPlugin(), args3)) {
                    if (ShieldDirty.getPlugin().getConfig().getBoolean("punish.taboo.enable")) {
                        if (sender == Bukkit.getConsoleSender()) {
                            Bukkit.getConsoleSender().sendMessage("§d禁言开启!");
                        } else {
                            p.sendMessage("§d禁言开启!");
                        }
                    } else {
                        if (sender == Bukkit.getConsoleSender()) {
                            Bukkit.getConsoleSender().sendMessage("§d禁言关闭!");
                        } else {
                            p.sendMessage("§d禁言关闭!");
                        }
                    }
                } else {
                    tips();
                }
            } else {
                tips();
            }
        } else {
            tips();
        }
    }

    private void subCmd(String args1, String args2, CommandSender sender) {
        if (args1.equalsIgnoreCase("dirty")) {
            if (args2.equalsIgnoreCase("list")) {
                dirty = Dirty.getDirty();
                if (sender == Bukkit.getConsoleSender()) {
                    Bukkit.getConsoleSender().sendMessage("§b当前违禁词有: ");
                    for (int i = 0; i <= dirty.size() - 1; i++) {
                        Bukkit.getConsoleSender().sendMessage("§e-- §c" + dirty.get(i));
                    }
                } else {
                    p.sendMessage("§b当前违禁词有: ");
                    for (int i = 0; i <= dirty.size() - 1; i++) {
                        p.sendMessage("§e-- §c" + dirty.get(i));
                    }
                }
            } else {
                tips();
            }
        } else if (args1.equalsIgnoreCase("punish")) {
            if (Config.enablePunish(ShieldDirty.getPlugin(), args2)) {
                if (ShieldDirty.getPlugin().getConfig().getBoolean("punish.enable")) {
                    if (sender == Bukkit.getConsoleSender()) {
                        Bukkit.getConsoleSender().sendMessage("§d惩罚开启!");
                    } else {
                        p.sendMessage("§d惩罚开启!");
                    }
                } else {
                    if (sender == Bukkit.getConsoleSender()) {
                        Bukkit.getConsoleSender().sendMessage("§d惩罚关闭!");
                    } else {
                        p.sendMessage("§d惩罚关闭!");
                    }
                }
            } else {
                tips();
            }
        } else {
            tips();
        }
    }

    private void tips() {
        if (sender == Bukkit.getConsoleSender()) {
            Bukkit.getConsoleSender().sendMessage("§c命令错误!");
            Bukkit.getConsoleSender().sendMessage("§e/sd reload--重载配置文件");
            Bukkit.getConsoleSender().sendMessage("§e/sd dirty add <违禁词>--添加违禁词");
            Bukkit.getConsoleSender().sendMessage("§e/sd dirty remove <违禁词>--删除违禁词");
            Bukkit.getConsoleSender().sendMessage("§e/sd dirty list--列出违禁词列表");
            Bukkit.getConsoleSender().sendMessage("§e/sd punish on/off--是否打开惩罚功能");
            Bukkit.getConsoleSender().sendMessage("§e/sd punish mv <次数>--设置违规次数");
            Bukkit.getConsoleSender().sendMessage("§e/sd punish taboo on/off--是否打开禁言功能");
            Bukkit.getConsoleSender().sendMessage("§e/sd punish taboo time <时间>--设置禁言时长");
        } else {
            p.sendMessage("§c命令错误!");
            p.sendMessage("§e/sd reload--重载配置文件");
            p.sendMessage("§e/sd dirty add <违禁词>--添加违禁词");
            p.sendMessage("§e/sd dirty remove <违禁词>--删除违禁词");
            p.sendMessage("§e/sd dirty list--列出违禁词列表");
            p.sendMessage("§e/sd punish on/off--是否打开惩罚功能");
            p.sendMessage("§e/sd punish mv <次数>--设置违规次数");
            p.sendMessage("§e/sd punish taboo on/off--是否打开禁言功能");
            p.sendMessage("§e/sd punish taboo time <时间>--设置禁言时长");
        }
    }
}
