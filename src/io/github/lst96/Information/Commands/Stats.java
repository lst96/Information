package io.github.lst96.Information.Commands;

import io.github.lst96.Information.Information;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Stats implements CommandExecutor {

	public Stats(Information instance) {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("stats"));
		if ((sender.isOp()) || (sender.hasPermission("information.stats"))) {
			sender.sendMessage(ChatColor.GOLD + "[Online Mode]: " + ChatColor.GREEN + Bukkit.getOnlineMode());
			sender.sendMessage(ChatColor.GOLD + "[Flight Allowed]: " + ChatColor.GREEN + Bukkit.getAllowFlight());
			sender.sendMessage(ChatColor.GOLD + "[Nether Allowed]: " +  ChatColor.GREEN + Bukkit.getAllowNether());
			sender.sendMessage(ChatColor.GOLD + "[End Allowed]: " +  ChatColor.GREEN + Bukkit.getAllowEnd());
			sender.sendMessage(ChatColor.GOLD + "[View Distance]: " +  ChatColor.WHITE + Bukkit.getViewDistance());
			sender.sendMessage(ChatColor.GOLD + "[Default GameMode]: " +  ChatColor.WHITE + Bukkit.getDefaultGameMode());
			sender.sendMessage(ChatColor.GOLD + "[Spawn Radius]: " +  ChatColor.WHITE + Bukkit.getSpawnRadius());
			sender.sendMessage(ChatColor.GOLD + "[Animal Spawn Limit]: " +  ChatColor.WHITE + Bukkit.getAnimalSpawnLimit());
			sender.sendMessage(ChatColor.GOLD + "[Monster Spawn Limit]: " +  ChatColor.WHITE + Bukkit.getMonsterSpawnLimit());
			sender.sendMessage(ChatColor.GOLD + "[Ambient Spawn Limit]: " +  ChatColor.WHITE + Bukkit.getAmbientSpawnLimit());
			sender.sendMessage(ChatColor.GOLD + "[Ticks Per Animal Spawn]: " +  ChatColor.WHITE + Bukkit.getTicksPerAnimalSpawns());
			sender.sendMessage(ChatColor.GOLD + "[Ticks Per Monster Spawn]: " +  ChatColor.WHITE + Bukkit.getTicksPerMonsterSpawns());
			sender.sendMessage(ChatColor.GOLD + "[Ops]: " +  ChatColor.WHITE + Bukkit.getOperators());
			sender.sendMessage(ChatColor.GOLD + "[CraftBukkit Version]: " +  ChatColor.WHITE + Bukkit.getBukkitVersion());
			sender.sendMessage(ChatColor.GOLD + "[Information Version]: " +  ChatColor.WHITE + Bukkit.getServer().getPluginManager().getPlugin("Information").getDescription().getVersion());
			return true;
		}
		sender.sendMessage(ChatColor.DARK_RED + "[Information]" + ChatColor.RED + " I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
		return true;
	}
}