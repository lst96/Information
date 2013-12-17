package io.github.lst96.Information;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Elistener implements Listener {

    private Information plugin;
	
	public Elistener(Information instance) {
        plugin = instance;
        
	}
	
	 @EventHandler
	 public void onPlayerJoin(PlayerJoinEvent event){
	   if(event.getPlayer().isOp() || (event.getPlayer().hasPermission("information.extra"))) {
	 	  event.getPlayer().sendMessage(ChatColor.DARK_RED + "[Information]" + " " + ChatColor.AQUA + plugin.getConfig().getString("Extra"));
	      }
	    }
	 } 
