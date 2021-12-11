package com.wbm.plugin.util;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.wbm.plugin.WbmMC;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public class Utils {

	public static String messagePrefix = "WbmMC";
	static JavaPlugin main = WbmMC.getInstance();
	static Logger logger = main.getLogger();
	static ConsoleCommandSender sender = main.getServer().getConsoleSender();

	private static String getMessagePrefixString() {
		return "[" + messagePrefix + "] ";
	}

	public static void sendMsg(Player p, String msg) {
		p.sendMessage(getMessagePrefixString() + msg);
	}

	public static void sendMsg(Player p, BaseComponent compo) {
		TextComponent msg = new TextComponent(getMessagePrefixString());
		msg.addExtra(compo);
		p.spigot().sendMessage(msg);
	}

	public static void info(String msg) {
		sender.sendMessage(getMessagePrefixString() + msg);
	}

	public static void warning(String msg) {
		sender.sendMessage(ChatColor.YELLOW + getMessagePrefixString() + msg);
	}

	public static void debug(String msg) {
		info(ChatColor.RED + "[DEBUG] " + msg);
	}

	public static void broadcast(String msg) {
		Bukkit.broadcastMessage(getMessagePrefixString() + msg);
	}

}
