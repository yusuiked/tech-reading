package org.yukung.practice.chap16;

public class CountUpThread extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			System.out.println(i);
		}
	}
}
