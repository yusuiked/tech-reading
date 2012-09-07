package org.yukung.perfect_java.chap15;

import java.io.IOException;

public class MyPager {
	
	public static void main(String[] args) {
		try {
			byte[] buf = new byte[4096];
			while ((System.in.read(buf)) > -1) {
				System.out.write(buf);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
