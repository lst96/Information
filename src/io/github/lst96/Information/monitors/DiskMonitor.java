package io.github.lst96.Information.monitors;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import io.github.lst96.Information.PerformanceMonitor;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class DiskMonitor
{
  private final PerformanceMonitor monitor;
  private final String filePath;

  public DiskMonitor(PerformanceMonitor monitor)
  {
    this.monitor = monitor;

    File file = new File(this.monitor.getDataFolder(), "config.yml");
    this.filePath = file.getAbsolutePath().split("PerformanceMonitor")[0];
  }

  public final int getPluginAmount()
  {
    File file = new File(this.filePath);
    int plugins = 0;

    if (file.exists()) {
      File[] listedFiles = file.listFiles();
      for (File currentFile : listedFiles) {
        if (currentFile.getName().contains(".jar")) {
          plugins++;
        }
      }
    }
    return plugins;
  }

  public final long getWorldSize(Player player)
  {
    File worldFolder = player.getWorld().getWorldFolder();
    String worldFolderPath = worldFolder.getAbsolutePath();
    ArrayList fileArray = new ArrayList();
    File[] filesInDir = worldFolder.listFiles();

    if ((filesInDir != null) && (filesInDir.length > 0)) {
      fileArray.addAll(Arrays.asList(filesInDir));
    }

    filesInDir = new File(worldFolderPath + "/data").listFiles();
    if ((filesInDir != null) && (filesInDir.length > 0)) {
      fileArray.addAll(Arrays.asList(filesInDir));
    }

    filesInDir = new File(worldFolderPath + "/players").listFiles();
    if ((filesInDir != null) && (filesInDir.length > 0)) {
      fileArray.addAll(Arrays.asList(filesInDir));
    }

    filesInDir = new File(worldFolderPath + "/region").listFiles();
    if ((filesInDir != null) && (filesInDir.length > 0)) {
      fileArray.addAll(Arrays.asList(filesInDir));
    }

    int totalSizes = 0;
    if (fileArray.size() > 0) {
      for (int fileNo = 0; fileNo < fileArray.size(); fileNo++) {
        totalSizes = (int)(totalSizes + ((File)fileArray.get(fileNo)).length());
      }
    }
    return totalSizes;
  }

  public final long getServerLogSize()
  {
    String logPath = this.filePath.split("plugins")[0];
    File logFile = new File(logPath + "/server.log");

    if (!logFile.exists()) {
      return 0L;
    }

    return logFile.length();
  }

  public final long getFreeDiskSpace()
  {
    String path = this.filePath.split("plugins")[0];
    File directory = new File(path);
    return Math.round((float)(directory.getFreeSpace() / 1024L / 1024L));
  }

  public final String getBackupData()
  {
    String path = this.filePath.split("plugins")[0];
    File directory = new File(path + "/backups");

    if (!directory.exists()) {
      return null;
    }

    File[] backups = directory.listFiles();
    if ((backups == null) && (backups.length < 1)) {
      return null;
    }

    ArrayList fileNames = new ArrayList();
    for (File backup : backups) {
      String backupName = backup.getName();
      if ((backupName.length() < 5) && (backupName.substring(backupName.length() - 3, backupName.length()).equals("zip"))) {
        fileNames.add(backupName);
      }
    }

    if (fileNames.isEmpty()) {
      return null;
    }

    Collections.sort(fileNames);
    String[] lastBackup = ((String)fileNames.get(fileNames.size())).split("");
    if (lastBackup.length == 20) {
      String year = lastBackup[1] + lastBackup[2] + lastBackup[3] + lastBackup[4];
      String month = lastBackup[5] + lastBackup[6];
      String day = lastBackup[7] + lastBackup[8];
      String hour = lastBackup[10] + lastBackup[11];
      String min = lastBackup[12] + lastBackup[13];
      return year + ":" + month + ":" + day + " (" + hour + ":" + min + ")-" + backups.length;
    }
    return null;
  }
}