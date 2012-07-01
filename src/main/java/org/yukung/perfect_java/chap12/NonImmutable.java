package org.yukung.perfect_java.chap12;

public class NonImmutable {
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("0");
		My my = new My(sb);
		sb.append("1"); // myオブジェクトのフィールド変数の参照オブジェクトを変更できてしまう！
		System.out.println(my.getBuffer().toString());
		meth(my);
	}
	
	static void meth(My my) {
		StringBuilder sb = my.getBuffer();
		sb.append("2"); // myオブジェクトのフィールド変数の参照オブジェクトを変更できてしまう！
		System.out.println(my.getBuffer().toString());
	}
	
}
