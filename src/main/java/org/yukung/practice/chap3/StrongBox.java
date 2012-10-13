package org.yukung.practice.chap3;

public class StrongBox<T> {

	private KeyType keyType;

	private int count;

	private T element;


	public StrongBox(KeyType keyType) {
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

	enum KeyType {
		PADLOCK(1024), BUTTON(10000), DIAL(30000), FINGER(1000000);

		private int count;


		private KeyType(int count) {
			this.count = count;
		}

		public int getCount() {
			return count;
		}
	}
}
