package org.yukung.practice.chap2;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetSample {

	public static void main(String[] args) {
		HashSetSample();
		TreeSetSample();
	}

	private static void TreeSetSample() {
		Set<String> colors = new TreeSet<String>();
		colors.add("赤"); colors.add("青"); colors.add("黄");
		for (String color : colors) {
			System.out.println(color);
		}
	}

	private static void HashSetSample() {
		Set<String> colors = new HashSet<String>();
		colors.add("赤"); colors.add("青"); colors.add("黄");
		for (String color : colors) {
			System.out.println(color);
		}
	}

}
