package org.yukung.perfect_java.chap17.di;

import java.util.List;

public class StringList {
	
	private List<String> list;
	
	
	public StringList(List<String> list) {
		this.list = list;
	}
	
	public void append(String s) {
		list.add(s);
	}
	
	public void dump() {
		for (String s : list) {
			System.out.println(s);
		}
	}
}
