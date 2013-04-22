package org.yukung.practice.chap16;

public class Main {
	public static void main(String[] args) {
		Thread t1 = new CountUpThread();
		Thread t2 = new CountUpThread();
		Thread t3 = new CountUpThread();
		t1.start();
		t2.start();
		t3.start();
	}
}
