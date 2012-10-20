package org.yukung.practice.chap8;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.junit.Test;

public class URLTest {

	@Test
	public void testWget() throws Exception {
		URL url = new URL("http://dokojava.jp");
		InputStream in = url.openStream();
		InputStreamReader isr = new InputStreamReader(in);
		for (int i = isr.read(); i > -1; i = isr.read()) {
			System.out.print((char) i);
		}
		isr.close();
	}

}
