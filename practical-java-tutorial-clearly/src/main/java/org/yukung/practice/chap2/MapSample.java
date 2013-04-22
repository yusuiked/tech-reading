package org.yukung.practice.chap2;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapSample {
	public static void main(String[] args) {
		Map<String,Integer> prefs = new HashMap<String, Integer>();
		prefs.put("京都府", 255);
		prefs.put("東京都", 1261);
		prefs.put("熊本県", 182);
		for (Entry<String, Integer> pref : prefs.entrySet()) {
			System.out.println(pref.getKey() + ":" + pref.getValue());
		}
	}
}
