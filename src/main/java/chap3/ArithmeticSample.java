package chap3;

public class ArithmeticSample {
	
	public static void main(String[] args) {
		int n;
		n = 2147483647;
		System.out.println(n + 1); //-2147483648 を出力
		
		n = -2147483648;
		System.out.println(n - 1); //2147483647 を出力
		
		n = 2147483647;
		System.out.println(n * 2); //-2 を出力
		
		n = -2147483648;
		System.out.println(-n * -1); //-2147483648 を出力
		
		n = -2147483648;
		System.out.println(-n / -1); //-2147483648 を出力
		
		// 割り算の切り捨ての例
		n = 10;
		System.out.println(n / 3); //3 を出力
		n = -10;
		System.out.println(n / 3); //-3 を出力
		
		/*
		 * 以下はint型の整数値とlong型の整数値を演算した際に、
		 * 暗黙にint型からlong型への型変換が起きることを利用している
		 */
		
		// すべて 214783648 を出力
		System.out.println(2147483647L + 1);
		System.out.println(2147483647 + 1L);
		System.out.println(2147483647L + 1L);
		
		// すべて -214783649 を出力
		System.out.println(-2147483648L - 1);
		System.out.println(-2147483648 - 1L);
		System.out.println(-2147483648L - 1L);
		
		/*
		 * 符号反転
		 * 符号の境界値に対して反転すると、想定しない結果を返す。
		 * 2の補数の意味を考えると理解できる。
		 */
		n = 10;
		System.out.println(-n); // -10 を出力
		
		n = -2147483648;
		System.out.println(-n); // -2147483648 を出力
	}
	
}
