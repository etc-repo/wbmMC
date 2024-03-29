package com.wbm.plugin.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortTool {
	// Map의 value(Integer제한)기준 내림차순 entry 반환
	public static <T1> List<Entry<T1, Integer>> getDescendingSortedList(Map<T1, Integer> rankData) {
		// clone entry set
		List<Entry<T1, Integer>> list = new ArrayList<>(rankData.entrySet());

		// sort
		Collections.sort(list, new Comparator<Entry<T1, Integer>>() {
			@Override
			public int compare(Entry<T1, Integer> o1, Entry<T1, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});

		return list;
	}

	// Map의 value(Integer제한)기준 오름차순 entry 반환
	public static <T1> List<Entry<T1, Integer>> getAscendingSortedList(Map<T1, Integer> rankData) {
		// clone entry set
		List<Entry<T1, Integer>> list = new ArrayList<>(rankData.entrySet());

		// sort
		Collections.sort(list, new Comparator<Entry<T1, Integer>>() {
			@Override
			public int compare(Entry<T1, Integer> o1, Entry<T1, Integer> o2) {
				return o1.getValue() - o2.getValue();
			}
		});

		return list;
	}
}
