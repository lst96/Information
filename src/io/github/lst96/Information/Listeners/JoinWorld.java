package io.github.lst96.Information.Listeners;

import io.github.lst96.Information.Information;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinWorld
  implements Listener
{
  private Information plugin;
  
  public JoinWorld(Information instance)
  {
    this.plugin = instance;
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerChangeWorld(PlayerChangedWorldEvent event)
  {
    if ((this.plugin.getConfig().getBoolean("Disabled Join Worlds.world_the_end")) && 
      ((!event.getPlayer().isOp()) || (!event.getPlayer().hasPermission("information.joinbypass.end"))) && 
      (event.getPlayer().getWorld().getName().endsWith("end"))) {
      if (!this.plugin.getConfig().getBoolean("Disabled Join Worlds.world"))
      {
        World world = Bukkit.getWorld("world");
        Location loc = world.getSpawnLocation();
        event.getPlayer().teleport(loc);
        event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to join the end!");
      }
      else if (!this.plugin.getConfig().getBoolean("Disabled Join Worlds.world_nether"))
      {
        World nether = Bukkit.getWorld("world_nether");
        Location loc = nether.getSpawnLocation();
        event.getPlayer().teleport(loc);
        event.getPlayer().sendMessage("World disabled, teleporting to world_nether instead");
      }
      else
      {
        event.getPlayer().sendMessage(ChatColor.RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
      }
    }
    if ((this.plugin.getConfig().getBoolean("Disabled Join Worlds.world_nether")) && 
      ((!event.getPlayer().isOp()) || (!event.getPlayer().hasPermission("information.joinbypass.nether"))) && 
      (event.getPlayer().getWorld().getName().endsWith("nether"))) {
      if (!this.plugin.getConfig().getBoolean("Disabled Join Worlds.world"))
      {
        World world = Bukkit.getWorld("world");
        Location loc = world.getSpawnLocation();
        event.getPlayer().teleport(loc);
        event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to join the nether!");
      }
      else if (!this.plugin.getConfig().getBoolean("Disabled Join Worlds.world_the_end"))
      {
        World end = Bukkit.getWorld("world_the_end");
        Location loc = end.getSpawnLocation();
        event.getPlayer().teleport(loc);
        event.getPlayer().sendMessage("World disabled, teleporting to world_end instead");
      }
      else
      {
        event.getPlayer().sendMessage(ChatColor.RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
      }
    }
    if ((this.plugin.getConfig().getBoolean("Disabled Join Worlds.world")) && 
      ((!event.getPlayer().isOp()) || (!event.getPlayer().hasPermission("information.joinbypass.world"))) && 
      (event.getPlayer().getWorld().getName().endsWith("world"))) {
      if (!this.plugin.getConfig().getBoolean("Disabled Join Worlds.world_nether"))
      {
        World nether = Bukkit.getWorld("world_nether");
        Location loc = nether.getSpawnLocation();
        event.getPlayer().teleport(loc);
        event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to join the world!");
      }
      else if (!this.plugin.getConfig().getBoolean("Disabled Join Worlds.world_the_end"))
      {
        World end = Bukkit.getWorld("world_the_end");
        Location loc = end.getSpawnLocation();
        event.getPlayer().teleport(loc);
        event.getPlayer().sendMessage("World disabled, teleporting to world_the_end instead");
      }
      else
      {
        event.getPlayer().sendMessage(ChatColor.RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
      }
    }
    if ((this.plugin.getConfig().getBoolean("Disabled Join Worlds.world")) && (this.plugin.getConfig().getBoolean("Disabled Join Worlds.world_the_nether")) && (this.plugin.getConfig().getBoolean("Disabled Join Worlds.world_the_end")))
    {
      event.getPlayer().kickPlayer("Config Error, all worlds are disabled, please undisable at least 1 world.");
      this.plugin.logger.info(ChatColor.DARK_RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
    }
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerChangeWorld(PlayerJoinEvent event)
  {
    if ((this.plugin.getConfig().getBoolean("Disabled Join Worlds.world_the_end")) && 
      ((!event.getPlayer().isOp()) || (!event.getPlayer().hasPermission("information.joinbypass.end"))) && 
      (event.getPlayer().getWorld().getName().endsWith("end"))) {
      if (!this.plugin.getConfig().getBoolean("Disabled Join Worlds.world"))
      {
        World world = Bukkit.getWorld("world");
        Location loc = world.getSpawnLocation();
        event.getPlayer().teleport(loc);
        event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to join the end!");
      }
      else if (!this.plugin.getConfig().getBoolean("Disabled Join Worlds.world_nether"))
      {
        World nether = Bukkit.getWorld("world_nether");
        Location loc = nether.getSpawnLocation();
        event.getPlayer().teleport(loc);
        event.getPlayer().sendMessage("World disabled, teleporting to world_nether instead");
      }
      else
      {
        event.getPlayer().sendMessage(ChatColor.RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
      }
    }
    if ((this.plugin.getConfig().getBoolean("Disabled Join Worlds.world_nether")) && 
      ((!event.getPlayer().isOp()) || (!event.getPlayer().hasPermission("information.joinbypass.nether"))) && 
      (event.getPlayer().getWorld().getName().endsWith("nether"))) {
      if (!this.plugin.getConfig().getBoolean("Disabled Join Worlds.world"))
      {
        World world = Bukkit.getWorld("world");
        Location loc = world.getSpawnLocation();
        event.getPlayer().teleport(loc);
        event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to join the nether!");
      }
      else if (!this.plugin.getConfig().getBoolean("Disabled Join Worlds.world_the_end"))
      {
        World end = Bukkit.getWorld("world_the_end");
        Location loc = end.getSpawnLocation();
        event.getPlayer().teleport(loc);
        event.getPlayer().sendMessage("World disabled, teleporting to world_end instead");
      }
      else
      {
        event.getPlayer().sendMessage(ChatColor.RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
      }
    }
    if ((this.plugin.getConfig().getBoolean("Disabled Join Worlds.world")) && 
      ((!event.getPlayer().isOp()) || (!event.getPlayer().hasPermission("information.joinbypass.world"))) && 
      (event.getPlayer().getWorld().getName().endsWith("world"))) {
      if (!this.plugin.getConfig().getBoolean("Disabled Join Worlds.world_nether"))
      {
        World nether = Bukkit.getWorld("world_nether");
        Location loc = nether.getSpawnLocation();
        event.getPlayer().teleport(loc);
        event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to join the world!");
      }
      else if (!this.plugin.getConfig().getBoolean("Disabled Join Worlds.world_the_end"))
      {
        World end = Bukkit.getWorld("world_the_end");
        Location loc = end.getSpawnLocation();
        event.getPlayer().teleport(loc);
        event.getPlayer().sendMessage("World disabled, teleporting to world_the_end instead");
      }
      else
      {
        event.getPlayer().sendMessage(ChatColor.RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
      }
    }
    if ((this.plugin.getConfig().getBoolean("Disabled Join Worlds.world")) && (this.plugin.getConfig().getBoolean("Disabled Join Worlds.world_the_nether")) && (this.plugin.getConfig().getBoolean("Disabled Join Worlds.world_the_end")))
    {
      event.getPlayer().kickPlayer("Config Error, all worlds are disabled, please undisable at least 1 world.");
      this.plugin.logger.info(ChatColor.DARK_RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
      Bukkit.broadcastMessage(ChatColor.DARK_RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
    }
  }
}
