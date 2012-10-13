package org.yukung.practice.chap3;

public class StrongBox<T> {
	
	private KeyType keyType;
	
	private int count;
	
	private T element;
	
	
	public StrongBox(KeyType keyType) {
		this.count = 0;
		this.keyType = keyType;
	}
	
	public T get() {
		if (count < keyType.getCount()) {
			++count;
			return null;
		}
		count = 0;
		return element;
	}
	
	public void put(T element) {
		this.element = element;
	}
	
}
