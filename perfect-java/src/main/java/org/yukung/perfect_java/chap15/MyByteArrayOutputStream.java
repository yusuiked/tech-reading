package org.yukung.perfect_java.chap15;

import java.io.ByteArrayOutputStream;

public class MyByteArrayOutputStream {
	
	public static void main(String[] args) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] b1 = {
			'0',
			'1',
			'2'
		};
		byte[] b2 = {
			'3',
			'4',
			'5'
		};
		byte[] b3 = {
			'6',
			'7',
			'8'
		};
		bos.write(b1, 0, b1.length);
		bos.write(b2, 0, b2.length);
		bos.write(b3, 0, b3.length);
		byte[] buf = bos.toByteArray();
		for (byte b : buf) {
			System.out.println(b);
		}
	}
}
