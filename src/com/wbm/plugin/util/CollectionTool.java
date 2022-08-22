package com.wbm.plugin.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class CollectionTool {
	/**
	 * Removes not necessary keys in overKeyTarget from minKeyTarget <br>
	 * <br>
	 * [IMPORTANT] Must be called before {@link #restoreMissedKeys(Map, Map)} if you
	 * use {@link #restoreMissedKeys(Map, Map)}<br>
	 * [IMPORTANT] Must use with Map<String, Object><br>
	 * 
	 * @param overKeyTarget Target which has to be removed necessary keys
	 * @param minKeyTarget  Target which has minimun keys for necessary
	 */
	@SuppressWarnings("unchecked")
	public static void removeNotNecessaryKeys(Map<String, Object> overKeyTarget, Map<String, Object> minKeyTarget) {
		// Use to avoid void ConcurrentModificationException
		List<String> notNecessaryKeyList = new ArrayList<>();

		for (Entry<String, Object> entry : overKeyTarget.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			// save not necessary key to list
			if (!minKeyTarget.containsKey(key)) {
				notNecessaryKeyList.add(key);
			}

			// for Map value
			if (value instanceof Map && minKeyTarget.get(key) instanceof Map) {
				removeNotNecessaryKeys((Map<String, Object>) value, (Map<String, Object>) minKeyTarget.get(key));
			}
		}

		// remove not necessary key
		notNecessaryKeyList.forEach(overKeyTarget::remove);
	}

	/**
	 * Restores missed keys in missedKeyTarget from fullKeyTarget<br>
	 * <br>
	 * [IMPORTANT] Must be called after {@link #removeNotNecessaryKeys(Map, Map)} if
	 * you use {@link #removeNotNecessaryKeys(Map, Map)}<br>
	 * [IMPORTANT] Must use with Map<String, Object><br>
	 * 
	 * @param missedKeyTarget Target which needs to restore missed keys
	 * @param fullKeyTarget   Target which has full keys
	 */
	@SuppressWarnings("unchecked")
	public static void restoreMissedKeys(Map<String, Object> missedKeyTarget, Map<String, Object> fullKeyTarget) {
		// restore "missedTarget" < "fullTarget"
		for (Entry<String, Object> entry : fullKeyTarget.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			// restore missed key
			if (!missedKeyTarget.containsKey(key)) {
				missedKeyTarget.put(key, value);
			}

			// for Map value
			if (value instanceof Map && missedKeyTarget.get(key) instanceof Map) {
				restoreMissedKeys((Map<String, Object>) missedKeyTarget.get(key), (Map<String, Object>) value);
			}
		}

	}

	/**
	 * Sync map keys order<br>
	 * <br>
	 * Don't have to sync to inner Map, because outer map already has them<br>
	 * <br>
	 * [IMPORTANT] Must be called every time, cause for some updates<br>
	 * [IMPORTANT] Must use with Map<String, Object><br>
	 * 
	 * @param unorderedMap Map which has unordered keys
	 * @param orderedMap   Map which has ordered keys
	 */
	public static void syncKeyOrder(Map<String, Object> unorderedMap, Map<String, Object> orderedMap) {
		Map<String, Object> unorderedCopyMap = new LinkedHashMap<>(unorderedMap);
		unorderedMap.clear();

		orderedMap.keySet().forEach(key -> {
			unorderedMap.put(key, unorderedCopyMap.get(key));
		});
	}

	public static <E> Optional<E> random(Collection<E> c) {
		return c.stream().skip((int) (Math.random() * c.size())).findFirst();
	}
}
