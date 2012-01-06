package org.yukung.perfect_java.chap2;

public class StringBuilderTest {
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("0123456789");
		char charAt = sb.charAt(3);
		int length = sb.length();
		sb.append(8);
		System.out.println(sb.toString());
		
	}
	
}
