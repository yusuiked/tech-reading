package org.yukung.perfect_java.chap17;

public class MyReflect {
	
	public static void main(String[] args) {
		try {
			Class clazz1 = StringBuilder.class;
			
			StringBuilder sb = new StringBuilder();
			Class clazz2 = sb.getClass();
			
			Class clazz3 = Class.forName("java.lang.StringBuilder");
			System.out.println(clazz1.hashCode() + ", " + clazz2.hashCode() + ", " + clazz3.hashCode());
			assert (clazz1 == clazz2);
			assert (clazz2 == clazz3);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
