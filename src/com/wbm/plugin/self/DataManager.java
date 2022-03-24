package com.wbm.plugin.self;

import com.wbm.plugin.WbmMC;
import com.wbm.plugin.util.data.yaml.YamlManager;
import com.wbm.plugin.util.data.yaml.YamlMember;

public class DataManager {
	private YamlManager yamlManager;

	public DataManager() {
		this.yamlManager = new YamlManager(WbmMC.getInstance().getDataFolder());
		registerDefaultMembers();
	}
	
	private void registerDefaultMembers() {
		registerYamlMember(new WbmMcSetting());
	}

	public void registerYamlMember(YamlMember member) {
		this.yamlManager.registerMember(member);
	}
}
