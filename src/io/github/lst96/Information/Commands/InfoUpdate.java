package io.github.lst96.Information.Commands;

import io.github.lst96.Information.Information;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class InfoUpdate
  implements CommandExecutor
{
  private Information plugin;
  
  public InfoUpdate(Information instance)
  {
    this.plugin = instance;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    if ((commandLabel.equalsIgnoreCase("infoupdate")) && (
      (sender.isOp()) || (sender.hasPermission("information.update")))) {
      this.plugin.setupUpdater();
    }
    return false;
  }
}
