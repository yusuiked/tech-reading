package org.yukung.perfect_java.chap15;

import java.io.File;
import java.io.FilenameFilter;

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
		
		System.out.println("-----------------------------------");
		// ディレクトリのファイル一覧とフィルタ処理
		File dir3 = new File(".");
		String[] files2 = dir3.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// 隠しファイルをフィルタする
				return dir.isDirectory() && !name.startsWith(".");
			}
		});
		for (String file2 : files2) {
			System.out.println(file2);
		}
	}
}
