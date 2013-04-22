package org.yukung.perfect_java.chap5;

import java.util.Comparator;

public class LocalInnerClass {
	
	void doit() {
		class LocalClass {
			
			void doit() {
				System.out.println("doit");
			}
		}
		
		LocalClass lc = new LocalClass();
		lc.doit();
	}
	
	/*
	 * ローカル内部クラスを使う目的でよく使われる例。
	 */
	Comparator<String> getComparator() {
		class StringLengthComparator implements Comparator<String> {
			
			@Override
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
			
		}
		return new StringLengthComparator();
	}
}
