package org.yukung.practice.chap6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
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

	public void write(File file, List<List<String>> data) {
		List<String> lines = new ArrayList<>();
		for (List<String> row : data) {
			StringBuilder sb = new StringBuilder();
			for (String column : row) {
				sb.append(column);
				sb.append(",");
			}
			sb.setCharAt(sb.lastIndexOf(","), System.lineSeparator().charAt(0));
			lines.add(sb.toString());
		}
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			for (String line : lines) {
				bw.write(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

		List<List<String>> data = new ArrayList<>();
		List<String> lines = Arrays.asList("abc", "ほげふが", "foobar");
		for (int i = 0; i < 10; i++) {
			data.add(lines);
		}
		accessor.write(new File("sample2.csv"), data);
	}

}
