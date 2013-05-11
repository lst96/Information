package io.github.lst96.Information;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class Donate implements CommandExecutor{
	
	private Information plugin; // pointer to your main class, unrequired if you don't need methods from the main class
	 
    public Donate(Information instance) {
        this.plugin = instance;
        
    }
    
    public boolean  onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
	if(commandLabel.equalsIgnoreCase("donate")){
		Player player = (Player) sender;
	if(player.hasPermission("information.donate")) {
		   player.sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.DARK_BLUE + plugin.getConfig().getString("Donate"));
		}else{
		   player.sendMessage(ChatColor.DARK_RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
		}
	return true;
	}
	return false;
    }
}