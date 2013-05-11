package io.github.lst96.Information;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class Commands implements CommandExecutor{
	
	private Information plugin; // pointer to your main class, unrequired if you don't need methods from the main class
	 
    public Commands(Information instance) {
        this.plugin = instance;
    }
	
public boolean  onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	   {
	if(commandLabel.equalsIgnoreCase("website")){
		Player player = (Player) sender;
	if(player.hasPermission("information.website")) {
		   player.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.DARK_GREEN + plugin.getConfig().getString("Website"));
		}else{
		   player.sendMessage(ChatColor.DARK_RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
		}
	}

  if(commandLabel.equalsIgnoreCase("donate")){
	  Player player = (Player) sender;
	  player.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.DARK_BLUE + plugin.getConfig().getString("Donate"));
  }

  if(commandLabel.equalsIgnoreCase("vote")){
	  Player player = (Player) sender;
	  player.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.DARK_AQUA + plugin.getConfig().getString("VoteLink1"));
  }

  if(commandLabel.equalsIgnoreCase("vote")){
	  Player player = (Player) sender;
	  player.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.DARK_PURPLE + plugin.getConfig().getString("VoteLink2"));
  }

  if(commandLabel.equalsIgnoreCase("vote")){
	  Player player = (Player) sender;
	  player.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.GOLD + plugin.getConfig().getString("VoteLink3"));
  }

  if(commandLabel.equalsIgnoreCase("vote")){
	  Player player = (Player) sender;
	  player.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.GREEN + plugin.getConfig().getString("VoteLink4"));
  }
  
  if(commandLabel.equalsIgnoreCase("staff")){
	  Player player = (Player) sender;
	  player.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.YELLOW + plugin.getConfig().getString("Staff"));
  }

  if(commandLabel.equalsIgnoreCase("rules")){
	  Player player = (Player) sender;
	  player.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.AQUA + plugin.getConfig().getString("Rules"));
  }   
  
  if (commandLabel.equalsIgnoreCase("inforeload")) {
	  Player player = (Player) sender;
      plugin.reloadConfig();
      player.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.RED + " Configuration Reloaded!");
    
  return true;
}
return false;
	   }
}

  
  
  

	   

	   