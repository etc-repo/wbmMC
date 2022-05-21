package com.wbm.plugin.util;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundTool {
	public static void play(Location loc, Sound sound) {
		loc.getWorld().playSound(loc, sound, 10, 1);
	}

	public static void play(Player p, Sound sound) {
		p.playSound(p.getLocation(), sound, 10, 1);
	}

	public static void play(List<Player> players, Sound sound) {
		players.forEach(p -> play(p, sound));
	}
}
