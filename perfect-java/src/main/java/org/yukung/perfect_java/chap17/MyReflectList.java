package org.yukung.perfect_java.chap17;

import java.util.ArrayList;
import java.util.List;

public class MyReflectList {
	
	public static void main(String[] args) {
		try {
			Class clazz1 = List.class;
			Class clazz2 = ArrayList.class;
			
			List rlist = new ArrayList();
			Class<? extends List> clazz3 = rlist.getClass();
			
			List<String> slist = new ArrayList<String>();
			Class<? extends List> clazz4 = slist.getClass();
			List<Integer> ilist = new ArrayList<Integer>();
			Class<? extends List> clazz5 = ilist.getClass();
			
			Class<?> clazz6 = Class.forName("java.util.List");
			Class<?> clazz7 = Class.forName("java.util.ArrayList");
			
			System.out.println(clazz1.hashCode() + ", " + clazz6.hashCode());
			System.out.println(clazz2.hashCode() + ", " + clazz3.hashCode() + ", " + clazz4.hashCode() + ", "
					+ clazz5.hashCode() + ", " + clazz7.hashCode());
			
			assert (clazz1 == clazz6);
			assert (clazz1 != clazz2);
			assert (clazz2 == clazz3);
			assert (clazz3 == clazz4);
			assert (clazz4 == clazz5);
			assert (clazz5 == clazz7);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
