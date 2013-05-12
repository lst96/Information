package io.github.lst96.Information.monitors;

import io.github.lst96.Information.Configuration;
import io.github.lst96.Information.PerformanceMonitor;
import org.bukkit.ChatColor;

public class RestartMonitor
{
  private long startTime = 0L;

  public final String getLastRestartTimeStamp(PerformanceMonitor monitor)
  {
    ChatColor labelColor = monitor.getConfigurationClass().labelColor;
    ChatColor valueColor = monitor.getConfigurationClass().valueColor;

    return "" + ChatColor.GOLD + getTimeSinceRestart() / 604800000L + labelColor + " W, " + valueColor + getTimeSinceRestart() / 86400000L + labelColor + " D, " + valueColor + getTimeSinceRestart() / 3600000L % 24L + labelColor + " H, " + valueColor + getTimeSinceRestart() / 60000L % 60L + labelColor + " M, " + valueColor + getTimeSinceRestart() / 1000L % 60L + labelColor + " S";
  }

  private long getMillis()
  {
    return System.currentTimeMillis();
  }

  public final long getTimeSinceRestart()
  {
    return getMillis() - this.startTime;
  }

  public final void setStartTime()
  {
    this.startTime = getMillis();
  }
}