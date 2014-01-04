package io.github.lst96.Information.Commands;

import io.github.lst96.Information.Information;

import java.util.Iterator;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

public class Vote implements CommandExecutor {

	private Information plugin;

	public Vote(Information instance) {
		this.plugin = instance;

	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("vote"))
			;
		if ((sender.isOp()) || (sender.hasPermission("information.vote"))) {
			List<?> Vote = plugin.getConfig().getStringList("Vote");
			String Vote1;
			sender.sendMessage(ChatColor.DARK_BLUE + "--Current Voting Link(s)--");
			for (Iterator<?> iterator = Vote.iterator(); iterator.hasNext(); sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Vote1)))
				Vote1 = (String) iterator.next();
			return true;
		}
		sender.sendMessage(ChatColor.DARK_RED + "[Information]" + ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
		return true;
	}
}
