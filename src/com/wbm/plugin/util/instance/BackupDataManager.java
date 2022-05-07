package com.wbm.plugin.util.instance;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.codehaus.plexus.util.FileUtils;

import com.wbm.plugin.util.Utils;

/**
 * Backup directory: "plugin/plugin_backup"<br>
 * Backup data directory: "plugin/plugin_backup/data1"<br>
 *
 */
public class BackupDataManager {
	public static final String BACKUP_LABEL = "_backup";
	private JavaPlugin javaPlugin;

	public BackupDataManager(JavaPlugin javaPlugin) {
		this.javaPlugin = javaPlugin;

		// mkdirs
		getBackupDir();
	}

	public File saveBackupData() {
		// Ref:
		// https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
		String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'H;mm;ss"));
		return saveBackupData(nowTime);
	}

	public File saveBackupData(String directory) {
		File backupDataDir = null;
		if (directory == null) {
			return saveBackupData();
		}

		backupDataDir = getBackupDataDir(directory);
		if (!backupDataDir.exists()) {
			backupDataDir.mkdirs();
		}

		try {
			File srcDir = this.javaPlugin.getDataFolder();

			// copy files with directory structure
			FileUtils.copyDirectoryStructure(srcDir, backupDataDir);

			// copy files without directory structure
//			FileUtils.copyDirectory(originFile, backupFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return backupDataDir;
	}

	/**
	 * If dstDir is not exist, make directories
	 * 
	 * @param backupDataDir Backup data directory
	 * @param dstDir        Destination directory
	 * @return True if works normally
	 */
	public boolean loadBackupData(String backupDataDirName, File dstDir) {
		if (!existBackupData(backupDataDirName)) {
			return false;
		}

		if (!dstDir.exists()) {
			dstDir.mkdirs();
		}

		File backupDataDir = getBackupDataDir(backupDataDirName);

		try {
			FileUtils.copyDirectoryStructure(backupDataDir, dstDir);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public File getBackupDataDir(String directory) {
		File backupDataDir = new File(getBackupDir(), directory);
		return backupDataDir;
	}

	public boolean existBackupData(String directory) {
		return getBackupDataDir(directory).exists();
	}

	public void startSavingBackupDataTask(int delayMin) {
		new BukkitRunnable() {
			@Override
			public void run() {
				saveBackupData();
			}
		}.runTaskTimer(javaPlugin, 20 * 60 * delayMin, 20 * 60 * delayMin);
	}

	public JavaPlugin getJavaPlugin() {
		return this.javaPlugin;
	}

	public String getBackupDirectoryName() {
		return this.javaPlugin.getDataFolder().getName() + BACKUP_LABEL;
	}

	public File getBackupDir() {
		File originFile = this.javaPlugin.getDataFolder();
		File backupDataDir = new File(originFile.getParentFile(), getBackupDirectoryName());
		if (!backupDataDir.exists()) {
			backupDataDir.mkdirs();
		}
		return backupDataDir;
	}
}
