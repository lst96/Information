package io.github.lst96.Information;

import io.github.lst96.Information.Commands.DelVote;
import io.github.lst96.Information.Commands.Donate;
import io.github.lst96.Information.Commands.Einfo;
import io.github.lst96.Information.Commands.Enchant;
import io.github.lst96.Information.Commands.Facebook;
import io.github.lst96.Information.Commands.InfoUpdate;
import io.github.lst96.Information.Commands.Inforeload;
import io.github.lst96.Information.Commands.Ip;
import io.github.lst96.Information.Commands.Motd;
import io.github.lst96.Information.Commands.Online;
import io.github.lst96.Information.Commands.PlayerInfo;
import io.github.lst96.Information.Commands.Ram;
import io.github.lst96.Information.Commands.Rules;
import io.github.lst96.Information.Commands.SetVote;
import io.github.lst96.Information.Commands.Staff;
import io.github.lst96.Information.Commands.Stats;
import io.github.lst96.Information.Commands.Twitter;
import io.github.lst96.Information.Commands.UUID;
import io.github.lst96.Information.Commands.Vote;
import io.github.lst96.Information.Commands.Website;
import io.github.lst96.Information.Commands.Youtube;
import io.github.lst96.Information.Listeners.Creative;
import io.github.lst96.Information.Listeners.Elistener;
import io.github.lst96.Information.Listeners.Flight;
import io.github.lst96.Information.Listeners.JoinWorld;
import io.github.lst96.Information.Listeners.NetherBlock;
import io.github.lst96.Information.Runnables.Tps;
import io.github.lst96.Information.metrics.Metrics;
import java.io.IOException;
import java.util.logging.Logger;
import net.gravitydevelopment.updater.Updater;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Information
  extends JavaPlugin
{
  public final Logger logger = Logger.getLogger("Minecraft");
  public PluginDescriptionFile pdfFile;
  public String PREFIX;
  public boolean autoUpdate = false;
  Updater updater;
  private boolean compatible;
  
  public void onEnable()
  {
    this.pdfFile = getDescription();
    this.PREFIX = ("[" + this.pdfFile.getName() + "]");
    getServer().getScheduler().scheduleSyncRepeatingTask(this, new Tps(), 100L, 1L);
    getServer().getPluginManager().registerEvents(new Elistener(this), this);
    getServer().getPluginManager().registerEvents(new Flight(this), this);
    getServer().getPluginManager().registerEvents(new Creative(this), this);
    getServer().getPluginManager().registerEvents(new JoinWorld(this), this);
    getServer().getPluginManager().registerEvents(new NetherBlock(this), this);
    this.logger.info(this.PREFIX + " Information version " + this.pdfFile.getVersion() + " has been enabled.");
    this.logger.info(this.PREFIX + " Developed by: " + this.pdfFile.getAuthors());
    getConfig().options().copyDefaults(true);
    saveConfig();
    System.gc();
    try
    {
      Metrics metrics = new Metrics(this);
      metrics.start();
    }
    catch (IOException localIOException) {}
    getCommand("website").setExecutor(new Website(this));
    getCommand("donate").setExecutor(new Donate(this));
    getCommand("player").setExecutor(new PlayerInfo(this));
    getCommand("vote").setExecutor(new Vote(this));
    getCommand("inforeload").setExecutor(new Inforeload(this));
    getCommand("staff").setExecutor(new Staff(this));
    getCommand("rules").setExecutor(new Rules(this));
    getCommand("ram").setExecutor(new Ram(this));
    getCommand("motd").setExecutor(new Motd(this));
    getCommand("online").setExecutor(new Online(this));
    getCommand("ip").setExecutor(new Ip(this));
    getCommand("twitter").setExecutor(new Twitter(this));
    getCommand("facebook").setExecutor(new Facebook(this));
    getCommand("einfo").setExecutor(new Einfo(this));
    getCommand("youtube").setExecutor(new Youtube(this));
    getCommand("stats").setExecutor(new Stats(this));
    getCommand("setvote").setExecutor(new SetVote(this));
    getCommand("delvote").setExecutor(new DelVote(this));
    getCommand("infoupdate").setExecutor(new InfoUpdate(this));
    getCommand("enchantall").setExecutor(new Enchant(this));
    getCommand("uuid").setExecutor(new UUID(this));
    this.autoUpdate = getConfig().getBoolean("autoupdate");
    if (this.autoUpdate)
    {
      setupUpdater();
      String mcVersion = Bukkit.getBukkitVersion();
      this.compatible = mcVersion.startsWith("1.7.2");
      if ((getConfig().getBoolean("check_bukkit_compatibility")) && 
        (!this.compatible))
      {
        this.logger.info("[Information] is not compatible with " + Bukkit.getVersion());
        getServer().getPluginManager().disablePlugin(this);
        return;
      }
    }
  }
  
  public void onDisable()
  {
    this.logger.info(this.PREFIX + " Information Disabled.");
  }
  
  public void setupUpdater()
  {
    Updater updater = new Updater(this, 57100, getFile(), Updater.UpdateType.DEFAULT, true);
    Updater.UpdateResult result = updater.getResult();
    if (updater.getResult() == Updater.UpdateResult.SUCCESS) {
      getLogger().info(ChatColor.GREEN + "Update " + updater.getLatestName() + " was found and downloaded, will be loaded on next server restart!");
    }
    if (updater.getResult() == Updater.UpdateResult.NO_UPDATE) {
      getLogger().info(ChatColor.GREEN + updater.getLatestName() + " is the latest version available!");
    }
    if (updater.getResult() == Updater.UpdateResult.DISABLED) {
      getLogger().info(ChatColor.RED + "Information autoupdate is disabled in the configuration!");
    }
    if (updater.getResult() == Updater.UpdateResult.FAIL_DOWNLOAD) {
      getLogger().info(ChatColor.RED + "Information failed to download " + updater.getLatestName());
    }
    if (updater.getResult() == Updater.UpdateResult.FAIL_DBO) {
      getLogger().info(ChatColor.RED + "Information updater was unable to contact DBO to download the update!");
    }
    if (updater.getResult() == Updater.UpdateResult.FAIL_NOVERSION) {
      getLogger().info(ChatColor.RED + "Information version was not found!");
    }
    if (updater.getResult() == Updater.UpdateResult.FAIL_BADID) {
      getLogger().info(ChatColor.RED + "Invalid Plugin ID");
    }
    if (updater.getResult() == Updater.UpdateResult.FAIL_APIKEY) {
      getLogger().info(ChatColor.RED + "Invalid Plugin API Key");
    }
    if (updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE) {
      getLogger().info(ChatColor.RED + "Information update " + updater.getLatestName() + " was found, but was not downloaded");
    }
    switch (result)
    {
    case DISABLED: 
      break;
    case FAIL_APIKEY: 
      break;
    case FAIL_BADID: 
      break;
    case FAIL_DBO: 
      break;
    case FAIL_DOWNLOAD: 
      break;
    case FAIL_NOVERSION: 
      break;
    case NO_UPDATE: 
      break;
	case SUCCESS:
		break;
	case UPDATE_AVAILABLE:
		break;
	default:
		break;
    }
  }
}
