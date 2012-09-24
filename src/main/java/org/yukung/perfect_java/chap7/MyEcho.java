package org.yukung.perfect_java.chap7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 愚直に文字列を読み込んで処理するコード。
 */
public class MyEcho {
	
	public static void main(String[] args) {
		MyEcho echo = new MyEcho();
		echo.doit();
	}
	
	public void doit() {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				System.out.println("Input any text.");
				String msg = stdin.readLine();
				System.out.println("You said, " + capitalize(replace(msg, "he", "she")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
// ここから拡張のたびに下請け処理メソッドが次々と増えていく。。。
	private String capitalize(String in) {
		return in.toUpperCase();
	}
	
	private String replace(String in, String origStr, String newStr) {
		return in.replaceAll(origStr, newStr);
	}
}
