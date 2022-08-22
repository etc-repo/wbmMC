package com.wbm.plugin.util;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.wbm.plugin.WbmMC;

public class Logs {

	public static String messagePrefix = "WbmMC";
	static JavaPlugin main = WbmMC.getInstance();
	static Logger logger = main.getLogger();
	static ConsoleCommandSender sender = main.getServer().getConsoleSender();

	private static String getMessagePrefixString() {
		return "[" + messagePrefix + "] ";
	}

	public static void info(String msg) {
		logger.info(msg);
	}

	public static void warning(String msg) {
		logger.warning(msg);
	}

	public static void severe(String msg) {
		logger.severe(msg);
	}

	public static void broadcast(String msg) {
		Bukkit.broadcastMessage(getMessagePrefixString() + msg);
	}

}
