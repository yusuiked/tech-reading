package org.yukung.practice.chap6;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CopyFileTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCopy() throws Exception {		File from = new File("src/test/resources/from.txt");
		File to = new File("src/test/resources/to.txt.gz");
		CopyFile copyFile = new CopyFile();
		copyFile.copy(from, to);
		BufferedInputStream origin = new BufferedInputStream(new FileInputStream(from)) ;
		GZIPInputStream dest = new GZIPInputStream(new BufferedInputStream(new FileInputStream(to))) ;		List<Integer> fromByteArrayList = new ArrayList<Integer>((int) from.length());
		List<Integer> toByteArrayList = new ArrayList<Integer>((int) from.length());
		int b1;
		while ((b1 = origin.read()) != -1) {
			fromByteArrayList.add(b1);
		}
		int b2;
		while ((b2 = dest.read()) != -1) {
			toByteArrayList.add(b2);
		}		origin.close();
		dest.close();
		assertThat(Arrays.equals(fromByteArrayList.toArray(), toByteArrayList.toArray()), is(true));
	}

}
