package io.github.lst96.Information.Listeners;

import io.github.lst96.Information.Information;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Creative
  implements Listener
{
  private Information plugin;
  
  public Creative(Information instance)
  {
    this.plugin = instance;
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerChangeWorld(PlayerMoveEvent event)
  {
    if ((this.plugin.getConfig().getBoolean("Disabled Creative Worlds.world_the_end")) && 
      ((!event.getPlayer().isOp()) || (!event.getPlayer().hasPermission("information.creativebypass.end"))) && 
      (event.getPlayer().getWorld().getName().endsWith("end")) && (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)))
    {
      event.getPlayer().setGameMode(GameMode.SURVIVAL);
      event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to use creative in this world!");
    }
    if ((this.plugin.getConfig().getBoolean("Disabled Creative Worlds.world_nether")) && 
      ((!event.getPlayer().isOp()) || (!event.getPlayer().hasPermission("information.creativebypass.nether"))) && 
      (event.getPlayer().getWorld().getName().endsWith("nether")) && (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)))
    {
      event.getPlayer().setGameMode(GameMode.SURVIVAL);
      event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to use creative in this world!");
    }
    if ((this.plugin.getConfig().getBoolean("Disabled Creative Worlds.world")) && 
      ((!event.getPlayer().isOp()) || (!event.getPlayer().hasPermission("information.creativebypass.world"))) && 
      (event.getPlayer().getWorld().getName().endsWith("world")) && (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)))
    {
      event.getPlayer().setGameMode(GameMode.SURVIVAL);
      event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to use creative in this world!");
    }
  }
}
