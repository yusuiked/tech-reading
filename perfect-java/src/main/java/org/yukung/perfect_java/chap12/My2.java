package org.yukung.perfect_java.chap12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class My2 {
	
	private final List<String> list;
	
	
	public My2() {
		list = new ArrayList<String>() {
			
			{
				add("0");
				add("1");
				add("2");
			}
		};
	}
	
	public List<String> getList() {
		return list;
	}
	
	public List<String> getUnmodifiableList() {
		return Collections.unmodifiableList(list);
	}
}
