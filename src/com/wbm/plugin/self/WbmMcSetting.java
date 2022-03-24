package com.wbm.plugin.self;

import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

import com.wbm.plugin.util.data.yaml.YamlHelper;
import com.wbm.plugin.util.data.yaml.YamlManager;
import com.wbm.plugin.util.data.yaml.YamlMember;

public class WbmMcSetting implements YamlMember {

	private Map<String, Object> settings;

	public WbmMcSetting() {
		this.settings = new LinkedHashMap<>();
		initSettingData();
	}

	private void initSettingData() {
		if (!this.settings.containsKey("check-update")) {
			this.settings.put("check-update", true);
		}
		Settings.CHECK_UPDATE = (boolean) this.settings.get("check-update");
	}

	@Override
	public void setData(YamlManager yamlManager, FileConfiguration config) {
		// sync config setting with variable setting
		if (config.isSet("settings")) {
			this.settings = YamlHelper.ObjectToMap(config.getConfigurationSection("settings"));
		}
		config.set("settings", this.settings);

		// check setting has basic values
		this.initSettingData();
	}

	@Override
	public String getFileName() {
		return "settings.yml";
	}

}
