package com.wbm.plugin.util.instance;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;

public class TPManager {

	private Map<String, Location> tpData = new HashMap<>();

	public void registerLocation(String title, Location loc) {
		tpData.put(title, loc);
	}

	public Location getLocation(String title) {
		return tpData.get(title);
	}
}
