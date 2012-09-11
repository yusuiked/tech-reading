package org.yukung.perfect_java.chap17;

public class MyReflectObjectTree {
	
	public static void main(String[] args) {
		Class clazz = StringBuilder.class;
		
		Class parent;
		Class base = clazz;
		while ((parent = base.getSuperclass()) != null) {
			System.out.println(parent);
			base = parent;
		}
		
		Class[] interfaces = clazz.getInterfaces();
		for (Class inf : interfaces) {
			System.out.println(inf);
		}
	}
}
