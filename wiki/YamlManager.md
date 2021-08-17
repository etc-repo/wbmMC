# 설명
- 데이터 관리를 도와주는 도구
- `Serial`, `json` 방식의 도구도 존재함 (마인크래프트에서는 `yaml`이 주로 쓰이고, serial관련 작업이 간편함)



# 구조
## YamlManager
- YamlMember 관리 클래스
- YamlMember를 등록시켜서 사용하는 구조
- YamlMember클래스 1개당 1개의 파일을 사용해서 저장되는 구조

## YamlMember
- 데이터 관리 클래스
- 데이터를 클래스 내부에서 편하게 관리 가능

## YamlHelper
- 데이터 관련 도움 클래스




# 사용법
## 1. YamlManager 생성
```java
public class TestMain extends JavaPlugin{
  YamlManager yamlManager;
	@Override
	public void onEnable() {
		super.onEnable();
    this.yamlManager = new YamlManager(this.getDataFolder());
	}
}
```

## 2. 데이터 관리할 클래스 생성
- `YamlMember` 구현
```java
class DataManager implements YamlMember {
	private YamlManager yamlManager;

	@Override
	public void setData(YamlManager yamlM, FileConfiguration config) {
		this.yamlManager = yamlM;
	}

	@Override
	public String getFileName() {
		return "data.yml";
	}

	@Override
	public void reload() {
		this.yamlManager.reload(this);
	}
}
```

## 3. 저장할 데이터 setData()와 연결
### 3.1 단순한 형태의 저장
```java
class DataManager implements YamlMember {
	private YamlManager yamlManager;
	private int a;
	private int b;
	private int c;

	public DataManager() {
	}

	@Override
	public void setData(YamlManager yamlM, FileConfiguration config) {
		this.yamlManager = yamlM;
		Set<String> keys = config.getKeys(false);
		for (String key : keys) {
			int value = config.getInt(key);
			switch (key) {
			case "a":
				this.a = value;
				break;
			case "b":
				this.b = value;
				break;
			case "c":
				this.c = value;
				break;
			}
		}
	}

	@Override
	public String getFileName() {
		return "data.yml";
	}

	@Override
	public void reload() {
		this.yamlManager.reload(this);
	}
}
```
### 3.2 객체 형태 저장
- Yaml은 `<String, Object>` 형태로만 데이터를 저장함
- `setData()`에서 일정 작업 필요
- `setData()`에서 내부객체까지 안전하게 변환해주는 `YamlHelper.ObjectToMap()` 사용해서 desirialize 작업
```java
class DataManager implements YamlMember {
	private YamlManager yamlManager;
	private Map<String, Object> data;

	public DataManager() {
		this.data = new HashMap<>();
	}

	@Override
	public void setData(YamlManager yamlM, FileConfiguration config) {
		this.yamlManager = yamlM;
		
		// data 섹션이 존재할 경우 this.data로 data load
		if (config.isSet("data")) {
			this.data = YamlHelper.ObjectToMap(config.getConfigurationSection("data"));
		}

		// config의 data 섹션에 this.data 동기화
		config.set("data", this.data);
	}

	@Override
	public String getFileName() {
		return "data.yml";
	}

	@Override
	public void reload() {
		this.yamlManager.reload(this);
	}
}
```
## 4. YamlMember 등록
- 등록을 해줘야 데이터 save/load가 가능함
- 등록을 하는 순간에 데이터를 load함
```java
public class TestMain extends JavaPlugin{
  YamlManager yamlManager;
	@Override
	public void onEnable() {
		super.onEnable();
    this.yamlManager = new YamlManager(this.getDataFolder());
		DataManager dataManager = new DataManager();
		yamlManager.registerMember(dataManager);
	}
}
```

## 5. 프로그램이 끝날 때
- e.g. 마인크래프트 서버 끝나는 `onDisable()`에서 수행
```java
public class TestMain extends JavaPlugin{
  YamlManager yamlManager;
	@Override
	public void onEnable() {
		super.onEnable();
    this.yamlManager = new YamlManager(this.getDataFolder());
		DataManager dataManager = new DataManager();
		yamlManager.registerMember(dataManager);
	}
  
  @Override
  public void onDisable() {
    super.onDisable();

    // 데이터 저장
    this.yamlManager.saveAllData();
  }
}
```


## 기타
- YamlManager의 기타 작업 가능
- `save(YamlMember member)`: member의 데이터 저장(file에 실제로 저장됨)
- `saveAllData()`: 모든 member의 데이터 저장
- `reload(YamlMember mmeber)`: member의 데이터 리로드(YamlMember의 `reload()`가 실행됨)
- `reloadAllData()`: 모든 member의 데이터 리로드




# 예시
- [SymbioWorld의 ReportCycleWorkManager](https://github.com/worldbiomusic/SymbioWorld/blob/main/src/com/github/symbioworld/managers/report/ReportCycleWorkManager.java)
- [MiniGameMaker의 MiniGameDataManager](https://github.com/worldbiomusic/MiniGameMaker/blob/main/src/com/wbm/minigamemaker/manager/MiniGameDataManager.java)

# 기타
- [마인크래프트 커스텀 객체 serialization 방법](https://github.com/worldbiomusic/Blog/blob/main/Minecraft/plugin/making/customYamlObject.md)




