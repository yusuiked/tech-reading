package org.yukung.perfect_java.chap6;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class MyNavigableMap {
	
	public static void main(String[] args) {
		NavigableMap<String, String> map = new TreeMap<String, String>();
		map.put("rei", "zero");
		map.put("ichi", "one");
		map.put("ni", "two");
		map.put("san", "three");
		
		Map.Entry<String, String> entry = map.ceilingEntry("ic");
		if (entry != null) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
}
