package io.github.lst96.Information;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listeners implements Listener {
	
	Information plugin;
	
	public Listeners(Information instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
	  if(event.getPlayer().isOp() && plugin.isUpdate)
	  {
		  event.getPlayer().sendMessage(ChatColor.DARK_RED + "[Information]" + ChatColor.RESET + ChatColor.RED + " An update is available: " + plugin.updater.getLatestVersionString());
		  event.getPlayer().sendMessage(ChatColor.DARK_RED + "[Information]" + ChatColor.RESET + ChatColor.RED + " Download update at http://dev.bukkit.org/server-mods/information/files/10-information-v0-9/");
	  }
	}

}