package org.yukung.perfect_java.chap5;

public class Sub extends Base {
	
	public static void main(String[] args) {
		Sub sub = new Sub();
		sub.doit();
		
		Base base = new Base();
		base.doit();
		
		Sub sub2 = new Sub();
		sub2.doit();
		
	}
	
	
	String s = "456";
	
	
	@Override
	void doit() {
		System.out.println("my:doit " + super.s);
	}
}

class Base {
	
	String s = "123";
	
	
	void doit() {
		System.out.println("base:doit " + s);
	}
}
