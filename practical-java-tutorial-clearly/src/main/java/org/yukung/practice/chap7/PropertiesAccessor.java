package org.yukung.practice.chap7;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesAccessor {

	public Map<String, String> read() {
		Map<String, String> map = new HashMap<>();
		try (InputStream in = getClass().getResourceAsStream("data.properties")) {
			Properties prop = new Properties();
			prop.load(in);
			map.put("name", prop.getProperty("name"));
			map.put("age", prop.getProperty("age"));
			map.put("sex", prop.getProperty("sex"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public void write(Map<String, String> map) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/org/yukung/practice/chap6/data.properties", true))) {
			Properties prop = new Properties();
			String country = map.get("country");
			prop.setProperty("country", country);
			prop.store(bw, "追記");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		PropertiesAccessor accessor = new PropertiesAccessor();
		Map<String, String> map = accessor.read();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.printf("%s : %s%n", entry.getKey(), entry.getValue());
		}

		Map<String,String> map2 = new HashMap<>();
		map2.put("country", "Japan");
		accessor.write(map2);
	}
}
