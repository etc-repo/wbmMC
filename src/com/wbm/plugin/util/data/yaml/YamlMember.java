package com.wbm.plugin.util.data.yaml;

import org.bukkit.configuration.file.FileConfiguration;

public interface YamlMember {
	/*
	 * FileConfiguration에서 바꾸면 메모리상에서 바뀌므로 반환할 필요 없이 YamlManager에서 저장만 하면 됨
	YamlManager에서 파일 데이터 가져와서 설정
	YamlManager인자는 가져가서 reload() 같은것에 사용가능(자동으로 config파일에 적용됨)
	FileConfiguration인자는 각 클래스에서 사용하는 config파일
	*/
	public void setData(YamlManager yamlManager, FileConfiguration config);

	// 파일 예시: "A + File.separator + B.yml" (구분자: File.separator)
	public String getFileName();
}
