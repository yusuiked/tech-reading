package org.yukung.perfect_java.chap3;

public class ConvertSample {
	
	public static void main(String[] args) {
		int n1 = Character.digit('1', 10);
		int n2 = Character.digit('f', 16);
		int n3 = Character.digit('7', 8);
		System.out.printf("value: n1:%s, n2:%s n3:%s %n", n1, n2, n3);
		char c1 = Character.forDigit(0, 10);
		char c2 = Character.forDigit(1, 10);
		char c3 = Character.forDigit(10, 16);
		char c4 = Character.forDigit(10, 10); // 変換できない場合、c4の値は0x0
		System.out.printf("value: c1:%s, c2:%s, c3:%s, c4:%s", c1, c2, c3, c4);
	}
}
