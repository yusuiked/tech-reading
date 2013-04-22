package org.yukung.perfect_java.chap15;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@SuppressWarnings("javadoc")
public class FileByte {
	
	public static void main(String[] args) {
		InputStream in = null;
		try {
			in = new FileInputStream(args[0]);
			byte[] buf = new byte[4096];
			int len;
			
			while ((len = in.read(buf)) != -1) {
				System.out.write(buf, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
