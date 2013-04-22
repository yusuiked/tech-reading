package org.yukung.perfect_java.chap15;

import java.io.File;

public class MyShowFiles {
	
	public static void main(String[] args) {
		File dir = new File(args[0]);
		showFiles(dir);
	}
	
	private static void showFiles(File base) {
		System.out.println(base);
		if (base.isDirectory()) {
			File[] files = base.listFiles();
			for (File file : files) {
				showFiles(file);
			}
		}
	}
}
