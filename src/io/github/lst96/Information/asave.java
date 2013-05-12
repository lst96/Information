package io.github.lst96.Information;

import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.entity.Player;
import io.github.lst96.Information.Autosave;

public class asave
  implements CommandExecutor
{
  public Autosave plugin;

  public asave(Autosave plugin)
  {
    this.plugin = plugin;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player player = null;
    if ((sender instanceof Player)) {
      player = (Player)sender;
    }

    if (args.length == 0) {
      this.plugin.displayHelp(sender);
      return true;
    }
    if ((args.length == 1) && (args[0].equalsIgnoreCase("on"))) {
      if (this.plugin.StartAutoSave()) {
        this.plugin.output(sender, "Autosave Started");
        return true;
      }
      this.plugin.output(sender, "Autosave Already Started");
      return true;
    }

    if ((args.length == 1) && (args[0].equalsIgnoreCase("off"))) {
      if (this.plugin.StopAutoSave()) {
        this.plugin.output(sender, "Autosave Halted");
        return true;
      }
      this.plugin.output(sender, "Autosave Already Halted");
      return true;
    }

    if ((args.length == 1) && (args[0].equalsIgnoreCase("save"))) {
      this.plugin.AutoSaveMap();
      return true;
    }
    if ((args.length == 1) && (args[0].equalsIgnoreCase("reload")))
    {
      if ((player == null) || (player.hasPermission("autosave.reload"))) {
        File file = new File(this.plugin.getDataFolder() + File.separator + "config.yml");
        if (!file.exists()) {
          this.plugin.output(sender, "Config Missing");
          sender.sendMessage(this.plugin.getDataFolder() + File.separator + "config.yml");
          FileConfiguration cfg = this.plugin.getConfig();
          FileConfigurationOptions cfgOptions = cfg.options();
          cfgOptions.copyDefaults(true);
          cfgOptions.copyHeader(true);
          this.plugin.saveConfig();
        } else {
          this.plugin.StopAutoSave();
          this.plugin.reloadConfig();
          this.plugin.StartAutoSave();
          this.plugin.output(sender, "Config Reloaded");
          return true;
        }
      } else {
        this.plugin.output(sender, "NoPermission");
        return true;
      }
    }
    else if ((args.length == 1) && (args[0].equalsIgnoreCase("status"))) {
      if (this.plugin.isRunning.booleanValue()) {
        this.plugin.output(sender, "Autosave ACTIVE");
        return true;
      }
      this.plugin.output(sender, "Autosave INACTIVE");
      return true;
    }

    return true;
  }
}