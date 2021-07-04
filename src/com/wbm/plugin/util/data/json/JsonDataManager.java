package com.wbm.plugin.util.data.json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonDataManager {
	/*
	 * [설명]
	 * 
	 * json데이타 관리해주는 클래스
	 * 
	 * 모든 파일은 data/ 로 시작하는곳에서 모든 데이터 관리함
	 * 
	 * 
	 * [참고: JSON <-> Map]
	 * 
	 * map to json:
	 * https://www.delftstack.com/ko/howto/java/how-to-convert-hashmap-to-json-
	 * object-in-java/
	 * 
	 * json to map:
	 * https://www.delftstack.com/ko/howto/java/how-to-convert-json-to-map-in-java/
	 * 
	 */

	private Gson gson;

	private Map<String, JsonDataMember> members;

	private File rootDir;

//	public static void main(String[] args) {
//		JsonDataManager m = new JsonDataManager();
//
//	}
//
//	public JsonDataManager() {
//		GsonBuilder gsonBuilder = new GsonBuilder();
//
//		// Map<String, Object>에 대해서 읽을 때(deserialize) Long과 Double구별하게 하는 Adapter 등록
//		gsonBuilder.registerTypeAdapter(new TypeToken<Map<String, Object>>() {
//		}.getType(), new MapDeserializerDoubleAsIntFix());
//
////		gsonBuilder.registerTypeAdapter(new TypeToken<Object>() {
////		}.getType(), new CustomizedObjectTypeAdapter());
//
//		// gson 설정
//		this.gson = gsonBuilder.setPrettyPrinting().create();
//
//		this.testLongOrDouble();
//
//	}

	void testLongOrDouble() {
		String json = "[{\"id\":1,\"quantity\":2.3,\"name\":\"apple\"}, {\"id\":3,\"quantity\":4.7,\"name\":\"orange\"}]";
		List<Map<String, Object>> l = gson.fromJson(json, new TypeToken<List<Map<String, Object>>>() {
		}.getType());

		for (Map<String, Object> item : l)
			System.out.println(item);

		String serialized = gson.toJson(l);
		System.out.println(serialized);
	}


	public Gson getGson() {
		// Minecraft에서 Gson을 사용할 때(데이터 로드 or 데이터 저장) JsonDataManager class에서 Gson객체로 꼭
		// 사용해야 함
		// Long, Double구분을 위해서 (Map<String, Object> 만 구분 가능)
		return this.gson;
	}

	public JsonDataManager(File rootDir) {
		this.members = new HashMap<>();
		GsonBuilder gsonBuilder = new GsonBuilder();

//		// Map<String, Object>에 대해서 읽을 때(deserialize) Long과 Double구별하게 하는 Adapter 등록
//		gsonBuilder.registerTypeAdapter(new TypeToken<Map<String, Object>>() {
//		}.getType(), new MapDeserializerDoubleAsIntFix());

		// gson 설정
		this.gson = gsonBuilder.setPrettyPrinting().create();

		this.setRootDir(rootDir);

	}

	public void registerMember(JsonDataMember member) {
		this.members.put(member.getFileName(), member);
	}

	public void unregisterMember(JsonDataMember member) {
		this.members.remove(member.getFileName());
	}

	public void distributeAllData() {
		// 모든 멤버에게 데이터 배분
		for (JsonDataMember member : this.members.values()) {
			String jsonString = this.load(member.getFileName());
			member.distributeData(this.gson, jsonString);
		}
	}

	public void saveAllData() {
		// 모든 멤버의 데이터 저장
		for (JsonDataMember member : this.members.values()) {
			Object obj = member.getData();
			this.save(member.getFileName(), obj);
		}
	}

	private void setRootDir(File rootDir) {
		// data dir 생성
		this.rootDir = rootDir;
		if (!this.rootDir.exists()) {
			this.rootDir.mkdir();
		}
	}

	/*
	 * [사용법]
	 * 
	 * Player p = new Player("abcd");
	 * 
	 * dataM.save(new File("test.json"), p);
	 */
	public void save(String fileName, Object obj) {
		try {
			File f = this.resourceFile(fileName);

			Writer writer = new FileWriter(f);
			this.gson.toJson(obj, writer);

			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* [코드 참고] Gson Github: */
	// https://github.com/google/gson/blob/master/gson/src/main/java/com/google/gson/Gson.java
//	public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
//	    Object object = fromJson(json, (Type) classOfT);
//	    return Primitives.wrap(classOfT).cast(object);
//	  }

	/*
	 * [사용법]
	 * 
	 * Player p = dataM.load(new File("test.json"), Player.class);
	 */
	public <T> T load(String fileName, Class<T> classOfT) {
		/*
		 * class 자체를 json파일로 사용할 때 사용하는 메소드
		 */
		String jsonString = this.load(fileName);
		return this.gson.fromJson(jsonString, (Type) classOfT);

	}

	public String load(String fileName) {
		/*
		 * JsonObject 관련된 객체를 String으로 로드해서 사용할 때 사용하는 메소드
		 * 
		 * String 사용법: Test test = this.gson.fromJson("String", Test.class);
		 */
		String jsonString = null;
		try {
			File f = this.resourceFile(fileName);
			if (!f.exists()) {
				return null;
			}

			Reader reader = new FileReader(f);

			Object obj = this.gson.fromJson(reader, Object.class);
			jsonString = this.gson.toJson(obj);

			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	public File resourceFile(String fileName) {
		return new File(this.rootDir, fileName);
	}

	public boolean exists(String fileName) {
		return this.resourceFile(fileName).exists();
	}
}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
