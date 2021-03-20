package com.wbm.plugin.util;

import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

public class PlayerTool {
	public static Collection<? extends Player> onlinePlayers() {
		return Bukkit.getOnlinePlayers();
	}

	public static int onlinePlayersCount() {
		return onlinePlayers().size();
	}

	public static void heal(Player p) {
		p.setHealth(20);
		p.setFoodLevel(20);
	}

	public static void removeAllState(JavaPlugin plugin, Player p) {
		// unhide
		unhidePlayerFromEveryone(plugin, p);
		// set glow off
		p.setGlowing(false);
		// 모든 포션효과 제거
		for (PotionEffect potion : p.getActivePotionEffects()) {
			p.removePotionEffect(potion.getType());
		}
	}

	public static void setHungry(Player p, int amount) {
		p.setFoodLevel(amount);
	}

	public static void hidePlayerFromAnotherPlayer(JavaPlugin plugin, Player hideTarget, Player anotherPlayer) {
		anotherPlayer.hidePlayer(plugin, hideTarget);
	}

	public static void hidePlayerFromOtherPlayers(JavaPlugin plugin, Player hideTarget, List<Player> others) {
		for (Player other : others) {
			hidePlayerFromAnotherPlayer(plugin, hideTarget, other);
		}
	}

	public static void hidePlayerFromEveryone(JavaPlugin plugin, Player hideTarget) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			hidePlayerFromAnotherPlayer(plugin, hideTarget, all);
		}
	}

	public static void unhidePlayerFromAnotherPlayer(JavaPlugin plugin, Player unhideTarget, Player anotherPlayer) {
		anotherPlayer.showPlayer(plugin, unhideTarget);
	}

	public static void unhidePlayerFromOtherPlayers(JavaPlugin plugin, Player unhideTarget, List<Player> others) {
		for (Player other : others) {
			unhidePlayerFromAnotherPlayer(plugin, unhideTarget, other);
		}
	}

	public static void unhidePlayerFromEveryone(JavaPlugin plugin, Player unhideTarget) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			unhidePlayerFromAnotherPlayer(plugin, unhideTarget, all);
		}
	}

	public static void playSound(Player p, Sound sound) {
		p.playSound(p.getLocation(), sound, 10, 1);
	}

	public static void playSoundToEveryone(Sound sound) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			playSound(p, sound);
		}
	}

	public static void removeAllPotionEffects(Player p) {
		for (PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
	}

}
