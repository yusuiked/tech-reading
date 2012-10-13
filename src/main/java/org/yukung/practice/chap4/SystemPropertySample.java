package org.yukung.practice.chap4;

import java.util.Iterator;
import java.util.Properties;

public class SystemPropertySample {

	public static void main(String[] args) {
		System.out.print("Java version is ");
		System.out.println(System.getProperty("java.version"));
		Properties properties = System.getProperties();
		Iterator<String> iterator = properties.stringPropertyNames().iterator();
		System.out.println("System Propertiy list...");
		while (iterator.hasNext()) {
			String key = iterator.next();
System.out.printf("%s : %s%n", key, System.getProperty(key));
		}
	}
}
