package com.wbm.plugin.util;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class ParticleTool {
	public static void line(Location start, Location end, Particle particle, int pointsPerLine,
			int particleCount) {
		double d = start.distance(end) / pointsPerLine;
		for (int i = 0; i < pointsPerLine; i++) {
			Location loc = start.clone();
			Vector direction = end.toVector().subtract(start.toVector()).normalize();
			Vector v = direction.multiply(i * d);
			loc.add(v.getX(), v.getY(), v.getZ());
			// extra = particle speed
			start.getWorld().spawnParticle(particle, loc, particleCount, 0, 0, 0, 0);
		}
	}
}
