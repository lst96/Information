package io.github.lst96.Information;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

public class Vote implements CommandExecutor{
	
	private Information plugin; // pointer to your main class, unrequired if you don't need methods from the main class
	 
    public Vote(Information instance) {
        this.plugin = instance;
        
    }
    
    public boolean  onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
    	if(commandLabel.equalsIgnoreCase("vote")){
    		if ((sender.isOp()) || (sender.hasPermission("information.vote"))) {
    		   sender.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.AQUA + plugin.getConfig().getString("VoteLink1"));
    		   sender.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.DARK_PURPLE + plugin.getConfig().getString("VoteLink2"));
    		   sender.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.GOLD + plugin.getConfig().getString("VoteLink3"));
    		   sender.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.GREEN + plugin.getConfig().getString("VoteLink4"));
    		   return true;
    		}
    		sender.sendMessage(ChatColor.DARK_RED + "[Information]" + ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
    		return true;
    	}
    	return false;
      }
    }
    		   
    	    