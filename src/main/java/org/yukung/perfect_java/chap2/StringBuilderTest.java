package org.yukung.perfect_java.chap2;

import org.apache.commons.lang3.time.StopWatch;

public class StringBuilderTest {
	
	/*
	 * 無駄が多い文字列結合。
	 *
	 * += の結合時にStrigBuilderが都度生成され、さらにappend()が2度呼ばれる。
	 * sのStringオブジェクトをappend,resultのStringオブジェクトをappend。
	 *
	 * time: 64093ms
	 */
	public static String concat(String[] array) {
		String result = "";
		for (String s : array) {
			result += s;
		}
		return result;
	}
	
	/*
	 * StringBuilder#append()を使った例。
	 * StringBuilderが一度だけ生成され、append()も一度しか呼ばれない。
	 *
	 * time: 9ms
	 */
	public static String effectiveConcat(String[] array) {
		StringBuilder result = new StringBuilder();
		for (String s : array) {
			result.append(s);
		}
		return result.toString();
	}
	
	public static void main(String[] args) {
		lookAtBehavior();
		
		measureConcatenate();
		
	}
	
	private static void lookAtBehavior() {
		StringBuilder sb = new StringBuilder("0123456789");
		char charAt = sb.charAt(3);
		System.out.println(charAt);
		int length = sb.length();
		System.out.println(length);
		sb.append(8);
		System.out.println(sb.toString());
		sb.delete(1, 2);
		System.out.println(sb.toString());
		int indexOf = sb.indexOf("6");
		System.out.println(indexOf);
		sb.insert(8, 'f');
		System.out.println(sb.toString());
		sb.replace(0, 5, "543210");
		System.out.println(sb.toString());
		sb.setCharAt(0, '7');
		System.out.println(sb.toString());
		String substring = sb.substring(5);
		System.out.println(substring);
		sb.reverse();
		System.out.println(sb.toString());
	}
	
	private static void measureConcatenate() {
		String[] data = new String[100000];
		for (int i = 0; i < 100000; i++) {
			data[i] = Integer.toString(i);
		}
		
		long start = System.currentTimeMillis();
		
		concat(data);
		
		long end = System.currentTimeMillis();
		System.out.printf("time: %sms%n", end - start);
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		effectiveConcat(data);
		
		stopWatch.stop();
		
		System.out.printf("time: %sms%n", stopWatch.getTime());
	}
}
