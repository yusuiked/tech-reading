package org.yukung.perfect_java.chap17.di;

import java.io.Console;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class MyCompiler {
	
	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			System.err.println("usage: java " + MyCompiler.class.getSimpleName() + " load-class-name method-name");
			return;
		}
		
		// コンソールから対象のソースファイルのパスをもらってコンパイル
		Console console = System.console();
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		if (compiler == null) {
			System.out.println("Compiler is not found.");
			return;
		}
		
		while (true) {
			int ret = compiler.run(null, null, null, new String[] {
				args[0] + ".java"
			});
			if (ret == 0) {
				// コンパイルできたら、クラスローダを作って
				ClassLoader loader = URLClassLoader.newInstance(new URL[] {
					new File(".").toURI().toURL()
				}, null);
				// 作ったクラスローダを指定して、クラスをロード
				Class<?> clazz = Class.forName(args[0], true, loader);
				if (clazz != null) {
					// クラスがロードできたら、メソッド実行
					clazz.getMethod(args[1]).invoke(clazz.newInstance());
				}
			} else {
				System.out.println("Compile failed.");
				break;
			}
			console.printf("Press enter key to continue.");
			console.readLine();
		}
	}
}
