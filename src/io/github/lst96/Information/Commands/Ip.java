package io.github.lst96.Information.Commands;

import io.github.lst96.Information.Information;

import java.util.Iterator;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

public class Ip implements CommandExecutor {

	private Information plugin;

	public Ip(Information instance) {
		this.plugin = instance;

	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("ip"));
		if ((sender.isOp()) || (sender.hasPermission("information.ip"))) {
			List<?> ServerIp = plugin.getConfig().getStringList("ServerIp");
			String ServerIp1;
			sender.sendMessage(ChatColor.DARK_BLUE + "--Server Ip(s)--");
			for (Iterator<?> iterator = ServerIp.iterator(); iterator.hasNext(); sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ServerIp1)))
				ServerIp1 = (String) iterator.next();
			return true;
		}
		sender.sendMessage(ChatColor.DARK_RED + "[Information]" + ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
		return true;
	}
}
