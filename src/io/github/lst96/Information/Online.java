
package io.github.lst96.Information;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Online implements CommandExecutor{
	  
    public Online(Information instance) {
        
    }
    
    public boolean  onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
    	
    	if(commandLabel.equalsIgnoreCase("online"));
    		if ((sender.isOp()) || (sender.hasPermission("information.online"))) {
    		   sender.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.GREEN + Bukkit.getOnlinePlayers().length + ChatColor.RESET + ChatColor.YELLOW + " of " + ChatColor.RESET + ChatColor.GREEN + Bukkit.getMaxPlayers());
    	    return true;
    		}
    		sender.sendMessage(ChatColor.DARK_RED + "[Information]" + ChatColor.RED + " I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
    		return true;
     }
}
  