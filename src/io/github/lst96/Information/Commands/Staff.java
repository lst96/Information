package io.github.lst96.Information.Commands;

import io.github.lst96.Information.Information;

import java.util.Iterator;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

public class Staff implements CommandExecutor {

	private Information plugin;

	public Staff(Information instance) {
		this.plugin = instance;

	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("staff"))
			;
		if ((sender.isOp()) || (sender.hasPermission("information.staff"))) {
			List<?> Staff = plugin.getConfig().getStringList("Staff");
			String Staff1;
			sender.sendMessage(ChatColor.DARK_BLUE + "--Current Staff(s)--");
			for (Iterator<?> iterator = Staff.iterator(); iterator.hasNext(); sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Staff1)))
				Staff1 = (String) iterator.next();
			return true;
		}
		sender.sendMessage(ChatColor.DARK_RED + "[Information]" + ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
		return true;
	}
}
