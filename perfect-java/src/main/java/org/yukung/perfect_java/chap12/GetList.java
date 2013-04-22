package org.yukung.perfect_java.chap12;

import java.util.List;

public class GetList {
	
	public static void main(String[] args) {
		My2 my2 = new My2();
		List<String> list = my2.getList();
		list.add("3"); // フィールドのリストの要素を変更できてしまう！
		for (String string : list) {
			System.out.println(string);
		}
		
		List<String> unmodifiableList = my2.getUnmodifiableList();
		unmodifiableList.add("4"); // throw UnsupportedOperationException
		for (String string : unmodifiableList) {
			System.out.println(string);
		}
	}
	
}
