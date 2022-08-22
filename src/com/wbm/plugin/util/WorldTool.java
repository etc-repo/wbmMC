package com.wbm.plugin.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.codehaus.plexus.util.FileUtils;

public class WorldTool {

	/*
	 * These method works, but recommend Multiverse-Core 
	 */

	/**
	 * Create or load world
	 * 
	 * @param name of world
	 * @return Created or loaded world
	 */
	public static World create(String name) {
		return new WorldCreator(name).createWorld();
	}

	/**
	 * Copy world with directory
	 * 
	 * @param originWorld
	 * @param newWorldName
	 * @return Copied world or null if IOException throws
	 */
	public static World copy(World originWorld, String newWorldName) {
		try {
			// copy folder
			File newWorldFolder = new File(Bukkit.getWorldContainer(), newWorldName);
			FileUtils.copyDirectoryStructure(originWorld.getWorldFolder(), newWorldFolder);

			// load world
			return create(newWorldName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Remove world directory
	 * 
	 * @param world to remove
	 * @return False if there are players in the world or IOException throws
	 */
	public static boolean remove(World world) {
		// check there are no players in the world
		if (!world.getPlayers().isEmpty()) {
			return false;
		}

		// unload world before remove
		Bukkit.unloadWorld(world, false);

		// delete world directory
		try {
			FileUtils.deleteDirectory(world.getWorldFolder());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean exist(String worldName) {
		List<File> dirs = Arrays.asList(Bukkit.getWorldContainer().listFiles()).stream().filter(File::isDirectory)
				.toList();

		for (File dir : dirs) {
			if (dir.getName().equals(worldName)) {
				return true;
			}
		}

		return false;
	}
}
