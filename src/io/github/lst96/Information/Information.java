package io.github.lst96.Information;

import io.github.lst96.Information.metrics.Metrics;
import io.github.lst96.Information.update.UpdateChecker;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Information extends JavaPlugin {
	
	protected Logger log;
	public PluginDescriptionFile pdfFile;
	protected UpdateChecker updatechecker;

	
   @Override 
   public void onEnable (){
	   this.log = this.getLogger();
	   PluginDescriptionFile pdfFile = getDescription();
	   this.log.info(pdfFile.getName() + " v" + pdfFile.getVersion() + " is enabled.");
	   getConfig().options().copyDefaults(true);
	   saveConfig();
	   Runtime runtime = Runtime.getRuntime();
       System.gc();
       try {
    	    Metrics metrics = new Metrics(this);
    	    metrics.start();
    	} catch (IOException e) {
    	    // Failed to submit the stats :-(
    	}
       this.updatechecker = new UpdateChecker(this, "http://dev.bukkit.org/server-mods/information/files.rss");
     
       if (this.updatechecker.updateNeeded()){
    	   this.log.info("A new version is available: " + this.updatechecker.getVersion());
    	   this.log.info("Get it from: " + this.updatechecker.getLink());
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
   }
   public void onDisable(){
	   PluginDescriptionFile pdfFile = getDescription();
	   getLogger().info(pdfFile.getName() + " v" + pdfFile.getVersion() + " is Disabled.");
   }
}


