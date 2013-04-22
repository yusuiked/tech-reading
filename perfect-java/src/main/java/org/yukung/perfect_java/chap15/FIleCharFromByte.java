package org.yukung.perfect_java.chap15;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class FIleCharFromByte {
	
	public static void main(String[] args) {
		Reader in = null;
		Writer out = null;
		try {
			in = new InputStreamReader(new FileInputStream(args[0]));
			out = new OutputStreamWriter(System.out);
			char[] cbuf = new char[4096];
			int len;
			while ((len = in.read(cbuf)) != -1) {
				out.write(cbuf, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
			}
		}
	}
	
}
