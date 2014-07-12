package com.tenjava.entries.hintss.t3;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Henry on 7/12/2014.
 */
public class TornadoCommand implements CommandExecutor {
    TenJava plugin;

    public TornadoCommand(TenJava plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tornado")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("tornadoes.reload")) {
                    plugin.reloadConfig();
                    sender.sendMessage(ChatColor.GREEN + "Successfully reloaded the config!");
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("spawn")) {
                if (sender.hasPermission("tornadoes.spawn")) {
                    if (sender instanceof Player) {
                        plugin.makeTornado((Player) sender);
                        sender.sendMessage(ChatColor.GREEN + "Spawned a tornado near you!");
                    } else {
                        sender.sendMessage(ChatColor.RED + "Console can't do that, you silly!");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
                }

                // TODO - this
            }
            return true;
        } else {
            return false;
        }
    }
}
