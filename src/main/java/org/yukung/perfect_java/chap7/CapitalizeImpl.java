package org.yukung.perfect_java.chap7;

public class CapitalizeImpl implements MyFilter {
	
	@Override
	public String doJob(String in) {
		return in.toUpperCase();
	}
	
}
