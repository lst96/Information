package io.github.lst96.Information;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DelVote implements CommandExecutor {
	List<String> list = new ArrayList<String>();
	private Information plugin;
	
	public DelVote(Information instance) {
        this.plugin = instance;
	}
    
	public boolean  onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
		if(commandLabel.equalsIgnoreCase("delvote"));
		if ((sender.isOp()) || (sender.hasPermission("information.del.vote"))) {
		if (args.length < 1) return false;
		      List<String> list = plugin.getConfig().getStringList("Vote");
		      list.remove(combineSplit(args));
		      plugin.getConfig().set("Vote", list);
		      this.plugin.saveConfig();
		      sender.sendMessage(ChatColor.DARK_GREEN + "Deleted Message!");
		      return true;
		    }
		    return false;
		  }

		  public String combineSplit(String[] string) {
		    StringBuilder builder = new StringBuilder();

		    for (int i = 0; i < string.length; i++) {
		      builder.append(string[i]);
		      builder.append(" ");
		    }

		    builder.deleteCharAt(builder.length() - " ".length());
		    return builder.toString();
		  }
		}