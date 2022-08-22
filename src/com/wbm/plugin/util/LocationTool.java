package com.wbm.plugin.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class LocationTool {

	// 경계포함
	public static boolean isIn(Location pos1, Location target, Location pos2) {
		double pos1X = pos1.getX();
		double pos1Y = pos1.getY();
		double pos1Z = pos1.getZ();

		double pos2X = pos2.getX();
		double pos2Y = pos2.getY();
		double pos2Z = pos2.getZ();

		/*
		 * 마크에서 위치검사할때 1 ~ 5이면 1.0 ~ 5.999까지 되야 하기 때문에
		 * 
		 * LocationTool만 큰수에 1를 더해줘서 -0.001빼줘서 해당 수의 가장 큰값까지 검사
		 */
		double smallXPos = Math.min(pos1X, pos2X);
		double smallYPos = Math.min(pos1Y, pos2Y);
		double smallZPos = Math.min(pos1Z, pos2Z);

		double bigXPos = Math.floor((Math.max(pos1X, pos2X) + 1)) - 0.001;
		double bigYPos = Math.floor((Math.max(pos1Y, pos2Y) + 1)) - 0.001;
		double bigZPos = Math.floor((Math.max(pos1Z, pos2Z) + 1)) - 0.001;

		double targetX = target.getX();
		double targetY = target.getY();
		double targetZ = target.getZ();

		if (MathTool.isIn(smallXPos, targetX, bigXPos) && MathTool.isIn(smallYPos, targetY, bigYPos)
				&& MathTool.isIn(smallZPos, targetZ, bigZPos)) {
			return true;
		}

		return false;
	}

	// 경계포함 x
	public static boolean isBetween(Location pos1, Location target, Location pos2) {
		double pos1X = pos1.getX();
		double pos1Y = pos1.getY();
		double pos1Z = pos1.getZ();

		double pos2X = pos2.getX();
		double pos2Y = pos2.getY();
		double pos2Z = pos2.getZ();

		double targetX = target.getX();
		double targetY = target.getY();
		double targetZ = target.getZ();

//		BroadcastTool.printConsoleMessage(pos1X + " " + targetX + " " + pos2X);
//		BroadcastTool.printConsoleMessage(pos1Y + " " + targetY + " " + pos2Y);
//		BroadcastTool.printConsoleMessage(pos1Z + " " + targetZ + " " + pos2Z);

		if (MathTool.isBetween(pos1X, targetX, pos2X) && MathTool.isBetween(pos1Y, targetY, pos2Y)
				&& MathTool.isBetween(pos1Z, targetZ, pos2Z)) {
			return true;
		}

		return false;
	}

	public static int getAreaBlockCount(Location loc1, Location loc2) {
		int dx = MathTool.getDiff((int) loc1.getX(), (int) loc2.getX());
		int dy = MathTool.getDiff((int) loc1.getY(), (int) loc2.getY());
		int dz = MathTool.getDiff((int) loc1.getZ(), (int) loc2.getZ());

		// +1하는 이유: 만약 (1,1) ~ (3,3) 면적의 블럭을 지정하면 총 9개의 블럭을 가리키는것인데
		// 위에서 dx, dy, dz를 구할때 차이를 구하므로 3-1 = 2 즉 2칸만을 의미하게 되서 +1을 해줌
		return (dx + 1) * (dy + 1) * (dz + 1);
	}

	public static void letEntityOnGround(Entity e) {
		World world = e.getWorld();
		Location eLoc = e.getLocation();
		int y = (int) eLoc.getY();

		Location loc = new Location(world, eLoc.getX(), y, eLoc.getZ());
		for (int i = 0; i <= y; i++) {
			if (loc.clone().subtract(0, i, 0).getBlock().getType() != Material.AIR) {
				TeleportTool.tp(e, loc);
				System.out.println("LOC: " + loc);
				break;
			}
		}
	}

	public static Location getRandomLocation(Location loc1, Location loc2) {
		// loc1과 loc2 위치중에(in) 랜덤 random tp
		int randomX = MathTool.getInRandom(loc1.getBlockX(), loc2.getBlockX());
		int randomY = MathTool.getInRandom(loc1.getBlockY(), loc2.getBlockY());
		int randomZ = MathTool.getInRandom(loc1.getBlockZ(), loc2.getBlockZ());
		return new Location(loc1.getWorld(), randomX, randomY, randomZ);
	}

	public static Location getRandomHighestLocation(Location loc1, Location loc2) {
		int randomX = MathTool.getInRandom(loc1.getBlockX(), loc2.getBlockX());
		int randomZ = MathTool.getInRandom(loc1.getBlockZ(), loc2.getBlockZ());
		World world = loc1.getWorld();
		int y = world.getHighestBlockYAt(randomX, randomZ);
		return new Location(world, randomX, y, randomZ);
	}

	public static Location minOfArea(Location pos1, Location pos2) {
		World world = pos1.getWorld();
		int pos1X = (int) pos1.getX();
		int pos2X = (int) pos2.getX();
		int pos1Y = (int) pos1.getY();
		int pos2Y = (int) pos2.getY();
		int pos1Z = (int) pos1.getZ();
		int pos2Z = (int) pos2.getZ();

		// get smaller x, y, z
		int smallX = Math.min(pos1X, pos2X);
		int smallY = Math.min(pos1Y, pos2Y);
		int smallZ = Math.min(pos1Z, pos2Z);

		return new Location(world, smallX, smallY, smallZ);
	}

	public static Location maxOfArea(Location pos1, Location pos2) {
		World world = pos1.getWorld();
		int pos1X = (int) pos1.getX();
		int pos2X = (int) pos2.getX();
		int pos1Y = (int) pos1.getY();
		int pos2Y = (int) pos2.getY();
		int pos1Z = (int) pos1.getZ();
		int pos2Z = (int) pos2.getZ();

		// get bigger x, y, z
		int bigX = Math.max(pos1X, pos2X);
		int bigY = Math.max(pos1Y, pos2Y);
		int bigZ = Math.max(pos1Z, pos2Z);

		return new Location(world, bigX, bigY, bigZ);
	}
	
	public static Location diff(Location pos1, Location pos2) { 
		World world = pos1.getWorld();
		int pos1X = (int) pos1.getX();
		int pos2X = (int) pos2.getX();
		int pos1Y = (int) pos1.getY();
		int pos2Y = (int) pos2.getY();
		int pos1Z = (int) pos1.getZ();
		int pos2Z = (int) pos2.getZ();

		// get difference
		int dx = MathTool.getDiff(pos1X, pos2X);
		int dy = MathTool.getDiff(pos1Y, pos2Y);
		int dz = MathTool.getDiff(pos1Z, pos2Z);
		
		return new Location(world, dx, dy, dz);
	}
}
