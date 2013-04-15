package org.yukung.practice.chap8;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class Downloader {

	private String targetUrl;

	public Downloader(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public void download(String to) {
		BufferedInputStream in = null;
		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(to))) {
			URL url = new URL(targetUrl);
			in = new BufferedInputStream(url.openStream());
			for (int buf = in.read(); buf > -1; buf = in.read()) {
				out.write(buf);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {}
		}
	}

	public static void main(String[] args) {
		Downloader downloader = new Downloader("http://dokojava.jp/favicon.ico");
		downloader.download("dj.ico");
	}
}
