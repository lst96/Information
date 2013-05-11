package io.github.lst96.Information;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Information extends JavaPlugin {
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public static Information plugin;
	
   @Override 
   public void onEnable (){
	   PluginDescriptionFile pdfFile = getDescription();
	   this.logger.info(pdfFile.getName() + " v" + pdfFile.getVersion() + " is enabled.");
	   getConfig().options().copyDefaults(true);
	   saveConfig();
	   getCommand("website").setExecutor(new Commands(this)); 
	   getCommand("donate").setExecutor(new Commands(this)); 
	   getCommand("vote").setExecutor(new Commands(this)); 
	   getCommand("inforeload").setExecutor(new Commands(this)); 
	   getCommand("staff").setExecutor(new Commands(this)); 
	   getCommand("rules").setExecutor(new Commands(this));
	   getCommand("got push").setExecutor(new Commands(this));
   }
   public void onDisable(){
	   PluginDescriptionFile pdfFile = getDescription();
	   this.logger.info(pdfFile.getName() + " v" + pdfFile.getVersion() + " is Disabled.");
   }
}