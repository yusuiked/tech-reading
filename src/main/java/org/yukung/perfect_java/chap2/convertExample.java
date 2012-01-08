package org.yukung.perfect_java.chap2;

import org.apache.commons.lang3.time.StopWatch;

public class convertExample {
	
	private static final int LOOP_COUNT = 1000000;
	
	
	public static void main(String[] args) {
		measureValueOf();
		measureWrapperClass();
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
