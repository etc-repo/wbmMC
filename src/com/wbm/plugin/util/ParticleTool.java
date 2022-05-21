package com.wbm.plugin.util;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class ParticleTool {
	public static void line(Location start, Location end, Particle particle, int pointsPerLine) {
		double d = start.distance(end) / pointsPerLine;
		for (int i = 0; i < pointsPerLine; i++) {
			Location loc = start.clone();
			Vector direction = end.toVector().subtract(start.toVector()).normalize();
			Vector v = direction.multiply(i * d);
			loc.add(v.getX(), v.getY(), v.getZ());
			// extra = particle speed
			spawn(loc, particle);
		}
	}

	public static void spawn(Location loc, Particle particle) {
		spawn(loc, particle, 1, 0);
	}

	public static void spawn(Location loc, Particle particle, int count, double speed) {
		loc.getWorld().spawnParticle(particle, loc, count, 0, 0, 0, speed);
	}
}
