package org.yukung.perfect_java.chap17;

public class MyReflectArray {
	
	public static void main(String[] args) {
		try {
			String[] s_arr = {};
			Class clazz1 = String[].class;
			Class<? extends String[]> clazz2 = s_arr.getClass();
			Class<?> clazz3 = Class.forName("[Ljava.lang.String;");
			
			System.out.println(clazz1.hashCode() + ", " + clazz2.hashCode() + ", " + clazz3.hashCode());
			assert (clazz1 == clazz2);
			assert (clazz2 == clazz3);
			
			Integer[] i_arr = {};
			Class clazz4 = Integer[].class;
			Class<? extends Integer[]> clazz5 = i_arr.getClass();
			Class<?> clazz6 = Class.forName("[Ljava.lang.Integer;");
			
			System.out.println(clazz4.hashCode() + ", " + clazz5.hashCode() + ", " + clazz6.hashCode());
			assert (clazz1 != clazz4);
			assert (clazz4 == clazz5);
			assert (clazz5 == clazz6);
			
			int[] int_arr = {};
			Class<int[]> clazz7 = int[].class;
			Class<? extends int[]> clazz8 = int_arr.getClass();
			Class clazz9 = Class.forName("[I");
			
			System.out.println(clazz7.hashCode() + ", " + clazz8.hashCode() + ", " + clazz9.hashCode());
			assert (clazz1 != clazz7);
			assert (clazz4 != clazz7);
			assert (clazz7 == clazz8);
			assert (clazz8 == clazz9);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
