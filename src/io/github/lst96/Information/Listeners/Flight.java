package io.github.lst96.Information.Listeners;

import io.github.lst96.Information.Information;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Flight
  implements Listener
{
  private Information plugin;
  
  public Flight(Information instance)
  {
    this.plugin = instance;
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerChangeWorld(PlayerMoveEvent event)
  {
    if ((this.plugin.getConfig().getBoolean("Disabled Flight Worlds.world_the_end")) && 
      ((!event.getPlayer().isOp()) || (!event.getPlayer().hasPermission("information.flightbypass.end"))) && 
      (event.getPlayer().getWorld().getName().endsWith("end")) && (event.getPlayer().isFlying()))
    {
      event.getPlayer().setAllowFlight(false);
      event.getPlayer().setFlying(false);
      event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to fly in this world!");
    }
    if ((this.plugin.getConfig().getBoolean("Disabled Flight Worlds.world_nether")) && 
      ((!event.getPlayer().isOp()) || (!event.getPlayer().hasPermission("information.flightbypass.nether"))) && 
      (event.getPlayer().getWorld().getName().endsWith("nether")) && (event.getPlayer().isFlying()))
    {
      event.getPlayer().setAllowFlight(false);
      event.getPlayer().setFlying(false);
      event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to fly in this world!");
    }
    if ((this.plugin.getConfig().getBoolean("Disabled Flight Worlds.world")) && 
      ((!event.getPlayer().isOp()) || (!event.getPlayer().hasPermission("information.flightbypass.world"))) && 
      (event.getPlayer().getWorld().getName().endsWith("world")) && (event.getPlayer().isFlying()))
    {
      event.getPlayer().setAllowFlight(false);
      event.getPlayer().setFlying(false);
      event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to fly in this world!");
    }
  }
}
