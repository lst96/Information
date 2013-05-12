package io.github.lst96.Information;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import io.github.lst96.Information.monitors.DiskMonitor;
import io.github.lst96.Information.monitors.MemoryMonitor;
import io.github.lst96.Information.monitors.RestartMonitor;
import io.github.lst96.Information.monitors.TpsMonitor;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatBroadcast
{
  private final PerformanceMonitor monitor;

  public StatBroadcast(PerformanceMonitor monitor)
  {
    this.monitor = monitor;
  }

  private void broadcastBackupStats(CommandSender cs, boolean viewAll)
  {
    Configuration config = this.monitor.getConfigurationClass();
    ChatColor titleColor = config.titleColor;
    ChatColor labelColor = config.labelColor;
    ChatColor valueColor = config.valueColor;

    if ((!config.showLastBackup) && (!config.showGeneratedBackups)) {
      cs.sendMessage(labelColor + "This feature has to be enabled in the config.yml file!");
      return;
    }

    String backupString = this.monitor.getDiskFileSize().getBackupData();
    cs.sendMessage(titleColor + "- Backup Stats");
    if (backupString != null) {
      String[] backupStringSplit = backupString.split("-");

      if ((viewAll) || (config.showLastBackup)) {
        cs.sendMessage(labelColor + "    Last backup generated on: " + valueColor + backupStringSplit[0]);
      }

      if ((viewAll) || (config.showGeneratedBackups))
        cs.sendMessage(labelColor + "    Backups generated: " + valueColor + backupStringSplit[1]);
    }
    else {
      cs.sendMessage(labelColor + "    No data to display!");
    }
  }

  private void broadcastDiskStats(CommandSender cs, boolean viewAll)
  {
    Configuration config = this.monitor.getConfigurationClass();

    cs.sendMessage(config.titleColor + "- Disk Stats");
    if ((viewAll) || (config.showLogSize)) {
      long serverLogSize = this.monitor.getDiskFileSize().getServerLogSize();
      cs.sendMessage(config.labelColor + "    Server log size: " + config.valueColor + serverLogSize + " bytes (" + Math.round((float)(serverLogSize / 1024L / 1024L)) + " MB)");
    }

    if ((viewAll) || (config.showFreeDiskSize))
      cs.sendMessage(config.labelColor + "    Free disk size: " + config.valueColor + this.monitor.getDiskFileSize().getFreeDiskSpace() + " MB");
  }

  private void broadcastFullStatTable(CommandSender cs)
  {
    Configuration config = this.monitor.getConfigurationClass();

    if ((config.showLastRestart) || (config.showServerTime)) {
      broadcastTimeStats(cs, false);
    }

    if ((config.showTotalMemory) || (config.showFreeMemory) || (config.showMaxMemory) || (config.showUsedMemory)) {
      broadcastMemoryStats(cs, false);
    }

    if ((config.showLogSize) || (config.showFreeDiskSize)) {
      broadcastDiskStats(cs, false);
    }

    if ((config.showLastBackup) || (config.showGeneratedBackups)) {
      broadcastBackupStats(cs, false);
    }

    if ((config.showWorldSize) || (config.showLivingEntitiesInWorld) || (config.showEntitiesInWorld)) {
      broadcastWorldStats(cs, false);
    }

    if ((config.showOnlinePlayersVsMaxSlots) || (config.showUniquePlayerLogins) || (config.showTotalOperators) || (config.showCreativePlayers) || (config.showSurvivalPlayers) || (config.showAdventurePlayers)) {
      broadcastPlayerStats(cs, false);
    }

    if ((config.showTps) || (config.showLoadedPlugins) || (config.calculateServerHealth) || (config.showBukkitVersion) || (config.showServerIpAndPort))
      broadcastServerStats(cs, false);
  }

  private void broadcastMemoryStats(CommandSender cs, boolean viewAll)
  {
    Configuration config = this.monitor.getConfigurationClass();
    int max = this.monitor.getMemoryMeter().getMaxRam();
    int total = this.monitor.getMemoryMeter().getTotalRam();

    cs.sendMessage(config.titleColor + "- Memory Stats");
    if ((viewAll) || (config.showMaxMemory)) {
      cs.sendMessage(config.labelColor + "    Max memory for server: " + config.valueColor + max + config.labelColor + " MB");
    }

    if ((viewAll) || (config.showTotalMemory)) {
      cs.sendMessage(config.labelColor + "    Total allocated memory: " + config.valueColor + total + config.labelColor + " MB (" + total * 100 / max + "%)");
    }

    if ((viewAll) || (config.showFreeMemory)) {
      int free = this.monitor.getMemoryMeter().getFreeRam();
      cs.sendMessage(config.labelColor + "    Free allocated memory: " + config.valueColor + free + config.labelColor + " MB (" + free * 100 / total + "%)");
    }

    if ((viewAll) || (config.showUsedMemory)) {
      int used = this.monitor.getMemoryMeter().getUsedRam();
      cs.sendMessage(config.labelColor + "    Used allocated memory: " + config.valueColor + used + config.labelColor + " MB (" + used * 100 / total + "%)");
    }
  }

  public final void broadcastPerformanceData(CommandSender cs, String part)
  {
    cs.sendMessage(ChatColor.DARK_GRAY + "----------------" + ChatColor.DARK_RED + "{ Performance Monitor }" + ChatColor.DARK_GRAY + "----------------");

    if (part.equals(""))
      broadcastFullStatTable(cs);
    else {
      broadcastStatTablePart(cs, part);
    }

    if (this.monitor.isLatestVersion())
      cs.sendMessage(ChatColor.DARK_GRAY + "-----------------------" + ChatColor.DARK_RED + "{ " + this.monitor.getVersion() + " }" + ChatColor.DARK_GRAY + "-----------------------");
    else
      cs.sendMessage(ChatColor.DARK_GRAY + "----------------" + ChatColor.DARK_RED + "{ " + this.monitor.getVersion() + " Update Available }" + ChatColor.DARK_GRAY + "----------------");
  }

  private void broadcastPlayerStats(CommandSender cs, boolean viewAll)
  {
    Configuration config = this.monitor.getConfigurationClass();
    Server server = this.monitor.getServer();
    Player[] onlinePlayers = server.getOnlinePlayers();

    cs.sendMessage(config.titleColor + "- Player Stats");
    if ((viewAll) || (config.showOnlinePlayersVsMaxSlots)) {
      cs.sendMessage(config.labelColor + "    Online players: " + config.valueColor + onlinePlayers.length + "/" + server.getMaxPlayers());
    }

    if ((viewAll) || (config.showUniquePlayerLogins)) {
      cs.sendMessage(config.labelColor + "    Unique logins since restart: " + config.valueColor + this.monitor.uniqueLogins);
    }

    if ((viewAll) || (config.showCreativePlayers) || (config.showSurvivalPlayers) || (config.showAdventurePlayers)) {
      int creativePlayers = 0; int survivalPlayers = 0; int adventurePlayers = 0;

      if ((onlinePlayers != null) && (onlinePlayers.length > 0))
      {
        for (Player player : onlinePlayers) {
          GameMode gameMode = player.getGameMode();
          if (gameMode.equals(GameMode.CREATIVE))
            creativePlayers++;
          else if (gameMode.equals(GameMode.SURVIVAL))
            survivalPlayers++;
          else {
            adventurePlayers++;
          }
        }
      }

      if ((viewAll) || (config.showCreativePlayers)) {
        cs.sendMessage(config.labelColor + "    Online players in Creative: " + config.valueColor + creativePlayers);
      }

      if ((viewAll) || (config.showSurvivalPlayers)) {
        cs.sendMessage(config.labelColor + "    Online players in Survival: " + config.valueColor + survivalPlayers);
      }

      if ((viewAll) || (config.showAdventurePlayers)) {
        cs.sendMessage(config.labelColor + "    Online players in Adventure: " + config.valueColor + adventurePlayers);
      }
    }
    if ((viewAll) || (config.showTotalOperators))
      cs.sendMessage(config.labelColor + "    Total Operators: " + config.valueColor + server.getOperators().size());
  }

  private void broadcastStatTablePart(CommandSender cs, String part)
  {
    if (part.equals("time")) {
      broadcastTimeStats(cs, true);
      return;
    }

    if (part.equals("memory")) {
      broadcastMemoryStats(cs, true);
      return;
    }

    if (part.equals("disk")) {
      broadcastDiskStats(cs, true);
      return;
    }

    if (part.equals("backup")) {
      broadcastBackupStats(cs, true);
      return;
    }

    if (part.equals("world")) {
      broadcastWorldStats(cs, true);
      return;
    }

    if (part.equals("player")) {
      broadcastPlayerStats(cs, true);
      return;
    }

    if (part.equals("server"))
      broadcastServerStats(cs, true);
  }

  private void broadcastServerStats(CommandSender cs, boolean viewAll)
  {
    Configuration config = this.monitor.getConfigurationClass();
    Server server = this.monitor.getServer();

    cs.sendMessage(config.titleColor + "- Server Stats");
    if ((viewAll) || (config.showBukkitVersion)) {
      cs.sendMessage(config.labelColor + "    Bukkit Version: " + config.valueColor + server.getBukkitVersion());
    }

    if ((viewAll) || (config.showServerIpAndPort)) {
      String Ip = this.monitor.getServer().getIp();
      if ((Ip == null) || (Ip.length() == 0)) {
        Ip = "127.0.0.1";
      }

      cs.sendMessage(config.labelColor + "    Server IP: " + config.valueColor + Ip + ":" + server.getPort());
    }

    if ((viewAll) || (config.showLoadedPlugins)) {
      cs.sendMessage(config.labelColor + "    Loaded Plugins: " + config.valueColor + this.monitor.getDiskFileSize().getPluginAmount());
    }

    if ((viewAll) || (config.showTps)) {
      float tps = this.monitor.getTpsMeter().getAverageTps();
      if (!config.useAverageTps) {
        tps = this.monitor.getTpsMeter().getTps();
      }

      cs.sendMessage(config.labelColor + "    TPS: " + config.valueColor + tps);
    }

    if ((viewAll) || (config.calculateServerHealth))
      cs.sendMessage(config.labelColor + "    Server Health: " + config.valueColor + getServerStatus());
  }

  private void broadcastTimeStats(CommandSender cs, boolean viewAll)
  {
    Configuration config = this.monitor.getConfigurationClass();

    cs.sendMessage(config.titleColor + "- Time Stats");
    if ((viewAll) || (config.showServerTime)) {
      cs.sendMessage(config.labelColor + "    Current server time: " + config.valueColor + DateFormat.getDateTimeInstance(2, 3).format(new Date()));
    }

    if ((viewAll) || (config.showLastRestart))
      cs.sendMessage(config.labelColor + "    Time since last restart: " + this.monitor.getRestartCounter().getLastRestartTimeStamp(this.monitor));
  }

  private void broadcastWorldStats(CommandSender cs, boolean viewAll)
  {
    Configuration config = this.monitor.getConfigurationClass();

    cs.sendMessage(config.titleColor + "- World Stats");
    if (!(cs instanceof Player)) {
      cs.sendMessage(config.labelColor + "    Not available through the console");
      return;
    }

    Player player = (Player)cs;
    World world = player.getWorld();

    if ((viewAll) || (config.showWorldSize)) {
      cs.sendMessage(config.labelColor + "    Current world size: " + config.valueColor + this.monitor.getDiskFileSize().getWorldSize((Player)cs) / 1024L / 1024L + " MB");
    }
    if ((viewAll) || (config.showLoadedChunks)) {
      cs.sendMessage(config.labelColor + "    Loaded chunks in this world: " + config.valueColor + world.getLoadedChunks().length);
    }
    if ((viewAll) || (config.showLivingEntitiesInWorld)) {
      cs.sendMessage(config.labelColor + "    Living entities in this world: " + config.valueColor + world.getLivingEntities().size());
    }
    if ((viewAll) || (config.showEntitiesInWorld))
      cs.sendMessage(config.labelColor + "    Entities in this world: " + config.valueColor + world.getEntities().size());
  }

  private String getServerStatus()
  {
    MemoryMonitor memoryMeter = this.monitor.getMemoryMeter();
    int totalMem = memoryMeter.getTotalRam();
    int usedMem = totalMem - memoryMeter.getFreeRam();

    int points = 0;
    if ((totalMem >= memoryMeter.getMaxRam() - 200) && (totalMem / 2 <= usedMem)) {
      points += 10;
      if (totalMem * 0.7D <= usedMem) {
        points += 10;
        if (totalMem * 0.9D <= usedMem) {
          points += 10;
        }
      }
    }

    if (this.monitor.getTpsMeter().getTps() < 15.0F) {
      points += 2;
    }

    if (Math.round((float)(this.monitor.getDiskFileSize().getServerLogSize() / 1024L / 1024L)) > 50) {
      points++;
      if (Math.round((float)(this.monitor.getDiskFileSize().getServerLogSize() / 1024L / 1024L)) > 100) {
        points++;
      }
    }

    String status = ChatColor.DARK_GREEN + "Good";
    if (points >= 30)
      status = ChatColor.DARK_RED + "Critical!";
    else if (points >= 20) {
      status = ChatColor.GOLD + "Low";
    }
    return status;
  }
}
