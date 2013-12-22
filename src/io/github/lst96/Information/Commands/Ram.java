package io.github.lst96.Information.Commands;

import io.github.lst96.Information.Information;
import io.github.lst96.Information.Runnables.Tps;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Ram implements CommandExecutor {
	public final long serverStart = System.currentTimeMillis();

	public Ram(Information instance) {

	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		double tps = Tps.getTPS();
		ChatColor color;
		if (tps >= 18.0F) {
			color = ChatColor.GREEN;
		} else {
			if (tps >= 15.0F) {
				color = ChatColor.YELLOW;
			} else {
				color = ChatColor.RED;
			}
		}
		Runtime runtime = Runtime.getRuntime();
		System.gc();
		if ((sender.isOp()) || (sender.hasPermission("information.ram"))) {
			final long diff = System.currentTimeMillis() - serverStart;
			sender.sendMessage(ChatColor.GOLD + "[Uptime]: " + ChatColor.RESET
					+ ChatColor.RED + (int) (diff / 604800000) + "w "
					+ (int) (diff / 86400000 % 7) + "d "
					+ (int) (diff / 3600000 % 24) + "h "
					+ (int) (diff / 60000 % 60) + "m "
					+ (int) (diff / 1000 % 60) + "s");
			sender.sendMessage(ChatColor.GOLD + "[Tps]: " + ChatColor.RESET
					+ color + Tps.getTPS());
			sender.sendMessage(ChatColor.GOLD + "[Maximum Ram]: "
					+ ChatColor.RESET + ChatColor.RED + runtime.maxMemory()
					/ 1024L / 1024L + " MB");
			sender.sendMessage(ChatColor.GOLD + "[Allocated Ram]: "
					+ ChatColor.RESET + ChatColor.RED + runtime.totalMemory()
					/ 1024L / 1024L + " MB");
			sender.sendMessage(ChatColor.GOLD + "[Used Ram]: "
					+ ChatColor.RESET + ChatColor.RED
					+ (runtime.totalMemory() - runtime.freeMemory()) / 1024L
					/ 1024L + " MB");
			sender.sendMessage(ChatColor.GOLD + "[Free Ram]: "
					+ ChatColor.RESET + ChatColor.RED + runtime.freeMemory()
					/ 1024L / 1024L + " MB");
			sender.sendMessage(ChatColor.GOLD + "[CraftBukkit Version]: "
					+ ChatColor.RESET + ChatColor.RED
					+ Bukkit.getBukkitVersion());
			sender.sendMessage(ChatColor.GOLD
					+ "[Information Version]: "
					+ ChatColor.RESET
					+ ChatColor.RED
					+ Bukkit.getServer().getPluginManager()
							.getPlugin("Information").getDescription()
							.getVersion());
			return true;
		}

		sender.sendMessage(ChatColor.DARK_RED
				+ "[Information]"
				+ ChatColor.RED
				+ " I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
		return true;
	}
}
