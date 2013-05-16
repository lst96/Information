package io.github.lst96.Information;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

public class Rules implements CommandExecutor{
	
	private Information plugin; // pointer to your main class, unrequired if you don't need methods from the main class
	 
    public Rules(Information instance) {
        this.plugin = instance;
        
    }
    
    public boolean  onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
    	if(commandLabel.equalsIgnoreCase("rules")){
    		if ((sender.isOp()) || (sender.hasPermission("information.rules"))) {
    		   sender.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.AQUA + plugin.getConfig().getString("Rules"));
    	    return true;
    		}
    		sender.sendMessage(ChatColor.DARK_RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
    		return true;
    	}
    	return false;
      }
    }