package org.yukung.perfect_java.chap2;

import org.apache.commons.lang3.time.StopWatch;

public class ConvertExample {
	
	private static final int LOOP_COUNT = 1000000;
	
	
	public static void main(String[] args) {
//		measureValueOf();
//		measureWrapperClass();
//		int2str();
		measureNumValueOf();
		measureNumParse();
	}
	
	private static void int2str() {
		String s1 = Integer.toHexString(-1);
		String s2 = Integer.toBinaryString(-1);
		System.out.printf("%s, %s", s1, s2);
	}
	
	private static void measureNumParse() {
		String[] array = new String[LOOP_COUNT];
		for (int i = 0; i < LOOP_COUNT; i++) {
			array[i] = String.valueOf(i);
		}
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		for (String string : array) {
			Integer.parseInt(string);
		}
		stopWatch.stop();
		System.out.printf("Integer#parseInt() time: %sms%n", stopWatch.getTime());
	}
	
	private static void measureNumValueOf() {
		String[] array = new String[LOOP_COUNT];
		for (int i = 0; i < LOOP_COUNT; i++) {
			array[i] = String.valueOf(i);
		}
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		for (String string : array) {
			Integer.valueOf(string);
		}
		stopWatch.stop();
		System.out.printf("Integer#valueOf() time: %sms%n", stopWatch.getTime());
	}
	
	/*
	 * time: 111ms
	 */
	private static void measureValueOf() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		for (int i = 0; i < LOOP_COUNT; i++) {
			String.valueOf(i);
		}
		stopWatch.stop();
		System.out.printf("time: %sms%n", stopWatch.getTime());
	}
	
	/*
	 * time: 67ms
	 */
	private static void measureWrapperClass() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		for (int i = 0; i < LOOP_COUNT; i++) {
			Integer.toString(i);
		}
		stopWatch.stop();
		System.out.printf("time: %sms%n", stopWatch.getTime());
	}
}
