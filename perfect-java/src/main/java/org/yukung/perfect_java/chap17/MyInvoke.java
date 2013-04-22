package org.yukung.perfect_java.chap17;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyInvoke {
	
	public static void main(String[] args) {
		try {
			Class<?> clazz = Class.forName("java.lang.StringBuilder");
			Method append_method = clazz.getMethod("append", String.class);
			Method length_method = clazz.getMethod("length");
			
			CharSequence sb = (CharSequence) clazz.newInstance();
			append_method.invoke(sb, "abc");
			append_method.invoke(sb, "def");
			append_method.invoke(sb, "ghi");
			System.out.println(sb);
			System.out.println(length_method.invoke(sb));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
}
