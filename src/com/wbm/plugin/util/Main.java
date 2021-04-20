package com.wbm.plugin.util;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {

		this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "wbmMC ON");
	}

	@Override
	public void onDisable() {
		this.getServer().getConsoleSender().sendMessage(ChatColor.RED + "wbmMC OFF");
	}
}
