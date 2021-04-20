package com.wbm.plugin.util;

public class WbmMC {
	// API 테스트로 객체 가져와보기 (Singleton pattern 사용)
	private static WbmMC instance;
	String a, b, c;

	private WbmMC() {
		this.a = "AAA";
		this.b = "BBB";
		this.c = "CCC";
	}

	public static WbmMC getInstance() {
		if (instance == null) {
			synchronized (WbmMC.class) {
				if (instance == null) {
					instance = new WbmMC();
				}
			}
		}
		return instance;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

}
