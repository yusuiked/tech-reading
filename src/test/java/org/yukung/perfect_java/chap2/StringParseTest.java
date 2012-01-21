package org.yukung.perfect_java.chap2;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class StringParseTest {
	
	@Test
	public void testByte2String() throws Exception {
		byte[] bytes1 = new byte[] {
			0x61,
			0x62,
			0x63
		};
		String s = new String(bytes1);
		assertThat("abc", is(s));
		
		byte[] bytes2 = new byte[] {
			(byte) 0xe3,
			(byte) 0x81,
			(byte) 0x82,
			(byte) 0xe3,
			(byte) 0x81,
			(byte) 0x84
		};
		String str = new String(bytes2, "UTF-8"); // "あい"を出力
		assertThat("あい", is(str));
	}
	
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
		
		String value = String.copyValueOf(new char[] {
			'a',
			'b',
			'c'
		});
		System.out.println(value);
	}
	
	@Test
	public void testString2Byte() throws Exception {
		String s = "abc";
		byte[] bytes = s.getBytes();
		for (byte b : bytes) {
			System.out.println(b);
		}
	}
}
