package org.yukung.perfect_java.chap16;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecute {
	
	public static void main(String[] args) {
		ExecutorService ex = Executors.newFixedThreadPool(8);
		
		ex.execute(new Worker("one"));
		ex.execute(new Worker("two"));
		
		for (int i = 0; i < 256; i++) {
			System.out.println("main " + i);
		}
		ex.shutdown();
	}
	
	
	private static class Worker implements Runnable {
		
		private String name;
		
		
		public Worker(String name) {
			this.name = name;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < 256; i++) {
				System.out.println(name + " " + i);
			}
		}
	}
}
