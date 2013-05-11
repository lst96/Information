package io.github.lst96.Information;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class Vote implements CommandExecutor{
	
	private Information plugin; // pointer to your main class, unrequired if you don't need methods from the main class
	 
    public Vote(Information instance) {
        this.plugin = instance;
        
    }
    
    public boolean  onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {

    	if(commandLabel.equalsIgnoreCase("vote")){
    		Player player = (Player) sender;
    	if(player.hasPermission("information.vote")) {
    		   player.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.DARK_AQUA + plugin.getConfig().getString("VoteLink1"));
    		}else{
    		   player.sendMessage(ChatColor.DARK_RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
    		}
    	}

    	if(commandLabel.equalsIgnoreCase("vote")){
    		Player player = (Player) sender;
    	if(player.hasPermission("information.vote")) {
    		   player.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.DARK_PURPLE + plugin.getConfig().getString("VoteLink2"));
    		}else{
    		   player.sendMessage(ChatColor.DARK_RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
    		}
    	}

    	if(commandLabel.equalsIgnoreCase("vote")){
    		Player player = (Player) sender;
    	if(player.hasPermission("information.vote")) {
    		   player.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.GOLD + plugin.getConfig().getString("VoteLink3"));
    		}else{
    		   player.sendMessage(ChatColor.DARK_RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
    		}
    	}
    	
    	if(commandLabel.equalsIgnoreCase("vote")){
    		Player player = (Player) sender;
    	if(player.hasPermission("information.vote")) {
    		   player.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.GREEN + plugin.getConfig().getString("VoteLink4"));
    		}else{
    		   player.sendMessage(ChatColor.DARK_RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
    		}
    	return true;
    	}
    	return false;
    }
}