package com.wbm.plugin.util;

import java.util.Map;
import java.util.Map.Entry;

public class CollectionTool {
	@SuppressWarnings("unchecked")
	static private void restoreMissedKeys(Map<String, Object> missedTarget, Map<String, Object> fullTarget) {
		// restore "missedTarget" < "fullTarget"
		for (Entry<String, Object> entry : fullTarget.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (!missedTarget.containsKey(key)) {
				missedTarget.put(key, value);
			}

			// for Map value
			if (value instanceof Map) {
				restoreMissedKeys((Map<String, Object>) missedTarget.get(key), (Map<String, Object>) value);
			}
		}
	}
}
