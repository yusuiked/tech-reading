package org.yukung.perfect_java.chap16;

public class MyRun {
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Worker("one"));
		Thread t2 = new Thread(new Worker("two"));
		
		t1.start();
		t2.start();
		
		for (int i = 0; i < 256; i++) {
			System.out.println("main " + i);
		}
		
		t1.join();
		t2.join();
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
