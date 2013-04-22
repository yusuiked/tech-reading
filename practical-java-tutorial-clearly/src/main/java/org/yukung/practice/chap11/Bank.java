package org.yukung.practice.chap11;

public class Bank {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("名前が設定されていません");
		}
		if (name.length() < 3) {
			throw new IllegalArgumentException("名前が不正です");
		}
		this.name = name;
	}
}
