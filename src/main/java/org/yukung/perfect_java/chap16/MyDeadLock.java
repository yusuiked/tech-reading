package org.yukung.perfect_java.chap16;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyDeadLock {
	
	public static void main(String[] args) throws InterruptedException {
		MyDeadLock my = new MyDeadLock();
		my.doit();
	}
	
	
	private List<String> list1 = Arrays.asList(new String[] {
		"one",
		"two",
		"three"
	});
	
	private List<String> list2 = Arrays.asList(new String[] {
		"ONE",
		"TWO",
		"THREE"
	});
	
	
	public void doit() throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 1000 * 1000; i++) {
					synchronized (list1) {
						Collections.sort(list1);
						synchronized (list2) {
							Collections.sort(list2);
						}
					}
					if (i % 1000 == 0) {
						System.out.println(",");
					}
				}
			}
		}, "my-thread1");
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 1000 * 1000; i++) {
					synchronized (list2) {
						Collections.sort(list2);
						synchronized (list1) {
							Collections.sort(list1);
						}
					}
					if (i % 1000 == 0) {
						System.out.println(".");
					}
				}
			}
		}, "my-thread2");
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		for (String s : list1) {
			System.out.println(s);
		}
		for (String s : list2) {
			System.out.println(s);
		}
	}
}
