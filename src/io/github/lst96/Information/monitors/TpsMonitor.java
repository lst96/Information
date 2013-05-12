package io.github.lst96.Information.monitors;

import java.util.LinkedList;
import io.github.lst96.Information.Configuration;
import io.github.lst96.Information.PerformanceMonitor;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class TpsMonitor
  implements Runnable
{
  private final PerformanceMonitor monitor;
  private final LinkedList<Float> loggedTps = new LinkedList();
  private long lastCall = getMillis() - 3000L;
  private long lastWarning = 0L;
  private float tps = 20.0F;
  private int interval = 40;

  public TpsMonitor(PerformanceMonitor mon)
  {
    this.monitor = mon;
  }

  public void addTps(Float tps)
  {
    if ((tps != null) && (tps.floatValue() <= 20.0F)) {
      this.loggedTps.add(tps);
    }
    if (this.loggedTps.size() > 10)
      this.loggedTps.poll();
  }

  public final float getAverageTps()
  {
    float amount = 0.0F;
    for (Float f : this.loggedTps) {
      if (f != null) {
        amount += f.floatValue();
      }
    }
    return amount / this.loggedTps.size();
  }

  private long getMillis()
  {
    return System.currentTimeMillis();
  }

  public final float getTps()
  {
    return this.tps;
  }

  public final void run()
  {
    long currentTime = getMillis();
    long spentTime = (currentTime - this.lastCall) / 1000L;
    if (spentTime == 0L) {
      spentTime = 1L;
    }

    float calculatedTps = (float)(this.interval / spentTime);
    if (calculatedTps > 20.0F) {
      calculatedTps = 20.0F;
    }

    setTps(calculatedTps);
    addTps(Float.valueOf(calculatedTps));
    if ((this.monitor.getConfigurationClass().warnWhenTpsIsLow) && (this.monitor.getConfigurationClass().warnAtTpsLevel >= getAverageTps()) && (this.lastWarning + 10000L < getMillis())) {
      Player[] online = this.monitor.getServer().getOnlinePlayers();
      if ((online != null) && (online.length > 0)) {
        for (Player player : online) {
          if (player.hasPermission("pmonitor.showstate")) {
            player.sendMessage(ChatColor.RED + "Warning: The TPS has reached a low level!");
          }
        }
      }
      this.lastWarning = getMillis();
    }
    this.lastCall = getMillis();
  }

  private void setTps(float newTps)
  {
    this.tps = newTps;
  }
}