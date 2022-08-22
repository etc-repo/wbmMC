package com.wbm.plugin.util;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;

public class Msgs {
	public static void msg(CommandSender sender, String msg) {
		sender.sendMessage(msg);
	}

	public static void msg(Collection<? extends CommandSender> senders, String msg) {
		senders.forEach(p -> msg(p, msg));
	}

	/**
	 * Send message to all the online players
	 * 
	 * @param msg Message to send
	 */
	public static void msgs(String msg) {
		msg(Bukkit.getOnlinePlayers(), msg);
	}

	//
	//
	//
	public static void msg(Player sender, BaseComponent compo) {
		sender.spigot().sendMessage(compo);
	}

	public static void msg(Player sender, ChatMessageType msgType, BaseComponent compo) {
		sender.spigot().sendMessage(msgType, compo);
	}

	public static void msg(Collection<? extends Player> senders, BaseComponent compo) {
		senders.forEach(s -> msg(s, compo));
	}

	public static void msg(Collection<? extends Player> senders, ChatMessageType msgType, BaseComponent compo) {
		senders.forEach(s -> msg(s, msgType, compo));
	}

	public static void msgs(BaseComponent compo) {
		msg(Bukkit.getOnlinePlayers(), compo);
	}

	public static void msgs(ChatMessageType msgType, BaseComponent compo) {
		msg(Bukkit.getOnlinePlayers(), msgType, compo);
	}

	//
	//
	//
	public static void title(Player p, String title, String subTitle) {
		p.sendTitle(title, subTitle, 2, 16, 2);
	}

	public static void title(Player p, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
		p.sendTitle(title, subTitle, fadeIn, stay, fadeOut);
	}

	public static void title(Collection<? extends Player> players, String title, String subTitle) {
		players.forEach(p -> title(p, title, subTitle));
	}

	public static void title(Collection<? extends Player> players, String title, String subTitle, int fadeIn, int stay,
			int fadeOut) {
		players.forEach(p -> title(p, title, subTitle, fadeIn, stay, fadeOut));

	}

	/**
	 * Send titles to all the online players
	 * 
	 * @param title    Title to send
	 * @param subTitle Subtitle to send
	 */
	public static void titles(String title, String subTitle) {
		title(Bukkit.getOnlinePlayers(), title, subTitle);
	}

	public static void titles(String title, String subTitle, int fadeIn, int stay, int fadeOut) {
		title(Bukkit.getOnlinePlayers(), title, subTitle, fadeIn, stay, fadeOut);
	}

}
