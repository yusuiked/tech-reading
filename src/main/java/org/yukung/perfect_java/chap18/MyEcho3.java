package org.yukung.perfect_java.chap18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MyEcho3 {
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		MyEcho3 echo = new MyEcho3(args);
		echo.doit();
	}
	
	
	private List<Pair> filters = new ArrayList<Pair>();
	
	
	public MyEcho3(String[] classNames) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		for (String className : classNames) {
			Class<?> clazz = Class.forName(className);
			for (Method method : clazz.getMethods()) {
				if (method.isAnnotationPresent(MyFilter.class)) {
					filters.add(new Pair(clazz.newInstance(), method));
				}
			}
		}
	}
	
	public void doit() {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		try {
			while (true) {
				System.out.println("Input any text");
				String msg;
				msg = stdin.readLine();
				
				String output = msg;
				for (Pair pair : filters) {
					output = (String) pair.method.invoke(pair.obj, output);
				}
				System.out.println("You said, " + output);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * simple value object class
	 */
	private static class Pair {
		
		final Object obj;
		
		final Method method;
		
		
		Pair(Object obj, Method method) {
			this.obj = obj;
			this.method = method;
		}
	}
}
