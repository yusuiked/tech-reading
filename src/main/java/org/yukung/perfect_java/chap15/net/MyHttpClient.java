package org.yukung.perfect_java.chap15.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map.Entry;

public class MyHttpClient {
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("usage: java " + MyHttpClient.class.getSimpleName() + " URL");
			System.exit(-1);
		}
		MyHttpClient client = new MyHttpClient();
		client.doit(args[0]);
	}
	
	public void doit(String url) {
		HttpURLConnection conn = null;
		BufferedReader in = null;
		try {
			conn = (HttpURLConnection) (new URL(url)).openConnection();
			conn.setRequestMethod("GET");
			conn.setInstanceFollowRedirects(false);
			
			conn.connect();
			
			for (Entry<String, List<String>> header : conn.getHeaderFields().entrySet()) {
				String key = header.getKey();
				System.out.println("Header name: " + key);
				for (String value : header.getValue()) {
					System.out.println("Header value: " + value);
				}
			}
			
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
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
			if (conn != null) {
				conn.disconnect();
			}
		}
	}
}
