package io.github.lst96.Information.update;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import io.github.lst96.Information.Information;

public class UpdateNotifier
  implements Runnable
{
  final Information plugin;
  Player player;

  public UpdateNotifier(Information plugin, Player player)
  {
    this.plugin = plugin;
    this.player = player;
  }

  public void run()
  {
    if (this.player.isOnline()) {
      String update = UpdateChecker.getLatestVersion();
      try {
        if (Integer.parseInt(this.plugin.pdfFile.getVersion().replace(".", "")) < Integer.parseInt(update.split(" ")[0].replace("v", "").replace(".", "")))
          this.player.sendMessage(ChatColor.BLUE + "There is a new update for Information available: " + update);
      }
      catch (NumberFormatException e) {
        Information.logger.log(Level.WARNING, "Could not parse version updates.");
      }
    }
  }
}