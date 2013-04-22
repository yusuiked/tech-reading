package org.yukung.perfect_java.chap18;

public class Capitalize {
	
	@MyFilter
	public String doJob(String in) {
		return in.toUpperCase();
	}
}
