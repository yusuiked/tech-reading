package org.yukung.perfect_java.chap16;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentServer {
	
	private static final int LISTEN_PORT = 9000;
	
	
	public static void main(String[] args) {
		ConcurrentServer server = new ConcurrentServer();
		server.loop();
	}
	
	
	private ExecutorService executor;
	
	
	public ConcurrentServer() {
		executor = Executors.newFixedThreadPool(8);
	}
	
	public void loop() {
		ServerSocket ssock = null;
		try {
			ssock = new ServerSocket(LISTEN_PORT);
			while (true) {
				Socket sock = ssock.accept();
				executor.execute(new Worker(sock));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
			try {
				if (ssock != null) {
					ssock.close();
				}
			} catch (IOException e) {
			}
		}
	}
	
	
	private static class Worker implements Runnable {
		
		private Socket sock;
		
		
		Worker(Socket sock) {
			this.sock = sock;
		}
		
		@Override
		public void run() {
			try {
				Reader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				Writer out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
				StringBuilder sb = new StringBuilder(4096);
				
				int c;
				while ((c = in.read()) != -1) {
					System.out.println(new Character((char) c));
					if (c == '.') {
						break;
					}
					sb.append((char) c);
				}
				out.write(sb.toString());
				out.flush();
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
}
