package org.yukung.perfect_java.chap17;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MyInstance {
	
	public static void main(String[] args) {
		try {
			Class<?> clazz = Class.forName("java.lang.StringBuilder");
			Constructor<?> constructor = clazz.getConstructor(String.class);
			CharSequence sb = (CharSequence) constructor.newInstance("abc");
			System.out.println(sb);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
