
package io.github.lst96.Information;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Online implements CommandExecutor{
	
	private Information plugin; // pointer to your main class, unrequired if you don't need methods from the main class
	 
    public Online(Information instance) {
        this.plugin = instance;
        
    }
    
    public boolean  onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
    	
    	if(commandLabel.equalsIgnoreCase("online")){
    		if ((sender.isOp()) || (sender.hasPermission("information.online"))) {
    		   sender.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.GREEN + Bukkit.getOnlinePlayers().length + " " + ChatColor.YELLOW + "of" + " " + ChatColor.GREEN + Bukkit.getMaxPlayers() + " " + ChatColor.YELLOW + "Players Online");
    	    return true;
    		}
    		sender.sendMessage(ChatColor.DARK_RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
    		return true;
    	}
    	return false;
    }
}
  