package org.yukung.perfect_java.chap10;

public class FloatingNumber {
	
	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(Float.floatToRawIntBits(0.5f)));
		// -> 00111111000000000000000000000000
		// 出力をIEEE754の形式に分割すると
		// 0 01111110 00000000000000000000000
		
		System.out.println(Integer.toBinaryString(Float.floatToRawIntBits(0.25f)));
		// -> 00111110100000000000000000000000
		// 0 01111101 00000000000000000000000
		
		System.out.println(Integer.toBinaryString(Float.floatToRawIntBits(16f)));
		// -> 01000001100000000000000000000000
		// 0 100000110 0000000000000000000000
		
		System.out.println(Integer.toBinaryString(Float.floatToRawIntBits(1.024f)));
		// -> 00111111100000110001001001101111
		// 0 011111110 0000110001001001101111
	}
	
}
