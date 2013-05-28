package io.github.lst96.Information;

import java.util.logging.Logger;
import net.h31ix.updater.Updater;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import io.github.lst96.Information.metrics.Metrics;
import java.io.IOException;

public class Information extends JavaPlugin {
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public PluginDescriptionFile pdfFile;
	public String PREFIX;
	public boolean isUpdate = false;
	public boolean messages = false;
	public boolean autoUpdate = false;
	Updater updater;
	
	public void onEnable() {
		
		this.saveDefaultConfig();
		pdfFile = this.getDescription();
		PREFIX = "[" + pdfFile.getName() + "]";
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Tps(), 100L, 1L);
		this.logger.info(PREFIX + " Information version " + pdfFile.getVersion() + " has been enabled.");
		this.logger.info(PREFIX + " Developed by: " + pdfFile.getAuthors());
		getConfig().options().copyDefaults(true);
	    saveConfig();
	    Runtime runtime = Runtime.getRuntime();
	    System.gc();
	    try {
	      Metrics metrics = new Metrics(this);
	      metrics.start();
	    }
	    catch (IOException localIOException) {
	    }
	    getCommand("website").setExecutor(new Website(this));
	    getCommand("donate").setExecutor(new Donate(this));
	    getCommand("vote").setExecutor(new Vote(this));
	    getCommand("inforeload").setExecutor(new Inforeload(this));
	    getCommand("staff").setExecutor(new Staff(this));
	    getCommand("rules").setExecutor(new Rules(this));
	    getCommand("ram").setExecutor(new Ram(this));
	    getCommand("motd").setExecutor(new Motd(this));
	    getCommand("online").setExecutor(new Online(this));
	    getCommand("ip").setExecutor(new Ip(this));
	    getCommand("player").setExecutor(new PlayerInfo(this));
	    getCommand("twitter").setExecutor(new PlayerInfo(this));
		
		messages = this.getConfig().getBoolean("messages");
		autoUpdate = this.getConfig().getBoolean("autoupdate-check");
		if(autoUpdate) {
			setupUpdater();
		}
	}
	public void onDisable() {
		this.logger.info(PREFIX +" plugin disabled.");
	}
	
	private void setupUpdater() {
	
		updater = new Updater(this, "information", this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
        switch(updater.getResult())
        {
            case SUCCESS:
                break;
            case NO_UPDATE:
                break;
            case FAIL_DOWNLOAD:
            case FAIL_DBO:
            case FAIL_NOVERSION:
            case FAIL_BADSLUG:
            	this.logger.warning(PREFIX + " Updater Failed!");
                break;
            case UPDATE_AVAILABLE:
            	this.logger.info(PREFIX + " New version " + updater.getLatestVersionString() + " available!");
            	isUpdate = true;
            	break;
            default:
            	break;
        }
	}
	
}