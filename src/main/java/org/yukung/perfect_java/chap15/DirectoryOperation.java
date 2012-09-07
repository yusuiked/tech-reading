package org.yukung.perfect_java.chap15;

import java.io.File;

public class DirectoryOperation {
	
	public static void main(String[] args) {
		File dir = new File("./src/main/java/org/yukung/perfect_java/chap15/hoge");
		dir.mkdir();
		if (dir.exists()) {
			dir.renameTo(new File("./src/main/java/org/yukung/perfect_java/chap15/fuga"));
		}
		dir =
				new File(
						"/Users/yukung/Documents/workspace/perfect_java/src/main/java/org/yukung/perfect_java/chap15/fuga");
		dir.delete();
		
		String path = "."; // カレントディレクトリ
		File dir2 = new File(path);
		String[] files = dir2.list();
		for (String file : files) {
			System.out.println(file);
		}
	}
}
