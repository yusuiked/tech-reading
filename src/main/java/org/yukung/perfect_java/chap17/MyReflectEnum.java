package org.yukung.perfect_java.chap17;

public class MyReflectEnum {
	
	public static void main(String[] args) {
		try {
			Class<Gender> clazz1 = Gender.class;
			Class<? extends Gender> clazz2 = Gender.MAN.getClass();
			Class<? extends Gender> clazz3 = Gender.WOMAN.getClass();
			Class<?> clazz4 = Class.forName("org.yukung.perfect_java.chap17.Gender");
			
			System.out.println(clazz1.hashCode() + ", " + clazz2.hashCode() + ", " + clazz3.hashCode() + ", "
					+ clazz4.hashCode());
			
			assert (clazz1 == clazz2);
			assert (clazz2 == clazz3);
			assert (clazz3 == clazz4);
			
			if (clazz1.isEnum()) {
				Gender[] values = clazz1.getEnumConstants();
				for (Gender gender : values) {
					System.out.println(gender);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

enum Gender {
	MAN, WOMAN, OTHER,
}
