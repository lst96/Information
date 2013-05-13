package io.github.lst96.Information;

import java.net.InetSocketAddress;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerInfo
  implements CommandExecutor
{
  public Information plugin;

  public PlayerInfo(Information instance)
  {
    this.plugin = instance;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    Player targetplayer = Bukkit.getServer().getPlayer(args[0]);
    if ((commandLabel.equalsIgnoreCase("player")) || (sender.hasPermission("information.player"))) {
      if (targetplayer == null) {
        sender.sendMessage(ChatColor.YELLOW + args[0] + ChatColor.GOLD + " is not online!");
        return true;
      }
      int health = targetplayer.getPlayer().getHealth();
      long firstPlayed = targetplayer.getPlayer().getFirstPlayed();
      int foodLevel = targetplayer.getPlayer().getFoodLevel();
      float exp = targetplayer.getPlayer().getLevel();
      InetSocketAddress playerip = targetplayer.getAddress();
      GameMode gamemode = targetplayer.getPlayer().getGameMode();
      String playername = targetplayer.getName();
      boolean flyCheck = targetplayer.isFlying();
      boolean opCheck = targetplayer.isOp();
      long timeonserver = Bukkit.getServer().getPlayer(args[0]).getPlayer().getPlayerTime();
      Location location = targetplayer.getLocation();
      int fireCheck = targetplayer.getFireTicks();
      sender.sendMessage(ChatColor.GOLD + playername + " health is at: " + health + "/20");
      sender.sendMessage(ChatColor.GOLD + playername + "'s Location: " + "X: " + location.getBlockX() + ", Y: " + location.getBlockY() + ", Z: " + location.getBlockZ());
      sender.sendMessage(ChatColor.GOLD + playername + " is in world: " + location.getWorld().getName());
      sender.sendMessage(ChatColor.GOLD + playername + " First Played: " + firstPlayed);
      sender.sendMessage(ChatColor.GOLD + playername + "'s food level is at: " + foodLevel + "/20");
      sender.sendMessage(ChatColor.GOLD + playername + " Gamemode is: " + gamemode);
      sender.sendMessage(ChatColor.GOLD + playername + " exp level is " + exp);
      sender.sendMessage(ChatColor.GOLD + playername + "'s ip address is " + playerip);
      sender.sendMessage(ChatColor.GOLD + playername + " is flying: " + flyCheck);
      sender.sendMessage(ChatColor.GOLD + playername + " is op: " + opCheck);
      sender.sendMessage(ChatColor.GOLD + playername + " is on fire: " + fireCheck);
      sender.sendMessage(ChatColor.GOLD + playername + " Time On Server: " + timeonserver);
    }

    return false;
  }
}