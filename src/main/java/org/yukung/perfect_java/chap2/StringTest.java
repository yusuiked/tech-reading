package org.yukung.perfect_java.chap2;

public class StringTest {
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("usage: StringTest From:mail-header(e.g. \"bill joy\" <bill.joy@x-sun.com>\")");
			return;
		}
		String input = args[0];
		int begin = input.indexOf('<');
		if (begin >= 0) {
			begin++;
			int end = input.indexOf('>');
			if (end > begin) {
				String addr = input.substring(begin, end);
				System.out.println(addr);
			}
		}
	}
	
}
