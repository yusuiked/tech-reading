package org.yukung.perfect_java.chap11.my;

public class MyFactory {
	
	public static My createMy() {
		return new MyImpl();
	}
}
