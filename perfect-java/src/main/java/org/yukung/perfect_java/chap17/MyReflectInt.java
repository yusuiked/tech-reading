package org.yukung.perfect_java.chap17;

public class MyReflectInt {
	
	public static void main(String[] args) {
		try {
			Class<Integer> clazz1 = Integer.class;
			
			Integer i = new Integer(0);
			Class<? extends Integer> clazz2 = i.getClass();
			
			Class<?> clazz3 = Class.forName("java.lang.Integer");
			
			Class<Integer> clazz4 = int.class;
			Class clazz5 = Integer.TYPE;
			
			System.out.println(clazz1.hashCode() + ", " + clazz2.hashCode() + ", " + clazz3.hashCode() + ", "
					+ clazz4.hashCode() + ", " + clazz5.hashCode());
			
			assert (clazz1 == clazz2);
			assert (clazz2 == clazz3);
			assert (clazz1 != clazz4);
			assert (clazz4 == clazz5);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
