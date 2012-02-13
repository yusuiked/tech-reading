package org.yukung.perfect_java.chap6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FunctionalCollection {
	
	/*
	 * n文字より長い文字列の要素を削除（破壊的なメソッド）
	 */
	void rejectLongString(List<String> list, int n) {
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			String s = iterator.next();
			if (s.length() > n) {
				iterator.remove();
			}
		}
	}
	
	/*
	 * n文字より長い文字列の要素を削除（関数的なメソッド）
	 */
	List<String> rejectLongStringFunctional(List<String> list, int n) {
		List<String> output = new ArrayList<String>();
		for (String s : list) {
			if (s.length() > n) {
				;
			} else {
				output.add(s);
			}
		}
		return output;
	}
}
