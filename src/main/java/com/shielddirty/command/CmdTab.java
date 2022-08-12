package com.shielddirty.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CmdTab implements TabCompleter {
    List<String> tab = new ArrayList<>();
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("sd")) {
            tab.clear();
            if (args.length == 1) {
                tab.add("dirty");
                tab.add("reload");
                tab.add("punish");
            } else if (args.length == 2 && args[0].equalsIgnoreCase("dirty")) {
                tab.add("add");
                tab.add("remove");
                tab.add("list");
            } else if (args.length == 2 && args[0].equalsIgnoreCase("punish")) {
                tab.add("on");
                tab.add("off");
                tab.add("mv");
                tab.add("taboo");
            } else if (args.length == 3 && args[1].equalsIgnoreCase("taboo")) {
                tab.add("on");
                tab.add("off");
                tab.add("time");
            }
            return tab;
        }
        return null;
    }
}
