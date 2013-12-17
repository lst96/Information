package io.github.lst96.Information;

import java.util.Iterator;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

public class Youtube implements CommandExecutor{
	
	private Information plugin;
	 
    public Youtube(Information instance) {
        this.plugin = instance;
        
    }
    
    public boolean  onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        	if(commandLabel.equalsIgnoreCase("youtube"));
        		if ((sender.isOp()) || (sender.hasPermission("information.youtube"))) {
        		List<?> Youtube = plugin.getConfig().getStringList("Youtube");
        		String Youtube1;
        		sender.sendMessage(ChatColor.DARK_BLUE + "--Youtube Link(s)--");
        		for (Iterator<?> iterator = Youtube.iterator(); iterator.hasNext(); sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Youtube1)))
        			Youtube1 = (String)iterator.next();
        				return true;
        		}
        		sender.sendMessage(ChatColor.DARK_RED + "[Information]" + ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
        		return true;
        }
    }