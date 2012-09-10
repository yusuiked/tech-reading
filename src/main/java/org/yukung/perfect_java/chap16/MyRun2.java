package org.yukung.perfect_java.chap16;

public class MyRun2 {
	
	private int count;
	
	private static final int LOOP_NUM = 10000000;
	
	
	public static void main(String[] args) throws InterruptedException {
		MyRun2 my = new MyRun2();
		my.doit();
	}
	
	public void doit() throws InterruptedException {
		Thread t1 = new Thread(new Worker());
		t1.start();
		Thread t2 = new Thread(new Worker());
		t2.start();
		
		t1.join();
		t2.join();
		System.out.println(count);
	}
	
	
	public class Worker implements Runnable {
		
		@Override
		public void run() {
			for (int i = 0; i < LOOP_NUM; i++) {
				count++;
			}
		}
	}
}
