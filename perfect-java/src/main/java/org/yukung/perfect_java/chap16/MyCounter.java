package org.yukung.perfect_java.chap16;

public class MyCounter {
	
	public static void main(String[] args) throws InterruptedException {
		MyCounter my = new MyCounter();
		my.doit();
	}
	
	
	private int count;
	
	
	public void doit() throws InterruptedException {
		Thread t1 = new Thread(new MyWorker(this));
		t1.start();
		Thread t2 = new Thread(new MyWorker(this));
		t2.start();
		
		t1.join();
		t2.join();
		System.out.println(count);
	}
	
	public void increment() {
		count++;
	}
}

class MyWorker implements Runnable {
	
	private static final int LOOP_NUM = 10000000;
	
	private MyCounter counter;
	
	
	MyWorker(MyCounter counter) {
		this.counter = counter;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < LOOP_NUM; i++) {
			counter.increment();
		}
	}
}
