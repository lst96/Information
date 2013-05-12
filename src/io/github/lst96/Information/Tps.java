package io.github.lst96.Information;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.ChatColor;

public class Tps {
	
	private int tps = 0;

	public void onEnable()
	{
	getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
	{
	    long sec;
	    long currentSec;
	    int  ticks;
	    int  delay;
	   
	    @Override
	    public void run()
	    {
	        sec = (System.currentTimeMillis() / 1000);
	       
	        if(currentSec == sec)
	        {
	            ticks++;
	        }
	        else
	        {
	            currentSec = sec;
	            tps = (tps == 0 ? ticks : ((tps + ticks) / 2));
	            ticks = 0;
	        }
	    }
	}, 0, 1);
	}

	@EventHandler
	public void eventBlockPlace(BlockPlaceEvent event)
	{
	    event.getPlayer().sendMessage("TPS = " + tps);
	}

}
