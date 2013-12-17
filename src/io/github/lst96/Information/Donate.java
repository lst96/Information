package io.github.lst96.Information;

import java.util.Iterator;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

public class Donate implements CommandExecutor{
	
	private Information plugin;
	 
    public Donate(Information instance) {
        this.plugin = instance;
        
    }
    
    public boolean  onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        	if(commandLabel.equalsIgnoreCase("donate"));
        		if ((sender.isOp()) || (sender.hasPermission("information.donate"))) {
        		List<?> Donate = plugin.getConfig().getStringList("Donate");
        		String Donate1;
        		sender.sendMessage(ChatColor.DARK_BLUE + "--Donation Link(s)--");
        		for (Iterator<?> iterator = Donate.iterator(); iterator.hasNext(); sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Donate1)))
                    Donate1 = (String)iterator.next();
        				return true;
        		}
        		sender.sendMessage(ChatColor.DARK_RED + "[Information]" + ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
        		return true;
        }
    }
