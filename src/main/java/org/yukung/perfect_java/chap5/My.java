package org.yukung.perfect_java.chap5;

/*
 * mainメソッドを実行すると、Myオブジェクトを無限に生成するコード。（致命的なバグ）
 */
public class My {
	
	public static void main(String[] args) {
		My my = new My();
	}
	
	
	My my = new My();
}
