package io.github.lst96.Information;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Autosave extends JavaPlugin
{
  public Logger log = Logger.getLogger("Minecraft");
  public int taskID;
  public Boolean isRunning = Boolean.valueOf(false);

  public void onEnable() {
    this.log.info("[Simple-AutoSave] " + getDescription().getVersion() + " enabled.");

    FileConfiguration cfg = getConfig();
    FileConfigurationOptions cfgOptions = cfg.options();
    cfgOptions.copyDefaults(true);
    cfgOptions.copyHeader(true);
    saveConfig();

    getCommand("asave").setExecutor(new asave(this));
    StartAutoSave();
  }

  public void onDisable() {
    this.log.info("[Simple-AutoSave] " + getDescription().getVersion() + " disabled.");
  }

  public String replaceColorMacros(String str) {
    str = str.replace("`r", ChatColor.RED.toString());
    str = str.replace("`R", ChatColor.DARK_RED.toString());
    str = str.replace("`y", ChatColor.YELLOW.toString());
    str = str.replace("`Y", ChatColor.GOLD.toString());
    str = str.replace("`g", ChatColor.GREEN.toString());
    str = str.replace("`G", ChatColor.DARK_GREEN.toString());
    str = str.replace("`c", ChatColor.AQUA.toString());
    str = str.replace("`C", ChatColor.DARK_AQUA.toString());
    str = str.replace("`b", ChatColor.BLUE.toString());
    str = str.replace("`B", ChatColor.DARK_BLUE.toString());
    str = str.replace("`p", ChatColor.LIGHT_PURPLE.toString());
    str = str.replace("`P", ChatColor.DARK_PURPLE.toString());
    str = str.replace("`0", ChatColor.BLACK.toString());
    str = str.replace("`1", ChatColor.DARK_GRAY.toString());
    str = str.replace("`2", ChatColor.GRAY.toString());
    str = str.replace("`w", ChatColor.WHITE.toString());
    return str;
  }

  public void displayHelp(CommandSender sender) {
    sender.sendMessage(ChatColor.GOLD + "[ " + ChatColor.WHITE + "Simple-AutoSave " + getDescription().getVersion() + ChatColor.GOLD + " ]");
    sender.sendMessage(ChatColor.YELLOW + " /Asave on/off " + ChatColor.WHITE + "- Toggle AutoSaving");
    sender.sendMessage(ChatColor.YELLOW + " /Asave status " + ChatColor.WHITE + "- Check AutoSave Status");
    sender.sendMessage(ChatColor.YELLOW + " /Asave save " + ChatColor.WHITE + "- Force AutoSave");
    if ((sender == null) || (sender.hasPermission("autosave.reload")))
      sender.sendMessage(ChatColor.YELLOW + " /Asave reload " + ChatColor.WHITE + "- reload config");
  }

  public boolean AutoSaveMap()
  {
    Boolean ServerBroadcast = Boolean.valueOf(getConfig().getBoolean("Broadcast.ServerBroadcast"));
    Boolean BroadcastWorld = Boolean.valueOf(getConfig().getBoolean("Broadcast.BroadcastEachWorld"));
    String AutoSaveMessage = getConfig().getString("Broadcast.AutoSaveMessage");
    Boolean SilentMode = Boolean.valueOf(getConfig().getBoolean("Broadcast.SilentMode"));

    if (SilentMode.booleanValue()) {
      Bukkit.savePlayers();
      for (World world : Bukkit.getWorlds()) {
        world.save();
      }
      return true;
    }
    if (ServerBroadcast.booleanValue())
      Bukkit.broadcastMessage(replaceColorMacros(AutoSaveMessage));
    else {
      Bukkit.getConsoleSender().sendMessage(replaceColorMacros(AutoSaveMessage));
    }

    Bukkit.savePlayers();

    for (World world : Bukkit.getWorlds()) {
      world.save();
      if (BroadcastWorld.booleanValue()) {
        String worldName = world.getName();
        Bukkit.broadcastMessage(" " + worldName + " Saved...");
      } else {
        String worldName = world.getName();
        this.log.info(worldName + " Saved...");
      }
    }

    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Save complete.");
    return true;
  }

  public boolean StartAutoSave()
  {
    if (this.isRunning.booleanValue()) {
      this.log.info("AutoSave already running");
      return false;
    }
    int Interval = getConfig().getInt("interval") * 1200;

    this.taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Bukkit.getServer().getPluginManager().getPlugin("Simple-AutoSave"), new Runnable() {
      public void run() {
        Autosave.this.AutoSaveMap();
      }
    }
    , Interval, Interval);

    this.isRunning = Boolean.valueOf(true);
    this.log.info("Autosave Started.");
    return true;
  }

  public boolean StopAutoSave()
  {
    if (!this.isRunning.booleanValue()) {
      this.log.info("AutoSave not running");
      return false;
    }
    Bukkit.getScheduler().cancelTask(this.taskID);
    this.log.info("Autosave Halted.");
    this.isRunning = Boolean.valueOf(false);
    return true;
  }

  public void output(CommandSender sender, String phrase)
  {
    String prefix = ChatColor.GOLD + "* ";

    if (phrase == "NoPermission") {
      sender.sendMessage(prefix + ChatColor.RED + "You do not have permission.");
    }
    if (phrase == "Autosave Started") {
      sender.sendMessage(prefix + ChatColor.GREEN + "Autosave Timer Started");
    }
    if (phrase == "Autosave Already Started") {
      sender.sendMessage(prefix + ChatColor.GREEN + "Autosave Timer Already Started");
    }
    if (phrase == "Autosave Halted") {
      sender.sendMessage(prefix + ChatColor.RED + "Autosave Timer Halted");
    }
    if (phrase == "Autosave Already Halted") {
      sender.sendMessage(prefix + ChatColor.RED + "Autosave Timer Already Halted");
    }
    if (phrase == "Config Missing") {
      sender.sendMessage(prefix + ChatColor.GOLD + "Config missing, generating a new one...");
    }
    if (phrase == "Config Reloaded") {
      sender.sendMessage(prefix + ChatColor.GREEN + "AutoSave Config Reloaded");
    }
    if (phrase == "Autosave ACTIVE") {
      sender.sendMessage(prefix + ChatColor.GREEN + "AutoSave is ACTIVE");
    }
    if (phrase == "Autosave INACTIVE")
      sender.sendMessage(prefix + ChatColor.RED + "AutoSave is NOT ACTIVE");
  }
}