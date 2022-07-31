package de.themoep.serverstatus;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Command;

import java.util.Map;

/*
 * ServerStatus
 * Copyright (C) 2021 Max Lee aka Phoenix616 (max@themoep.de)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

public class ServerStatusCommand extends Command {
    private final ServerStatus plugin;

    public ServerStatusCommand(ServerStatus plugin, String name, String permission, String... aliases) {
        super(name, permission, aliases);
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.hasPermission(getPermission())) {
            if(args.length > 0) {
                if("reload".equalsIgnoreCase(args[0])) {
                    if(plugin.reloadConfig()) {
                        sender.sendMessage(ChatColor.YELLOW + plugin.getDescription().getName() + ChatColor.GREEN + " reload!");
                    } else {
                        sender.sendMessage(ChatColor.YELLOW + plugin.getDescription().getName() + ChatColor.RED + " failed to reload!");
                    }
                    return;
                } else if("refresh".equalsIgnoreCase(args[0])) {
                    if(plugin.isEnabled()) {
                        plugin.getChecker().refreshStatusMap(plugin.getProxy().getServers().values());
                        sender.sendMessage(ChatColor.YELLOW + "Refreshed server status map!");
                    } else {
                        sender.sendMessage(ChatColor.YELLOW + plugin.getDescription().getName() + ChatColor.RED + " did not properly enable!");
                    }
                    return;
                } else if(args.length > 1) {
                    boolean status = "setonline".equalsIgnoreCase(args[0]);
                    if(status || "setoffline".equalsIgnoreCase(args[0])) {
                        ServerInfo server = plugin.getProxy().getServerInfo(args[1]);
                        if(server == null) {
                            sender.sendMessage(ChatColor.YELLOW + args[1] + ChatColor.RED + " is not a server on this BungeeCord!");
                            return;
                        }
                        plugin.getChecker().setManualStatus(server, status);
                        sender.sendMessage(ChatColor.YELLOW + "Manually set status of " + server.getName() + " to " + (status ? "online" : "offline"));
                        return;
                    }
                }
                sender.sendMessage(ChatColor.RED + "Usage: /serverstatus [reload|refresh|[setonline|setoffline] <server>]");
            } else if(plugin.isEnabled()) {
                sender.sendMessage(ChatColor.YELLOW + plugin.getDescription().getName() + " v" + plugin.getDescription().getVersion());
                for(Map.Entry<String, Boolean> entry : plugin.getChecker().getStatusMap().entrySet()) {
                    String msg = " " + ChatColor.YELLOW + entry.getKey() + ChatColor.WHITE + ": ";
                    if(entry.getValue()) {
                        msg += ChatColor.GREEN + "online";
                    } else {
                        msg += ChatColor.RED + "offline";
                    }
                    if(plugin.getChecker().isManuallySet(entry.getKey())) {
                        msg += ChatColor.YELLOW + " (manual)";
                    }
                    sender.sendMessage(msg);
                }
            } else {
                sender.sendMessage(ChatColor.YELLOW + plugin.getDescription().getName() + ChatColor.RED + " did not properly enable!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You don't have the permission to execute this command!");
        }
    }
}
