package org.yukung.perfect_java.chap17.di;

import java.lang.reflect.Constructor;

public class MyDI {
	
	public static void main(String[] args) throws Exception {
		MyDI di = new MyDI();
		StringList sl =
				(StringList) di.resolve("org.yukung.perfect_java.chap17.di.StringList", "java.util.List",
						"java.util.ArrayList");
		sl.append("one");
		sl.append("two");
		sl.dump();
	}
	
	public Object resolve(String targetType, String argType, String argObjectType) throws Exception {
		Class<?> targetClass = Class.forName(targetType);
		Class<?> argClass = Class.forName(argObjectType);
		Object arg = argClass.newInstance();
		
		Constructor<?> constructor = targetClass.getConstructor(Class.forName(argType));
		return constructor.newInstance(arg);
	}
}
