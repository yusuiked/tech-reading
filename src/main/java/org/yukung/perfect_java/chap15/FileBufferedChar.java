package org.yukung.perfect_java.chap15;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class FileBufferedChar {
	
	public static void main(String[] args) {
		Reader in = null;
		Writer out = null;
		try {
			in = new BufferedReader(new FileReader(args[0]));
			out = new BufferedWriter(new OutputStreamWriter(System.out));
			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
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
