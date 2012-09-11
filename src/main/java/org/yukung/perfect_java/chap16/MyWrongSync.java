package org.yukung.perfect_java.chap16;

public class MyWrongSync {
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Worker());
		Thread t2 = new Thread(new Worker());
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		System.out.println(Worker.count);
	}
	
	
	private static class Worker implements Runnable {
		
		private static final int LOOP_NUM = 10000000;
		
		private static int count;
		
		
		@Override
		public void run() {
			for (int i = 0; i < LOOP_NUM; i++) {
				increment();
			}
		}
		
		private synchronized void increment() {
			count++; // モニタロックが Worker なので、count に対して synchronized できていない
		}
	}
}
