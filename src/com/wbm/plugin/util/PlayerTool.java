package com.wbm.plugin.util;

import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import com.wbm.plugin.Main;

public class PlayerTool {
	public static Collection<? extends Player> onlinePlayers() {
		return Bukkit.getOnlinePlayers();
	}

	public static int onlinePlayersCount() {
		return onlinePlayers().size();
	}
	
	public static void makePureState(Player p) {
		// 1. 체력 회복
		// 2. 배고픔 회복
		// 3. hide 제거
		// 4. glowing 제거
		// 5. 포션 효과 제거
		heal(p);
		removeAllState(p);
	}

	public static void heal(Player p) {
		p.setHealth(20);
		p.setFoodLevel(20);
	}

	public static void removeAllState(Player p) {
		// unhide
		unhidePlayerFromEveryone(p);
		// set glow off
		p.setGlowing(false);
		// 모든 포션효과 제거
		removeAllPotionEffects(p);
	}

	public static void setHungry(Player p, int amount) {
		p.setFoodLevel(amount);
	}

	public static void hidePlayerFromAnotherPlayer(Player hideTarget, Player anotherPlayer) {
		anotherPlayer.hidePlayer(Main.getInstance(), hideTarget);
	}

	public static void hidePlayerFromOtherPlayers(Player hideTarget, List<Player> others) {
		for (Player other : others) {
			hidePlayerFromAnotherPlayer(hideTarget, other);
		}
	}

	public static void hidePlayerFromEveryone(Player hideTarget) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			hidePlayerFromAnotherPlayer(hideTarget, all);
		}
	}

	public static void unhidePlayerFromAnotherPlayer(Player unhideTarget, Player anotherPlayer) {
		anotherPlayer.showPlayer(Main.getInstance(), unhideTarget);
	}

	public static void unhidePlayerFromOtherPlayers(Player unhideTarget, List<Player> others) {
		for (Player other : others) {
			unhidePlayerFromAnotherPlayer(unhideTarget, other);
		}
	}

	public static void unhidePlayerFromEveryone(Player unhideTarget) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			unhidePlayerFromAnotherPlayer(unhideTarget, all);
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
