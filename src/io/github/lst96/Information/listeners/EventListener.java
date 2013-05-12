package io.github.lst96.Information.listeners;

import java.util.ArrayList;
import io.github.lst96.Information.Configuration;
import io.github.lst96.Information.PerformanceMonitor;
import io.github.lst96.Information.StatBroadcast;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener
  implements Listener
{
  private final PerformanceMonitor monitor;
  private final ArrayList<String> names = new ArrayList();

  public EventListener(PerformanceMonitor mon)
  {
    this.monitor = mon;
  }

  @EventHandler(priority=EventPriority.NORMAL)
  public final void onPlayerJoin(PlayerJoinEvent e)
  {
    Player player = e.getPlayer();
    if ((this.monitor.getConfigurationClass().showUniquePlayerLogins) && (!this.names.contains(player.getName()))) {
      this.names.add(player.getName());
      this.monitor.uniqueLogins += 1;
    }

    if ((this.monitor.getConfigurationClass().statusMessageUponLogin) && (player.hasPermission("pmonitor.showstate")))
      this.monitor.getBroadcaster().broadcastPerformanceData(player, "");
  }
}