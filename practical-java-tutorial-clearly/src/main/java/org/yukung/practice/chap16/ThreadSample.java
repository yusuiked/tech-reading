package org.yukung.practice.chap16;

import java.util.Scanner;

public class ThreadSample {
	public static void main(String[] args) {
		System.out.println("何か入力してください");
		new Scanner(System.in).nextLine();
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
		}
	}
}
