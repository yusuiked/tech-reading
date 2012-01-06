package org.yukung.perfect_java.chap2;

public class StringBuilderTest {
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("0123456789");
		char charAt = sb.charAt(3);
		System.out.println(charAt);
		int length = sb.length();
		System.out.println(length);
		sb.append(8);
		System.out.println(sb.toString());
		sb.delete(1, 2);
		System.out.println(sb.toString());
		int indexOf = sb.indexOf("6");
		System.out.println(indexOf);
		sb.insert(8, 'f');
		System.out.println(sb.toString());
		sb.replace(0, 5, "543210");
		System.out.println(sb.toString());
		sb.setCharAt(0, '7');
		System.out.println(sb.toString());
		String substring = sb.substring(5);
		System.out.println(substring);
		sb.reverse();
		System.out.println(sb.toString());
	}
	
}
