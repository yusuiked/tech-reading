package org.yukung.perfect_java.chap16;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MyProduceConsume {
	
	public static void main(String[] args) throws InterruptedException {
		MyProduceConsume my = new MyProduceConsume();
		my.doit();
	}
	
	public void doit() throws InterruptedException {
		Queue<String> queue = new LinkedList<String>();
		
		Thread producer = new Thread(new Producer(queue));
		Thread consumer = new Thread(new Consumer(queue));
		
		producer.start();
		consumer.start();
		
		producer.join();
		consumer.join();
	}
	
	
	private static class Consumer implements Runnable {
		
		private Queue<String> queue;
		
		
		Consumer(Queue<String> queue) {
			this.queue = queue;
		}
		
		@Override
		public void run() {
			try {
				while (true) {
					synchronized (queue) {
						while (queue.isEmpty()) {
							System.out.println(Consumer.class.getSimpleName() + ": waiting...");
							queue.wait();
						}
						String s = queue.remove();
						System.out.println(Consumer.class.getSimpleName() + ": " + s + " is consumed");
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class Producer implements Runnable {
		
		private Queue<String> queue;
		
		
		Producer(Queue<String> queue) {
			this.queue = queue;
		}
		
		@Override
		public void run() {
//			Console console = System.console();	// System.console() は Eclipse 上だと null を返す
			Scanner scanner = new Scanner(System.in);
			while (true) {
				System.out.println(Producer.class.getSimpleName() + ": input any string");
				String s = scanner.next();
				synchronized (queue) {
					queue.add(s);
					queue.notifyAll();
				}
			}
		}
	}
}
