package org.yukung.perfect_java.chap15.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class MyClient {
	
	private static final String SERVER_HOST = "localhost";
	
	private static final int SERVER_PORT = 9000;
	
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("usage: java " + MyClient.class.getSimpleName() + " messages");
			System.exit(1);
		}
		Socket sock = null;
		try {
			sock = new Socket(SERVER_HOST, SERVER_PORT);
			Reader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			Writer out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
			
			for (String arg : args) {
				out.write(arg);
			}
			out.write("."); // End
			out.flush();
			
			char[] cbuf = new char[4096];
			while (in.read(cbuf) != -1) {
				System.out.println(cbuf);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (sock != null) {
					sock.close();
				}
			} catch (IOException e) {
			}
		}
	}
}
