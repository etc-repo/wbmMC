package com.wbm.plugin.util;

import org.bukkit.Location;
import org.bukkit.event.Listener;

public class SpawnLocationTool implements Listener
{
	public static Location JOIN;
	public static Location RESPAWN;
	public static Location LOBBY;

	public SpawnLocationTool(Location joinLocation, 
			Location respawnLocation,
			Location lobby)
	{
		SpawnLocationTool.JOIN = joinLocation;
		SpawnLocationTool.RESPAWN = respawnLocation;
		SpawnLocationTool.LOBBY = lobby;
	}
}
