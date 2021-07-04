package com.wbm.plugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.wbm.plugin.util.BroadcastTool;
import com.wbm.plugin.util.data.yaml.YamlManager;
import com.wbm.plugin.util.data.yaml.YamlMember;

public class Main extends JavaPlugin {
	private static Main instance;
	YamlManager yamlM;

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
		BroadcastTool.info(ChatColor.GREEN + "wbmMC ON");

//		this.config();
//		this.customConfig();

//		this.yamlM();

//		this.customConfig2();

	}

	private void yamlM() {
		this.yamlM = new YamlManager(this.getDataFolder());

		Test t = new Test();
		this.yamlM.registerMember(t);
		t.writeData();
		t.printData();

		t.updateData();
		this.yamlM.saveAllData();
		t.reload();
		t.printData();

		this.yamlM.saveAllData();
	}

	@SuppressWarnings("unchecked")
	void config() {
		// 파일 자동 생성(getDataFolder() + "config.yml")
		this.saveConfig();

		// 자동 생성 x (플러그인 만들 때 안에 넣어둔 config.yml을 그대로 사용하겠다는 의미)
//		this.saveDefaultConfig();
		Map<String, Object> map1 = new HashMap<>();
		map1.put("number", 1);
		map1.put("string", "worldbiomusic");
		map1.put("loc1", new Location(Bukkit.getWorld("world"), 0, 0, 0));

		Map<String, Object> map2 = new HashMap<>();
		map1.put("map2", map2);
		map2.put("number", 2);
		map2.put("loc2", new Location(Bukkit.getWorld("world"), 1, 2, 3));

		this.getConfig().set("first", map1);
		this.getConfig().set("test", "wbm");
		this.saveConfig();

		Map<String, Object> deserialMap = (Map<String, Object>) this.getConfig().get("first");
		System.out.println("number: " + deserialMap.get("number"));

		Map<String, Object> deserialMap2 = (Map<String, Object>) deserialMap.get("map2");
		System.out.println("map2's loc" + deserialMap2.get("loc2"));

	}

	void customConfig() {
		try {
			File customFile = new File(this.getDataFolder(), "custom.yml");
			// folder없으면 생성
			File parentFile = customFile.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdir();
			}

			// file 없으면 생성
			if (!customFile.exists()) {
				customFile.createNewFile();
			}

			// 변수 삽입
			Map<String, Object> map1 = new HashMap<>();
			map1.put("number", 1);
			map1.put("string", "worldbiomusic");
			map1.put("loc1", new Location(Bukkit.getWorld("world"), 0, 0, 0));

			Map<String, Object> map2 = new HashMap<>();
			map1.put("map2", map2);
			map2.put("number", 2);
			map2.put("loc2", new Location(Bukkit.getWorld("world"), 1, 2, 3));

			FileConfiguration configM = YamlConfiguration.loadConfiguration(customFile);
			configM.set("map1", map1);

			// FileConfiguration 으로 파일 저장
			configM.save(customFile);
			// 또는 밑의것으로도 파일 저장 가능
//			his.getConfig().save(customFile);

			// 변수 출력 확인
			File f = new File(customFile.toString());
			FileConfiguration f2 = YamlConfiguration.loadConfiguration(f);

			ConfigurationSection sectionMap1 = f2.getConfigurationSection("map1");
			Map<String, Object> deserialMap = sectionMap1.getValues(true);
			System.out.println("number: " + deserialMap.get("number"));

//			ConfigurationSection sectionMap2 = f2.getConfigurationSection("map1.map2");
//			Map<String, Object> deserialMap2 = sectionMap2.getValues(true);
			Map<String, Object> deserialMap2 = ((ConfigurationSection) deserialMap.get("map2")).getValues(true);

			System.out.println("map2's loc2: " + deserialMap2.get("loc2"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void customConfig2() {
		try {
			File customFile = new File(this.getDataFolder(), "custom2.yml");
			// folder없으면 생성
			File parentFile = customFile.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdir();
			}

			// file 없으면 생성
			if (!customFile.exists()) {
				customFile.createNewFile();
			}

			// 변수 삽입
			Map<String, Object> map1 = new HashMap<>();
			map1.put("number", 1);
			map1.put("string", "worldbiomusic");
			map1.put("loc1", new Location(Bukkit.getWorld("world"), 0, 0, 0));

			FileConfiguration configM = YamlConfiguration.loadConfiguration(customFile);
			for (Entry<String, Object> entry : map1.entrySet()) {
				configM.set(entry.getKey(), entry.getValue());
			}

			// FileConfiguration 으로 파일 저장
			configM.save(customFile);
			// 또는 밑의것으로도 파일 저장 가능
//			his.getConfig().save(customFile);

			// 변수 출력 확인
			File f = new File(customFile.toString());
			FileConfiguration f2 = YamlConfiguration.loadConfiguration(f);

			ConfigurationSection sectionMap1 = f2.getConfigurationSection("");
			Map<String, Object> deserialMap = sectionMap1.getValues(true);
//			System.out.println("number: " + deserialMap.get("number"));
			for (Entry<String, Object> entry : deserialMap.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onDisable() {
		BroadcastTool.warn(ChatColor.RED + "wbmMC OFF");
	}

}

class Test implements YamlMember {
	YamlManager yamlM;
	FileConfiguration config;

	void writeData() {
		config.set("a", 1);
	}

	void updateData() {
		config.set("a", 2);
	}

	void printData() {
		System.out.println("a: " + config.get("a"));
	}

	void reload() {
		yamlM.reload(this);
	}

	@Override
	public void setData(YamlManager yamlM, FileConfiguration config) {
		this.yamlM = yamlM;
		this.config = config;
	}

	@Override
	public String getFileName() {
		return "wbmMC1/wbmMC2/customConfig.yml";
	}

}