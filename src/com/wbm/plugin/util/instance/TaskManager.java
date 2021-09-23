package com.wbm.plugin.util.instance;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.wbm.plugin.WbmMC;

public class TaskManager {
	/*
	 * [Usage]
	 * 1. register anonymous class of "Runnable"
	 * 2. run or cancel task with name
	 */

	// runnable list
	private Map<String, Runnable> runnableList;

	// running task
	private Map<String, BukkitTask> taskList;

	public TaskManager() {
		this.runnableList = new HashMap<>();
		this.taskList = new HashMap<>();
	}

	public void registerTask(String name, Runnable runnable) {
		// except for BukkitRunnable (BukkitRunnable instance can't run after used)
		if (!(runnable instanceof BukkitRunnable)) {
			// register task with name
			this.runnableList.put(name, runnable);
		}
	}

	public void cancelTask(String name) {
		if (this.taskList.containsKey(name)) {
			this.taskList.get(name).cancel();
		}
	}

	public void cancelAllTasks() {
		// BukkiTask
		for (BukkitTask task : this.taskList.values()) {
			task.cancel();
		}
	}

	public boolean isTaskCancelled(String name) {
		if (this.taskList.containsKey(name)) {
			return this.taskList.get(name).isCancelled();
		}
		return false;
	}

	// prevent bugs of using BukkitRunnable
//	public BukkitRunnable getBukkitRunnable(String name) {
//		return this.runnableList.get(name);
//	}

	private void removeRunnable(String name) {
		this.runnableList.remove(name);
	}

	public boolean existRunnable(String name) {
		return this.runnableList.containsKey(name);
	}

	// runTask
	public void runTask(String name) {
		this.runTask(name, WbmMC.getInstance());
	}

	public void runTask(String name, Plugin plugin) {
		if (this.existRunnable(name)) {
			Runnable r = this.runnableList.get(name);
			BukkitTask task = Bukkit.getScheduler().runTask(WbmMC.getInstance(), r);
			this.taskList.put(name, task);
		}
	}

	public void runTaskAsynchronously(String name, Plugin plugin) {
		if (this.existRunnable(name)) {
			Runnable r = this.runnableList.get(name);
			BukkitTask task = Bukkit.getScheduler().runTaskAsynchronously(WbmMC.getInstance(), r);
			this.taskList.put(name, task);
		}
	}

	// runTaskLater
	public void runTaskLater(String name, long delay) {
		this.runTaskLater(name, WbmMC.getInstance(), delay);
	}

	public void runTaskLater(String name, Plugin plugin, long delay) {
		if (this.existRunnable(name)) {
			Runnable r = this.runnableList.get(name);
			BukkitTask task = Bukkit.getScheduler().runTaskLater(WbmMC.getInstance(), r, delay);
			this.taskList.put(name, task);
		}
	}

	public void runTaskLaterAsynchronously(String name, Plugin plugin, long delay) {
		if (this.existRunnable(name)) {
			Runnable r = this.runnableList.get(name);
			BukkitTask task = Bukkit.getScheduler().runTaskLaterAsynchronously(WbmMC.getInstance(), r, delay);
			this.taskList.put(name, task);
		}
	}

	// runTaskTimer
	public void runTaskTimer(String name, long delay, long period) {
		this.runTaskTimer(name, WbmMC.getInstance(), delay, period);
	}

	public void runTaskTimer(String name, Plugin plugin, long delay, long period) {
		if (this.existRunnable(name)) {
			Runnable r = this.runnableList.get(name);
			BukkitTask task = Bukkit.getScheduler().runTaskTimer(WbmMC.getInstance(), r, delay, period);
			this.taskList.put(name, task);
		}
	}

	public void runTaskTimerAsynchronously​(String name, Plugin plugin, long delay, long period) {
		if (this.existRunnable(name)) {
			Runnable r = this.runnableList.get(name);
			BukkitTask task = Bukkit.getScheduler().runTaskTimerAsynchronously(WbmMC.getInstance(), r, delay, period);
			this.taskList.put(name, task);
		}
	}

}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
