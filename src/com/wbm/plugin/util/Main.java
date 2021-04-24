package com.wbm.plugin.util;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	private static Main instance;

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
		BroadcastTool.info(ChatColor.GREEN + "wbmMC ON");
	}

	@Override
	public void onDisable() {
		BroadcastTool.warn(ChatColor.RED + "wbmMC OFF");
	}
}
