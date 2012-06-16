package org.yukung.perfect_java.chap8;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
	
	public static void main(String[] args) {
		// 暗黙にlist.toString()が呼ばれて、文字列に型変換される
		List<String> list = new ArrayList<String>();
		list.add("bar");
		list.add("baz");
		String s = "foo" + list;
		System.out.println(s); //=> "foo[bar, baz]"
		
		// 暗黙に数値1がボクシング変換でInteger型に変換され、さらにInteger.toString()が呼ばれる
		String s1 = "foo" + 1;
		System.out.println(s1); //=> "foo1"
		
		// nullと文字列の結合
		String s2 = "foo" + null;
		System.out.println(s2); //=> "foonull"
		
		// +=演算子の例
		String s3 = "foo";
		s3 += "bar";
		System.out.println(s3); //=> "foobar"
		
		// 文字（char）型は数値の加算減算
		System.out.println('a' + 'b'); //=> 195
		
		// 文字列結合演算になる例
		System.out.println("a" + 'b'); //=> "ab"
	}
	
}
