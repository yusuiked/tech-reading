package org.yukung.practice.chap1;

public class StringConcatenation {

	private static final int LOOP_COUNT = 30000;

	public static void main(String[] args) {
		// +演算子で結合した場合
		whenCoupledWithPlusOperator();
		System.out.println("---------------------");
		// +演算子でも StringBuilder でも変わらない場合
		caseNeitherChange();
		System.out.println("---------------------");
		// StringBuilder を使った場合
		whenCoupledWithStringBuilder();
		// StringBuffer を使った場合
		whenCoupledWithStringBuffer();
	}

	private static void whenCoupledWithStringBuffer() {
		long start = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < LOOP_COUNT; i++) {
			sb.append(i);
		}
		System.out.println(sb.toString().length());
		long end = System.currentTimeMillis();
		System.out.printf("whenCoupledWithStringBuffer: %s%n", end - start);
	}

	private static void whenCoupledWithStringBuilder() {
		long start = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < LOOP_COUNT; i++) {
			sb.append(i);
		}
		System.out.println(sb.toString().length());
		long end = System.currentTimeMillis();
		System.out.printf("whenCoupledWithStringBuilder: %s%n", end - start);
	}

	private static void caseNeitherChange() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < LOOP_COUNT; i++) {
			String s = "prefix:" + i + "_suffix";
//			System.out.println(s);
		}
		long end = System.currentTimeMillis();
		System.out.printf("caseNeitherChange:plus: %s%n", end - start);
		System.out.println("=====================");
		start = System.currentTimeMillis();
		for (int i = 0; i < LOOP_COUNT; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append("prefix:");
			sb.append(i);
			sb.append("_suffix");
//			System.out.println(sb.toString());
		}
		end = System.currentTimeMillis();
		System.out.printf("caseNeitherChange:StringBuilder: %s%n", end - start);
	}

	private static void whenCoupledWithPlusOperator() {
		long start = System.currentTimeMillis();
		String s = "";
		for (int i = 0; i < LOOP_COUNT; i++) {
			s += i;
		}
		System.out.println(s.length());
		long end = System.currentTimeMillis();
		System.out.printf("whenCoupledWithPlusOperator: %s%n", end - start);
	}

}
