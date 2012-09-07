package org.yukung.perfect_java.chap15;

import java.io.File;

public class DirectoryOperation {
	
	public static void main(String[] args) {
		File file = new File("./src/main/java/org/yukung/perfect_java/chap15/hoge");
		file.mkdir();
		if (file.exists()) {
			file.renameTo(new File("./src/main/java/org/yukung/perfect_java/chap15/fuga"));
		}
		file =
				new File(
						"/Users/yukung/Documents/workspace/perfect_java/src/main/java/org/yukung/perfect_java/chap15/fuga");
		file.delete();
	}
}
