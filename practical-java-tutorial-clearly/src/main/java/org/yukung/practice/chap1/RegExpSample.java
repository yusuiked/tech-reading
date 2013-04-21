package org.yukung.practice.chap1;

public class RegExpSample {

	public static void main(String[] args) {
		String s = "Java";
		System.out.println(s.matches("Java"));
		System.out.println(s.matches("J.va"));
		System.out.println(s.matches("Ja*va"));

		s = "abc,def:ghi";
		String[] words = s.split("[,:]");
		for (String word : words) {
			System.out.println(word);
		}

		String w = s.replaceAll("[beh]", "X");
		System.out.println(w);
	}

}
