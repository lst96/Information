package io.github.lst96.Information.listeners;

import io.github.lst96.Information.Configuration;
import io.github.lst96.Information.PerformanceMonitor;
import io.github.lst96.Information.StatBroadcast;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandListener
  implements CommandExecutor
{
  private final PerformanceMonitor monitor;

  public CommandListener(PerformanceMonitor monitor)
  {
    this.monitor = monitor;
  }

  private boolean isSubCommand(String command)
  {
    if (command.equals("time")) {
      return true;
    }
    if (command.equals("memory")) {
      return true;
    }
    if (command.equals("disk")) {
      return true;
    }
    if (command.equals("backup")) {
      return true;
    }
    if (command.equals("world")) {
      return true;
    }
    if (command.equals("player")) {
      return true;
    }
    if (command.equals("server")) {
      return true;
    }
    return false;
  }

  public final boolean onCommand(CommandSender cs, Command cmd, String label, String[] args)
  {
    String name = cmd.getName().toLowerCase();

    if ((!name.equals("ss")) && (!name.equals("serverstate"))) {
      return false;
    }

    ChatColor titleColor = this.monitor.getConfigurationClass().titleColor;
    ChatColor labelColor = this.monitor.getConfigurationClass().labelColor;

    if (!cs.hasPermission("Information.showstate")) {
      cs.sendMessage(titleColor + "[InformationMonitor] " + labelColor + "You are lacking permission: pmonitor.showstate!");
      return true;
    }

    if (args.length == 1) {
      String category = args[0].toLowerCase();
      if (category.equals("reload")) {
        this.monitor.reloadConfigFile();
        cs.sendMessage(titleColor + "[InformationMonitor] " + labelColor + "Configuration reloaded!");
        return true;
      }
      if (isSubCommand(category)) {
        this.monitor.getBroadcaster().broadcastPerformanceData(cs, category);
        return true;
      }
      cs.sendMessage(titleColor + "[InformationMonitor] " + labelColor + "Invalid Sub-Command!");
      return true;
    }

    this.monitor.getBroadcaster().broadcastPerformanceData(cs, "");
    return true;
  }
}