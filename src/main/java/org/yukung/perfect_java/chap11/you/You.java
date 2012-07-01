package org.yukung.perfect_java.chap11.you;

import org.yukung.perfect_java.chap11.my.My;
import org.yukung.perfect_java.chap11.my.MyFactory;

public class You {
	
	public static void main(String[] args) {
		My my = MyFactory.createMy();
//		MyImpl myImpl = new MyImpl();	// パッケージプライベートなので、クラスが解決できない
		my.doit(); // => "my.MyImpl"を出力
	}
	
}
