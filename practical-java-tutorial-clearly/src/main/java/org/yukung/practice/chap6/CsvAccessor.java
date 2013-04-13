package org.yukung.practice.chap6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CsvAccessor {

	public List<Map<String, String>> read(File file) {
		List<Map<String, String>> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				Map<String,String> map = new LinkedHashMap<>();
				for (int i = 0; i < tokens.length; i++) {
					map.put(String.valueOf(i), tokens[i]);
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		CsvAccessor accessor = new CsvAccessor();
		List<Map<String,String>> list = accessor.read(new File("sample1.csv"));
		for (Map<String, String> map : list) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				System.out.printf("%s列目: %s%n", entry.getKey(), entry.getValue());
			}
			System.out.println("----------------------------");
		}
	}

}
