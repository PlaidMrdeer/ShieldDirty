package com.playerguild;

import com.playerguild.createguild.CreateGuild;
import com.playerguild.joinguild.JoinGuild;
import com.playerguild.quitguild.QuitGuild;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {
    Player p;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pg")) {
            if (sender instanceof Player) {
                p = (Player) sender;
                switch (args[0]) {
                    case "create":
                        if (args.length == 2) {
                            CreateGuild.createGuild(p, args[1]);
                        } else {
                            p.sendMessage(ChatColor.RED + "命令错误!");
                            p.sendMessage(ChatColor.RED + "/pg create <公会名称>");
                        }
                        break;
                    case "join":
                        if (args.length == 2) {
                            JoinGuild.joinTeam(p, args[1]);
                        } else {
                            p.sendMessage(ChatColor.RED + "命令错误!");
                            p.sendMessage(ChatColor.RED + "/pg join <公会名称>");
                        }
                        break;
                    case "quit":
                        if (args.length == 2) {
                            QuitGuild.quitTeam(p, args[1]);
                        } else {
                            p.sendMessage(ChatColor.RED + "命令错误!");
                            p.sendMessage(ChatColor.RED + "/pg quit <公会名称>");
                        }
                        break;
                }
            }
            return true;
        }
        return false;
    }
}
