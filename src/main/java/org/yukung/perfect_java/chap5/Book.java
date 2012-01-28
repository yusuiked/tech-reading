package org.yukung.perfect_java.chap5;

import java.util.Date;

public class Book {
	
	String title;
	
	int price;
	
	String author;
	
	Date published;
	
	
	/*
	 * this呼び出しの例
	 */
	Book(String title, int price, String author) {
		this(title, price, author, new Date());
	}
	
	Book(String title, int price, String author, Date published) {
		super();
		this.title = title;
		this.price = price;
		this.author = author;
		this.published = published;
	}
	
}
