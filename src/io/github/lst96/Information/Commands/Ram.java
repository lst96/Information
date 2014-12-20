package io.github.lst96.Information.Commands;

import io.github.lst96.Information.Information;
import io.github.lst96.Information.Runnables.Tps;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Ram
  implements CommandExecutor
{
  public final long serverStart = System.currentTimeMillis();
  
  public Ram(Information instance) {}
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    double tps = Tps.getTPS();
    ChatColor color;
    if (tps >= 18.0D)
    {
      color = ChatColor.GREEN;
    }
    else
    {
      if (tps >= 15.0D) {
        color = ChatColor.YELLOW;
      } else {
        color = ChatColor.RED;
      }
    }
    Runtime runtime = Runtime.getRuntime();
    System.gc();
    if ((sender.isOp()) || (sender.hasPermission("information.ram")))
    {
      long diff = System.currentTimeMillis() - this.serverStart;
      sender.sendMessage(ChatColor.GOLD + "[Uptime]: " + ChatColor.WHITE + (int)(diff / 604800000L) + "w " + (int)(diff / 86400000L % 7L) + "d " + (int)(diff / 3600000L % 24L) + "h " + (int)(diff / 60000L % 60L) + "m " + (int)(diff / 1000L % 60L) + "s");
      sender.sendMessage(ChatColor.GOLD + "[Tps]: " + color + Math.round(Tps.getTPS() / 100.0D * 100.0D) + " " + Math.round(Tps.getTPS() / 20.0D * 100.0D / 100.0D * 100.0D) + "%");
      sender.sendMessage(ChatColor.GOLD + "[Processor(s)] " + ChatColor.WHITE + runtime.availableProcessors());
      sender.sendMessage(ChatColor.GOLD + "[Maximum Ram]: " + ChatColor.WHITE + runtime.maxMemory() / 1024L / 1024L + " MB" + " " + runtime.maxMemory() * 100L / runtime.maxMemory() + "%");
      sender.sendMessage(ChatColor.GOLD + "[Allocated Ram]: " + ChatColor.WHITE + runtime.totalMemory() / 1024L / 1024L + " MB" + " " + runtime.totalMemory() * 100L / runtime.maxMemory() + "%");
      sender.sendMessage(ChatColor.GOLD + "[Used Ram]: " + ChatColor.WHITE + (runtime.totalMemory() - runtime.freeMemory()) / 1024L / 1024L + " MB" + " " + (runtime.totalMemory() - runtime.freeMemory()) * 100L / runtime.maxMemory() + "%");
      sender.sendMessage(ChatColor.GOLD + "[Free Ram]: " + ChatColor.WHITE + runtime.freeMemory() / 1024L / 1024L + " MB" + " " + runtime.freeMemory() * 100L / runtime.maxMemory() + "%");
      if (Bukkit.getVersion().startsWith("git-Bukkit")) {
        sender.sendMessage(ChatColor.GOLD + "[CraftBukkit Version]: " + ChatColor.WHITE + Bukkit.getVersion() + " (Implementing API version " + Bukkit.getBukkitVersion() + ")");
      } else if (Bukkit.getVersion().startsWith("git-Spigot")) {
        sender.sendMessage(ChatColor.GOLD + "[Spigot Version]: " + ChatColor.WHITE + Bukkit.getVersion() + " (Implementing API version " + Bukkit.getBukkitVersion() + ")");
      } else {
        sender.sendMessage(ChatColor.GOLD + "[Unknown Version]: " + ChatColor.WHITE + Bukkit.getVersion() + " (Implementing API version " + Bukkit.getBukkitVersion() + ")");
      }
      sender.sendMessage(ChatColor.GOLD + "[Information Version]: " + ChatColor.WHITE + Bukkit.getServer().getPluginManager().getPlugin("Information").getDescription().getVersion());
      return true;
    }
    sender.sendMessage(ChatColor.DARK_RED + "[Information]" + ChatColor.RED + " I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
    return true;
  }
}
