package org.yukung.perfect_java.chap5;

public class LocalInnerClass {
	
	void doit() {
		class LocalClass {
			
			void doit() {
				System.out.println("doit");
			}
		}
		
		LocalClass lc = new LocalClass();
		lc.doit();
	}
}
