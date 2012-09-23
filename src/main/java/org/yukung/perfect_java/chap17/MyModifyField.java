package org.yukung.perfect_java.chap17;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class MyModifyField {
	
	public static void main(String[] args) {
		try {
			File file = new File("/tmp");
			Class<? extends File> clazz = file.getClass();
			Field field = clazz.getDeclaredField("path");
			if (!field.isAccessible()) {
				field.setAccessible(true); // dangerous!
			}
			
			System.out.println(field.get(file));
			
			field.set(file, "/tmp/newfile");
			file.createNewFile();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
