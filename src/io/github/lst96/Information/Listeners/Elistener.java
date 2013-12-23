package io.github.lst96.Information.Listeners;

import java.util.Iterator;
import java.util.List;

import io.github.lst96.Information.Information;

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
	public void onPlayerJoin(PlayerJoinEvent event) {
		if ((event.getPlayer().isOp()) || (event.getPlayer().hasPermission("information.extra"))) {
			List<?> Extra = plugin.getConfig().getStringList("Extra");
			String Extra1;
			event.getPlayer().sendMessage(ChatColor.DARK_BLUE + "--Extra(s)--");
			for (Iterator<?> iterator = Extra.iterator(); iterator.hasNext(); event.getPlayer()
					.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Extra1)))
				Extra1 = (String) iterator.next();
			return;
		}
	}
}
