package org.yukung.perfect_java.chap15;

import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class FileChar {
	
	public static void main(String[] args) {
		Reader in = null;
		Writer out = null;
		try {
			in = new FileReader(args[0]); // 文字コードを指定する場合はInputStreamReader
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
