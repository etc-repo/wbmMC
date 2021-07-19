package com.wbm.plugin.util.data.yaml;

import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;

public class YamlHelper {
	// 참고:
	// https://github.com/worldbiomusic/Blog/blob/main/Minecraft/plugin/making/YAML.md
	public static Map<String, Object> getChildMap(Map<String, Object> map, String key) {
		return ObjectToMap(map.get(key));
	}

	/*
	 * Map의 자식이 Map일 떄 변환하기 위해 사용
	 *  
	 * - 첫번째 map을 ObjectToMap으로 변환하면 자식들의 ConfigurationSection도 자동으로 Map<String, Object>로 변환되므로, 형변환(Map<String, Object>)해서 사용하면 됨
	 * 
	 * ex) Map<String, Object> map =
	 * YamlHelper.ObjectToMap(config.getConfigurationSection("minigames"));
	 * 
	 * 
	 */
	public static Map<String, Object> ObjectToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		if (obj instanceof ConfigurationSection) {
			// 내부 자식들중 Map형태가 있으면 모두 Map으로 형변환 시킴
			ConfigurationSection section = (ConfigurationSection) obj;
			for (String key : section.getKeys(false)) {
				if (section.isConfigurationSection(key)) {
					Object childObj = section.getConfigurationSection(key);
					section.set(key, ObjectToMap(childObj)); // recursive
				}
			}

			return section.getValues(true);
		}
		return null;
	}

}
