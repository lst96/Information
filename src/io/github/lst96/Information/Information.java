package io.github.lst96.Information;

import io.github.lst96.Information.Commands.DelVote;
import io.github.lst96.Information.Commands.Donate;
import io.github.lst96.Information.Commands.Einfo;
import io.github.lst96.Information.Commands.Facebook;
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
import io.github.lst96.Information.Commands.Vote;
import io.github.lst96.Information.Commands.Website;
import io.github.lst96.Information.Commands.Youtube;
import io.github.lst96.Information.Listeners.Elistener;
import io.github.lst96.Information.Runnables.Tps;
import io.github.lst96.Information.metrics.Metrics;

import java.io.IOException;
import java.util.logging.Logger;

import net.gravitydevelopment.updater.Updater;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Information extends JavaPlugin {

	public final Logger logger = Logger.getLogger("Minecraft");
	public PluginDescriptionFile pdfFile;
	public String PREFIX;
	public boolean autoUpdate = false;
	Updater updater;
	private boolean compatible;

	@Override
	public void onEnable() {
		pdfFile = this.getDescription();
		PREFIX = "[" + pdfFile.getName() + "]";
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Tps(), 100L, 1L);
		getServer().getPluginManager().registerEvents(new Elistener(this), this);
		this.logger.info(PREFIX + " Information version " + pdfFile.getVersion() + " has been enabled.");
		this.logger.info(PREFIX + " Developed by: " + pdfFile.getAuthors());
		getConfig().options().copyDefaults(true);
		saveConfig();
		System.gc();
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException localIOException) {
		}
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
		autoUpdate = this.getConfig().getBoolean("autoupdate");
		if (autoUpdate) {
			setupUpdater();
			String mcVersion = Bukkit.getBukkitVersion();
			this.compatible = mcVersion.startsWith("1.7.2");
			if ((this.getConfig().getBoolean("check_bukkit_compatibility"))
					&& (!this.compatible)) {
				this.logger.info("[Information] is not compatible with " + Bukkit.getVersion());
				getServer().getPluginManager().disablePlugin(this);
				return;
			}
		}
	}

	@Override
	public void onDisable() {
		this.logger.info(PREFIX + " Information Disabled.");
	}

	private void setupUpdater() {

		Updater updater = new Updater(this, 57100, this.getFile(),
				Updater.UpdateType.DEFAULT, true);
		Updater.UpdateResult result = updater.getResult();
		switch (result) {
		case SUCCESS:
			// Success: The updater found an update, and has readied it to be
			// loaded the next time the server restarts/reloads
			break;
		case NO_UPDATE:
			// No Update: The updater did not find an update, and nothing was
			// downloaded.
			break;
		case DISABLED:
			// Won't Update: The updater was disabled in its configuration file.
			break;
		case FAIL_DOWNLOAD:
			// Download Failed: The updater found an update, but was unable to
			// download it.
			break;
		case FAIL_DBO:
			// dev.bukkit.org Failed: For some reason, the updater was unable to
			// contact DBO to download the file.
			break;
		case FAIL_NOVERSION:
			// No version found: When running the version check, the file on DBO
			// did not contain the a version in the format 'vVersion' such as
			// 'v1.0'.
			break;
		case FAIL_BADID:
			// Bad id: The id provided by the plugin running the updater was
			// invalid and doesn't exist on DBO.
			break;
		case FAIL_APIKEY:
			// Bad API key: The user provided an invalid API key for the updater
			// to use.
			break;
		case UPDATE_AVAILABLE:
			// There was an update found, but because you had the UpdateType set
			// to NO_DOWNLOAD, it was not downloaded.
		}
	}
}
