package io.github.lst96.Information;

import java.net.InetSocketAddress;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.GameMode;
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
    if(commandLabel.equalsIgnoreCase("player")){
		if ((sender.isOp()) || (sender.hasPermission("information.player"))) {
      if (targetplayer == null) {
        sender.sendMessage(ChatColor.YELLOW + args[0] + ChatColor.GOLD + " is not online!");
        return true;
      }
      int health = (int) targetplayer.getPlayer().getHealth();
      int foodLevel = targetplayer.getPlayer().getFoodLevel();
      float exp = targetplayer.getPlayer().getLevel();
      InetSocketAddress playerip = targetplayer.getAddress();
      GameMode gamemode = targetplayer.getPlayer().getGameMode();
      String playername = targetplayer.getName();
      boolean flyCheck = targetplayer.isFlying();
      boolean opCheck = targetplayer.isOp();
      Location location = targetplayer.getLocation();
      sender.sendMessage(ChatColor.GOLD +"====== Player: " + ChatColor.RED + playername + ChatColor.GOLD + "======");
      sender.sendMessage(ChatColor.GOLD + " Health: " + ChatColor.WHITE + health + "/20");
      sender.sendMessage(ChatColor.GOLD + " Location: " + ChatColor.WHITE + "(" + location.getWorld().getName() + ", " + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ() + ")");
      sender.sendMessage(ChatColor.GOLD + " Food: " + ChatColor.WHITE + foodLevel + "/20");
      sender.sendMessage(ChatColor.GOLD + " Gamemode: " + ChatColor.WHITE + gamemode);
      sender.sendMessage(ChatColor.GOLD + " Exp: " + ChatColor.WHITE + exp);
      sender.sendMessage(ChatColor.GOLD + " IP Address: " + ChatColor.WHITE + playerip);
      sender.sendMessage(ChatColor.GOLD + " Fly mode: " + ChatColor.GREEN + flyCheck);
      sender.sendMessage(ChatColor.GOLD + " OP: " + ChatColor.GREEN + opCheck);
      return true;
		}
		sender.sendMessage(ChatColor.DARK_RED + "[Information]" + ChatColor.RED + " I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
        return true;
    }
  return false;
  }
}
     