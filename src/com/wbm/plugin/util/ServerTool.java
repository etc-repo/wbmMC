package com.wbm.plugin.util;

import com.wbm.plugin.WbmMC;

public class ServerTool {
	public static boolean isPluginEnabled(String pluginName) {
		return WbmMC.getInstance().getServer().getPluginManager().isPluginEnabled(pluginName);
	}
}
