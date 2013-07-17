package io.github.lst96.Information;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetVote implements CommandExecutor {
	List<String> list = new ArrayList();
	private Information plugin;
	
	public SetVote(Information instance) {
        this.plugin = instance;
	}
    
	public boolean  onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
		if(commandLabel.equalsIgnoreCase("setvote"));
		if ((sender.isOp()) || (sender.hasPermission("information.set.vote"))) {
		if (args.length < 1) return false;
		      List list = plugin.getConfig().getStringList("Vote");
		      list.add(combineSplit(args));
		      plugin.getConfig().set("Vote", list);
		      this.plugin.saveConfig();
		      sender.sendMessage(ChatColor.DARK_GREEN + "Saved Message!");
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