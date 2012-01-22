package org.yukung.perfect_java.chap3;

public class BooleanSample {
	
	/*
	 * フラグ変数が多い場合、メソッドが長すぎることを疑うべき。
	 * フラグ変数を使った条件分岐がだらだらと続くコードは典型。
	 */
	public static void main(String[] args) {
		boolean isContains = isContains("hoge", "ge");
	}
	
	private static boolean isContains(String haystack, String needle) {
		//冗長なコード例
		boolean found; // 未初期化のローカル変数が残るのも良くない点
		if (haystack.indexOf(needle) >= 0) {
			found = true;
		} else {
			found = false;
		}
		return found;
	}
	
	private static boolean isContains2(String haystack, String needle) {
		//まだ冗長なコード
		if (haystack.indexOf(needle) >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isContains3(String haystack, String needle) {
		// 冗長さを完全に排した書き方（ただし、メソッドにする意味も同時に失われた）
		return haystack.indexOf(needle) >= 0;
	}
}
