package org.yukung.perfect_java.chap7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class MyEcho2 {
	
	public static void main(String[] args) {
		MyEcho2 echo = new MyEcho2(Arrays.asList(new MyFilter[] {
			new ReplaceImpl("he", "she"),
			new CapitalizeImpl()
		}));
		echo.doit();
	}
	
	
	private List<MyFilter> filters;
	
	
	public MyEcho2(List<MyFilter> filters) {
		this.filters = filters;
	}
	
	public void doit() {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				System.out.println("Input any text");
				String msg = stdin.readLine();
				
				String output = msg;
				for (MyFilter filter : filters) {
					output = filter.doJob(output);
				}
				System.out.println("You said, " + output);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
