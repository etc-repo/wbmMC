package com.wbm.plugin.util;

public class WbmMC {
	// API 테스트로 객체 가져와보기 (Singleton 사용)
	private static WbmMC instance;

	private WbmMC() {
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

}
