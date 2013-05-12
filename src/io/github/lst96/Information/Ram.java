package io.github.lst96.Information;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

public class Ram implements CommandExecutor{
	
	private long serverStart = System.currentTimeMillis();
	
	private Information plugin; // pointer to your main class, unrequired if you don't need methods from the main class
	 
    public Ram(Information instance) {
        this.plugin = instance;
        
    }
        
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            Runtime runtime = Runtime.getRuntime();
            System.gc();
            if ((sender.isOp()) || (sender.hasPermission("information.ram"))) {
              long diff = System.currentTimeMillis() - this.serverStart;
              sender.sendMessage(ChatColor.GOLD + "[Uptime]: " + (int)(diff / 86400000L) + "d" + " " + (int)(diff / 3600000L % 24L) + "h" + " " + (int)(diff / 60000L % 60L) + "m" + " " + (int)(diff / 1000L % 60L) + "s");
              sender.sendMessage(ChatColor.GOLD + "[Maximum Ram]: " + ChatColor.RESET + ChatColor.RED + runtime.maxMemory() / 1048576L + " MB");
              sender.sendMessage(ChatColor.GOLD + "[Allocated Ram]: " + ChatColor.RESET + ChatColor.RED + runtime.totalMemory() / 1048576L + " MB");
              sender.sendMessage(ChatColor.GOLD + "[Used Ram]: " + ChatColor.RESET + ChatColor.RED + (runtime.totalMemory() - runtime.freeMemory()) / 1048576L + " MB");
              sender.sendMessage(ChatColor.GOLD + "[Free Ram]: " + ChatColor.RESET + ChatColor.RED + runtime.freeMemory() / 1048576L + " MB");
              sender.sendMessage(ChatColor.GOLD + "[CraftBukkit Version]: " + ChatColor.RESET + ChatColor.RED + Bukkit.getBukkitVersion());
              sender.sendMessage(ChatColor.GOLD + "[Information Version]: " + ChatColor.RESET + ChatColor.RED + Bukkit.getServer().getPluginManager().getPlugin("Information").getDescription());
              return true;
            }

            sender.sendMessage(ChatColor.DARK_RED + "[Information]" + ChatColor.RED + "You don't have permissions.");
            return true;
        }
}
