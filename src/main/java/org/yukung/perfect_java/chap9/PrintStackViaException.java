package org.yukung.perfect_java.chap9;

public class PrintStackViaException {
	
	public static void main(String[] args) {
		(new Throwable()).printStackTrace();
		print();
	}
	
	private static void print() {
		StackTraceElement[] frames = (new Throwable()).getStackTrace();
		for (StackTraceElement frame : frames) {
			System.out.printf("%s#%s:%d\n", frame.getClassName(), frame.getMethodName(), frame.getLineNumber());
		}
	}
	
}
