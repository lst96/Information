package io.github.lst96.Information.Commands;

import io.github.lst96.Information.Information;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UUID
  implements CommandExecutor
{
  public UUID(Information instance) {}
  
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    if (commandLabel.equalsIgnoreCase("uuid"))
    {
      if ((sender.isOp()) || (sender.hasPermission("information.uuid")))
      {
        if (args.length != 1)
        {
          sender.sendMessage(ChatColor.RED + "Incorrect usage! Correct usage /uuid <playername>.");
          return false;
        }
        Player targetplayer = Bukkit.getServer().getPlayer(args[0]);
        if (targetplayer == null)
        {
          sender.sendMessage(ChatColor.DARK_RED + args[0] + ChatColor.RED + " is currently not online.");
          return true;
        }
        sender.sendMessage(ChatColor.GOLD + args[0] + " UUID: " + ChatColor.WHITE + targetplayer.getUniqueId());
        return true;
      }
      sender.sendMessage(ChatColor.DARK_RED + "[Information]" + ChatColor.RED + " I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
      return false;
    }
    return false;
  }
}
