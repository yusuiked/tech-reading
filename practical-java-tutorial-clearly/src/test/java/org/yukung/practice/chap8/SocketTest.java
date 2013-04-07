package org.yukung.practice.chap8;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Test;

public class SocketTest {

	@Test
	public void testGetWebPageWithSocket() throws Exception {
		Socket socket = new Socket("dokojava.jp", 80);
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();
		out.write("GET /index.html HTTP/1.0\r\n".getBytes());
		out.write("\r\n".getBytes());
		out.flush();
		InputStreamReader isr = new InputStreamReader(in);
		for (int i = isr.read(); i >= 0; i = isr.read()) {
			System.out.print((char) i);
		}
		socket.close();
	}
}
