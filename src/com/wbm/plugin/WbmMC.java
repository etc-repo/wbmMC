package com.wbm.plugin;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.wbm.plugin.self.DataManager;
import com.wbm.plugin.self.Settings;
import com.wbm.plugin.util.Metrics;
import com.wbm.plugin.util.UpdateChecker;
import com.wbm.plugin.util.Logs;

public class WbmMC extends JavaPlugin {
	private static WbmMC instance;
	private DataManager dataManager;

	public static WbmMC getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;

		// bstats
		new Metrics(this, 14416);

		Logs.info(ChatColor.GREEN + "=============================================");
		Logs.info(ChatColor.RESET + "                    WbmMC                ");
		Logs.info(ChatColor.GREEN + "=============================================");

		// setup data
		setupData();

		// check version
		checkVersion();
	}

	private void setupData() {
		this.dataManager = new DataManager();
	}

	public static boolean checkVersion() {
		// check option
		if (!Settings.CHECK_UPDATE) {
			return false;
		}

		String latestReleaseTag = UpdateChecker.getGithubLatestReleaseVersion(314590619);
		boolean isLatest = false;

		String currentVersion = WbmMC.getInstance().getDescription().getVersion();
		isLatest = currentVersion.equals(latestReleaseTag);

		ChatColor currentVersionColor = isLatest ? ChatColor.GREEN : ChatColor.RED;
		ChatColor latestVersionColor = ChatColor.GREEN;

		// print update checkers
		Logs.info("                Update Checker                ");
		Logs.info(" - Current version: " + currentVersionColor + currentVersion);
		Logs.info(" - Latest  version: " + latestVersionColor + latestReleaseTag);

		if (!isLatest) {
			Logs.warning("");
			Logs.warning("Your version is " + currentVersionColor + "outdated");
			Logs.warning("Download latest version: " + "https://github.com/etc-repo/wbmMC/releases");
		}
		Logs.info(ChatColor.GREEN + "=============================================");

		return isLatest;
	}



	@Override
	public void onDisable() {
		Logs.info(ChatColor.RED + "wbmMC OFF");
	}

}

