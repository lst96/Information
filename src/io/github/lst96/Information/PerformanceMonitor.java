package io.github.lst96.Information;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;
import io.github.lst96.Information.listeners.CommandListener;
import io.github.lst96.Information.listeners.EventListener;
import io.github.lst96.Information.monitors.DiskMonitor;
import io.github.lst96.Information.monitors.MemoryMonitor;
import io.github.lst96.Information.monitors.RestartMonitor;
import io.github.lst96.Information.monitors.TpsMonitor;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class PerformanceMonitor extends JavaPlugin
  implements Runnable
{
  private static final Logger log = Logger.getLogger("Minecraft");
  private final Configuration configClass = new Configuration();
  private EventListener loginListener;
  private RestartMonitor restartCounter;
  private DiskMonitor diskFileSize;
  private StatBroadcast broadcast;
  private MemoryMonitor memoryMeter;
  private TpsMonitor tpsMeter;
  private FileConfiguration config = null;
  private File configFile = null;
  public int uniqueLogins = 0;
  private boolean latestVersion = true;

  public final StatBroadcast getBroadcaster()
  {
    if (this.broadcast == null) {
      this.broadcast = new StatBroadcast(this);
    }
    return this.broadcast;
  }

  public final FileConfiguration getConfig()
  {
    if (this.config == null) {
      reloadConfigFile();
    }
    return this.config;
  }

  public final Configuration getConfigurationClass()
  {
    return this.configClass;
  }

  public final DiskMonitor getDiskFileSize()
  {
    if (this.diskFileSize == null) {
      this.diskFileSize = new DiskMonitor(this);
    }
    return this.diskFileSize;
  }

  public final MemoryMonitor getMemoryMeter()
  {
    if (this.memoryMeter == null) {
      this.memoryMeter = new MemoryMonitor();
    }
    return this.memoryMeter;
  }

  public final RestartMonitor getRestartCounter()
  {
    return this.restartCounter;
  }

  public final TpsMonitor getTpsMeter()
  {
    return this.tpsMeter;
  }

  public final String getVersion()
  {
    return getDescription().getVersion();
  }

  public final boolean isLatestVersion()
  {
    return this.latestVersion;
  }

  private void logMsg(String msg)
  {
    String logMsg = "[PerformanceMonitor " + getVersion() + "] " + msg;
    log.info(logMsg);
  }

  public final void onEnable()
  {
    getConfigurationClass().update(this);
    validateListeners();

    CommandListener cl = new CommandListener(this);
    getCommand("ss").setExecutor(cl);
    getCommand("serverstate").setExecutor(cl);

    logMsg("Plugin enabled!");

    if (getConfigurationClass().checkForUpdatesOnStart)
      new Thread(this).start();
    try
    {
      MetricsLite metrics = new MetricsLite(this);
      metrics.start();
    }
    catch (IOException e)
    {
    }
  }

  public final void onDisable()
  {
    if (getConfigurationClass().showTps) {
      registerSchedulingTasks(true);
    }
    logMsg("Plugin disabled!");
  }

  private void registerSchedulingTasks(boolean cancel)
  {
    if (!cancel) {
      logMsg("Starting tps meter ...");
      getServer().getScheduler().scheduleSyncRepeatingTask(this, this.tpsMeter, 0L, 40L);
    } else {
      getServer().getScheduler().cancelTasks(this);
    }
  }

  public final void reloadConfigFile()
  {
    if (this.configFile == null) {
      this.configFile = new File(getDataFolder(), "config.yml");
    }
    this.config = YamlConfiguration.loadConfiguration(this.configFile);

    InputStream defaultConfigStream = getResource("config.yml");
    if (defaultConfigStream != null) {
      YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
      this.config.setDefaults(defaultConfig);
    }
    getConfigurationClass().update(this);
    validateListeners();
  }

  public final void run()
  {
    String address = "http://richarddahlgren.net/bukkit/pmon.html";
    try {
      URL url = new URL("http://richarddahlgren.net/bukkit/pmon.html");
      URLConnection connection = url.openConnection();
      connection.setConnectTimeout(8000);
      connection.setReadTimeout(15000);
      connection.setRequestProperty("User-agent", "PerformanceMonitor " + getVersion());

      InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      String version = bufferedReader.readLine();
      if ((version != null) && (!version.equals(getVersion()))) {
        this.latestVersion = false;
        logMsg("There is a new version available for download!");
      }

      connection.getInputStream().close();
    } catch (IOException ex) {
      logMsg("Could not check for latest version: " + ex.getMessage());
    }
  }

  public final void saveConfigFile()
  {
    if ((this.config != null) && (this.configFile != null))
      try {
        getConfig().save(this.configFile);
      } catch (IOException ex) {
        logMsg("Could not save config to " + this.configFile + "! " + ex.getMessage());
      }
  }

  private boolean validateListeners()
  {
    if ((getConfigurationClass().showLastRestart) && 
      (this.restartCounter == null)) {
      this.restartCounter = new RestartMonitor();
      this.restartCounter.setStartTime();
    }

    if ((getConfigurationClass().showTps) && 
      (this.tpsMeter == null)) {
      this.tpsMeter = new TpsMonitor(this);
      registerSchedulingTasks(false);
    }

    if (((getConfigurationClass().statusMessageUponLogin) || (getConfigurationClass().showUniquePlayerLogins)) && 
      (this.loginListener == null)) {
      this.loginListener = new EventListener(this);
      getServer().getPluginManager().registerEvents(this.loginListener, this);
    }

    return true;
  }
}