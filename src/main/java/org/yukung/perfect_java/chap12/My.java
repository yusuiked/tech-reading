package org.yukung.perfect_java.chap12;

/*
 * 不変クラスになっていない例
 */
public class My {
	
	private final StringBuilder sb;
	
	
	public My(StringBuilder sb) {
		this.sb = sb;
	}
	
	public StringBuilder getBuffer() {
		return sb;
	}
}
