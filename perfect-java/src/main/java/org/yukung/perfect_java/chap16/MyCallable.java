package org.yukung.perfect_java.chap16;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyCallable {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService ex = Executors.newFixedThreadPool(8);
		
		Future<Integer> ret1 = ex.submit(new Worker("one"));
		Future<Integer> ret2 = ex.submit(new Worker("two"));
		
		for (int i = 0; i < 256; i++) {
			System.out.println("main " + i);
		}
		System.out.println(ret1.get());
		System.out.println(ret2.get());
		ex.shutdown();
	}
	
	
	private static class Worker implements Callable<Integer> {
		
		private String name;
		
		
		public Worker(String name) {
			this.name = name;
		}
		
		@Override
		public Integer call() throws Exception {
			int sum = 0;
			for (int i = 0; i < 256; i++) {
				System.out.println(name + " " + i);
				sum += i;
			}
			return sum;
		}
	}
}
