package org.yukung.perfect_java.chap5;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
 * 再帰呼び出しをするメソッドの例。
 */
public class MyRecursive {
	
	public static void main(String[] args) {
		MyRecursive my = new MyRecursive();
		my.doit();
	}
	
	void doit() {
		List<String> list = Arrays.asList(new String[] {
			"0",
			"1",
			"2",
			"3",
			"4"
		});
		dump(list.iterator());
	}
	
	void dump(Iterator<String> itr) {
		if (itr != null && itr.hasNext()) {
			String s = itr.next();
			System.out.println(s);
			dump(itr);
		}
	}
	
}
