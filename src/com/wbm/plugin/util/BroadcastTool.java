package com.wbm.plugin.util;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastTool {
	// broadcast해줄때 앞에 "[serverName] " 붙여주는 기능

	// sendMessage
	public static void sendMessage(Player p, String msg) {
		p.sendMessage(msg);
	}

	// sendMessage
	public static void sendMessage(CommandSender sender, String msg) {
		sender.sendMessage(msg);
	}

	public static void sendMessage(List<Player> many, String msg) {
		for (Player p : many) {
			sendMessage(p, msg);
		}
	}

	public static void sendMessageToEveryone(String msg) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			sendMessage(p, msg);
		}
	}

	public static void sendMessageToEveryoneWithoutPrefix(String msg) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(msg);
		}
	}

	// sendTitle
	public static void sendTitle(Player p, String title, String subTitle, double fadeIn, double stay, double fadeOut) {
		p.sendTitle(title, subTitle, (int) (20 * fadeIn), (int) (20 * stay), (int) (20 * fadeOut));
	}

	public static void sendTitle(Player p, String title, String subTitle) {
		p.sendTitle(title, subTitle, 20 * 1, 20 * 3, 20 * 1);
	}

	public static void sendTitle(Player[] players, String title, String subTitle) {
		for (Player p : players) {
			p.sendTitle(title, subTitle, 20 * 1, 20 * 3, 20 * 1);
		}
	}

	public static void sendTitle(Player[] players, String title, String subTitle, double fadeIn, double stay,
			double fadeOut) {
		for (Player p : players) {
			BroadcastTool.sendTitle(p, title, subTitle, fadeIn, stay, fadeOut);
		}
	}

	public static void sendTitle(List<Player> players, String title, String subTitle) {
		for (Player p : players) {
			p.sendTitle(title, subTitle, 20 * 1, 20 * 3, 20 * 1);
		}
	}

	public static void sendTitle(List<Player> players, String title, String subTitle, double fadeIn, double stay,
			double fadeOut) {
		for (Player p : players) {
			BroadcastTool.sendTitle(p, title, subTitle, fadeIn, stay, fadeOut);
		}
	}

	public static void sendTitleToEveryone(String title, String subTitle) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendTitle(title, subTitle, 20 * 1, 20 * 3, 20 * 1);
		}
	}

	public static void sendTitleToEveryone(String title, String subTitle, double fadeIn, double stay, double fadeOut) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			sendTitle(p, title, subTitle, fadeIn, stay, fadeOut);
		}
	}

}
