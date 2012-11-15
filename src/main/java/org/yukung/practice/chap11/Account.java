package org.yukung.practice.chap11;

public class Account {
	/** 口座名義人 */
	String owner;
	/** 口座残高 */
	int balance;

	/**
	 * 引数を２つ持つコンストラクタ
	 * @param owner
	 * @param balance
	 */
	public Account(String owner, int balance) {
		owner = owner;
		balance = balance;
	}

	/**
	 * 送金を行うメソッド
	 * @param dest
	 * @param amount
	 */
	public void transfer(Account dest, int amount) {
		dest.balance += amount;
		balance -= amount;
	}
}
