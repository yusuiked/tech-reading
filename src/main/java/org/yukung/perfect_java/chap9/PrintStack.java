package org.yukung.perfect_java.chap9;

public class PrintStack {
	
	public static void main(String[] args) {
		Thread.currentThread().dumpStack();
		print();
	}
	
	public static void print() {
		StackTraceElement[] frames = Thread.currentThread().getStackTrace();
		for (StackTraceElement frame : frames) {
			System.out.printf("%s#%s:%d\n", frame.getClassName(), frame.getMethodName(), frame.getLineNumber());
		}
	}
	
}
