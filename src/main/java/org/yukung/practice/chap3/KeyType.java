package org.yukung.practice.chap3;

public enum KeyType {
	PADLOCK(1024), BUTTON(10000), DIAL(30000), FINGER(1000000);
	
	private int count;
	
	
	private KeyType(int count) {
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}
}
