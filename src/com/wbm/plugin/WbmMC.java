package com.wbm.plugin;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.wbm.plugin.self.DataManager;
import com.wbm.plugin.self.Settings;
import com.wbm.plugin.util.Metrics;
import com.wbm.plugin.util.UpdateChecker;
import com.wbm.plugin.util.Utils;

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

		Utils.info(ChatColor.GREEN + "=============================================");
		Utils.info(ChatColor.RESET + "                    WbmMC                ");
		Utils.info(ChatColor.GREEN + "=============================================");

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
		Utils.info("                Update Checker                ");
		Utils.info(" - Current version: " + currentVersionColor + currentVersion);
		Utils.info(" - Latest  version: " + latestVersionColor + latestReleaseTag);

		if (!isLatest) {
			Utils.warning("");
			Utils.warning("Your version is " + currentVersionColor + "outdated");
			Utils.warning("Download latest version: " + "https://github.com/worldbiomusic/wbmMC/releases");
		}
		Utils.info(ChatColor.GREEN + "=============================================");

		return isLatest;
	}



	@Override
	public void onDisable() {
		Utils.info(ChatColor.RED + "wbmMC OFF");
	}

}

