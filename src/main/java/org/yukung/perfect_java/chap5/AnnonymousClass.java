package org.yukung.perfect_java.chap5;

import java.util.Comparator;

public class AnnonymousClass {
	
	/*
	 * 無名（匿名）クラスの例。
	 */
	Comparator<String> getComparator() {
		return new Comparator<String>() {
			
			@Override
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
			
		};
	}
}
