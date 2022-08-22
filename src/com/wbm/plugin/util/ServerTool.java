package com.wbm.plugin.util;

import org.bukkit.Bukkit;

public class ServerTool {
	public static boolean isPluginEnabled(String pluginName) {
		return Bukkit.getServer().getPluginManager().isPluginEnabled(pluginName);
	}
}
