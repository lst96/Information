package io.github.lst96.Information.Commands;

import io.github.lst96.Information.Information;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerInfo implements CommandExecutor {
	public PlayerInfo(Information instance) {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("player")) {
			if ((sender.isOp()) || (sender.hasPermission("information.player"))){
				if(args.length != 1) {
					sender.sendMessage(ChatColor.RED + "Incorrect usage! Correct usage /player <playername>.");
					return false;
				}
	    	Player targetplayer = Bukkit.getServer().getPlayer(args[0]);
			if (targetplayer == null) {
				sender.sendMessage(ChatColor.DARK_RED + args[0] + ChatColor.RED + " is currently not online.");
				return true;
			}
			sender.sendMessage(ChatColor.GOLD + "====== Player: " + ChatColor.RED + targetplayer.getName() + ChatColor.GOLD + " ======");
			sender.sendMessage(ChatColor.GOLD + " Nick: " + ChatColor.WHITE + targetplayer.getDisplayName());
			sender.sendMessage(ChatColor.GOLD + " Health: " + ChatColor.WHITE + targetplayer.getHealth() + "/20");
			sender.sendMessage(ChatColor.GOLD + " Hunger: " + ChatColor.WHITE + targetplayer.getFoodLevel() + "/20" + " (+" + targetplayer.getSaturation() + " saturation)");
			sender.sendMessage(ChatColor.GOLD + " Exp: " + ChatColor.WHITE + targetplayer.getLevel());
			sender.sendMessage(ChatColor.GOLD + " Location: " + ChatColor.WHITE + "(" + targetplayer.getLocation().getWorld().getName() + ", " + targetplayer.getLocation().getBlockX() + ", " + targetplayer.getLocation().getBlockY() + ", " + targetplayer.getLocation().getBlockZ() + ")");
			sender.sendMessage(ChatColor.GOLD + " IP Address: " + ChatColor.WHITE + targetplayer.getAddress());
			sender.sendMessage(ChatColor.GOLD + " Gamemode: " + ChatColor.WHITE + targetplayer.getGameMode());
			sender.sendMessage(ChatColor.GOLD + " OP: " + ChatColor.GREEN + targetplayer.isOp());
			sender.sendMessage(ChatColor.GOLD + " Fly mode: " + ChatColor.GREEN + targetplayer.isFlying());
			return true;
		}
		sender.sendMessage(ChatColor.DARK_RED + "[Information]" + ChatColor.RED + " I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
		return false;
	}
		return false;
  }
}


