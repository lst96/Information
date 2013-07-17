package io.github.lst96.Information;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ViewVote implements CommandExecutor {
	private Information plugin;
	
	public ViewVote(Information instance) {
        this.plugin = instance;
	}
    
	public boolean  onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
		if(commandLabel.equalsIgnoreCase("viewvote"));
		if ((sender.isOp()) || (sender.hasPermission("information.view.vote"))) {
			 sender.sendMessage(ChatColor.GRAY + "--Current Links--");
		      for (String msg : plugin.getConfig().getStringList("Vote")) {
		        sender.sendMessage(msg);
		      }
		      return true;
		    }
		    return false;
		  }
		}