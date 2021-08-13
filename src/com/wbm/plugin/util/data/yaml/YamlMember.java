package com.wbm.plugin.util.data.yaml;

import org.bukkit.configuration.file.FileConfiguration;

public interface YamlMember {
//	 FileConfiguration에서 바꾸면 메모리상에서 바뀌므로 반환할 필요 없이 YamlManager에서 저장만 하면 됨
//	public Object getData();

	// YamlManager에서 파일 데이터 가져와서 설정
	// YamlManager인자는 가져가서 reload() 같은것에 사용가능(자동으로 config파일에 적용됨)
	// FileConfiguration인자는 각 클래스에서 사용하는 config파일
	public void setData(YamlManager yamlM, FileConfiguration config);

	// 파일 예시: "A/B/C/D.yml" (구분자: "/")
	public String getFileName();

	// setData()가 다시 실행됨 (현재 서버 데이터는 저장하지 않는 한 소멸됨)
	// 상황에 따라서 reload() 후 서버 데이터에 해야 할 작업이 있을 수 도 있음
	public void reload();
}
