package com.wbm.plugin.util.data.json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
	 */

	Gson gson;

	Map<String, JsonDataMember> members;

	File rootDir;

	public JsonDataManager(File rootDir) {
		this.members = new HashMap<>();
		this.gson = new GsonBuilder().setPrettyPrinting().create();

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
			member.distributeData(jsonString);
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
