package org.yukung.perfect_java.chap16;

public class MyThread extends Thread {
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new MyThread("one");
		Thread t2 = new MyThread("two");
		
		t1.start();
		t2.start();
		
		for (int i = 0; i < 256; i++) {
			System.out.println("main " + i);
		}
		t1.join();
		t2.join();
	}
	
	
	private String name;
	
	
	public MyThread(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 256; i++) {
			System.out.println(name + " " + i);
		}
	}
}
