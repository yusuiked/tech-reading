package org.yukung.perfect_java.chap2;

import org.junit.Test;

public class StringParseTest {
	
	@Test
	public void testParseString() {
		String s = Integer.toString(-1, 16);
		String s2 = Integer.toHexString(-1);
		System.out.println(s);
		System.out.println(s2);
		String s3 = Integer.toString(255, 16);
		String s4 = Integer.toHexString(255);
		System.out.println(s3);
		System.out.println(s4);
		
		/*
		 *	-1
		 *	ffffffff
		 *	ff
		 *	ff
		 */
	}
}
