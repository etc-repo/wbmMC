package com.wbm.plugin.util.instance;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.wbm.plugin.WbmMC;

public class BukkitTaskManager {
	/*
	 * [사용법]
	 * 1. registerTask() 메소드로 먼저 실행할 구현객체를 등록한다(BukkitRunnable 익명 클래스)
	 * 2. 그 다음 runTask 관련 메소드로 실행한다
	 * 3. 종료는 cancelTask관련 메소드로 정지한다
	 * 
	 * [주의사항]
	 * - 등록(registerTask())하고 사용한(runTask~()) task는 재사용이 불가능하므로, 사용할 떄 마다
	 * 등록(registerTask())을 한다음 사용해야 함
	 */

	// 실행될 task 내용물
	private Map<String, BukkitRunnable> runnableList;

	// 실제 실행된 task
	private Map<String, BukkitTask> taskList;

	public BukkitTaskManager() {
		this.runnableList = new HashMap<>();
		this.taskList = new HashMap<>();
	}

	public void registerTask(String name, BukkitRunnable runnable) {
		// register task with name
		this.runnableList.put(name, runnable);
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

	// BukkitRunnable가져와서 사용하면 버그 날 가능성이 많으므로 제공 x
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
			BukkitTask task = this.runnableList.get(name).runTask(plugin);
			this.taskList.put(name, task);
			this.removeRunnable(name);
		}
	}

	public void runTaskAsynchronously(String name, Plugin plugin) {
		if (this.existRunnable(name)) {
			BukkitTask task = this.runnableList.get(name).runTaskAsynchronously(plugin);
			this.taskList.put(name, task);
			this.removeRunnable(name);
		}
	}

	// runTaskLater
	public void runTaskLater(String name, long delay) {
		this.runTaskLater(name, WbmMC.getInstance(), delay);
	}

	public void runTaskLater(String name, Plugin plugin, long delay) {
		if (this.existRunnable(name)) {
			BukkitTask task = this.runnableList.get(name).runTaskLater(plugin, delay);
			this.taskList.put(name, task);
			this.removeRunnable(name);
		}
	}

	public void runTaskLaterAsynchronously(String name, Plugin plugin, long delay) {
		if (this.existRunnable(name)) {
			BukkitTask task = this.runnableList.get(name).runTaskLaterAsynchronously(plugin, delay);
			this.taskList.put(name, task);
			this.removeRunnable(name);
		}
	}

	// runTaskTimer
	public void runTaskTimer(String name, long delay, long period) {
		this.runTaskTimer(name, WbmMC.getInstance(), delay, period);
	}

	public void runTaskTimer(String name, Plugin plugin, long delay, long period) {
		if (this.existRunnable(name)) {
			BukkitTask task = this.runnableList.get(name).runTaskTimer(plugin, delay, period);
			this.taskList.put(name, task);
			this.removeRunnable(name);
		}
	}

	public void runTaskTimerAsynchronously​(String name, Plugin plugin, long delay, long period) {
		if (this.existRunnable(name)) {
			BukkitTask task = this.runnableList.get(name).runTaskTimerAsynchronously(plugin, delay, period);
			this.taskList.put(name, task);
			this.removeRunnable(name);
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
