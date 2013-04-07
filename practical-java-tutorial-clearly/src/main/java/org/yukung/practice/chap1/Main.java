package org.yukung.practice.chap1;

public class Main {

	public static void main(String[] args) {
		String s1 = "スッキリJava";
		String s2 = "Java";
		String s3 = "java";
		if (s2.equals(s3)) {
			System.out.println("等しい");
		}
		if (s2.equalsIgnoreCase(s3)) {
			System.out.println("ケース無視なら等しい");
		}
		System.out.println(s1.length());
		if (s1.isEmpty()) {
			System.out.println("s1は空文字");
		}

		String s4 = "Java and JavaScript";
		if (s4.contains("Java")) {
			System.out.println("含んでる");
		}
		if (s4.endsWith("Java")) {
			System.out.println("Javaで終わってる");
		}

		System.out.println(s4.indexOf("Java"));
		System.out.println(s4.lastIndexOf("Java"));

		String s5 = "Java Programming";

		System.out.println("3文字目以降は" + s5.substring(3));
		System.out.println("3〜8文字は" + s5.substring(3, 8));

		System.out.println(s5.toUpperCase());
		System.out.println(s5.toLowerCase());
		System.out.println(s5.trim());
		System.out.println(s5.replace(' ', '-'));
	}
}
