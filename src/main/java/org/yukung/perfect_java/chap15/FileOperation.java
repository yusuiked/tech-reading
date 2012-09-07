package org.yukung.perfect_java.chap15;

import java.io.File;
import java.io.IOException;

public class FileOperation {
	
	public static void main(String[] args) {
		File file = new File("./src/main/java/org/yukung/perfect_java/chap15/hoge.txt");
		try {
			file.createNewFile();
			if (file.exists()) {
				file.renameTo(new File("./src/main/java/org/yukung/perfect_java/chap15/fuga.txt"));
			}
			file =
					new File(
							"/Users/yukung/Documents/workspace/perfect_java/src/main/java/org/yukung/perfect_java/chap15/fuga.txt");
			file.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
