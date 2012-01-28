package org.yukung.perfect_java.chap5;

import java.util.Date;

public class ProgrammingBook extends Book {
	
	String language;
	
	
	/*
	 * super呼び出しの例。
	 */
	ProgrammingBook(String title, int price, String author, Date published, String language) {
		super(title, price, author, published);
		this.language = language;
	}
	
}
