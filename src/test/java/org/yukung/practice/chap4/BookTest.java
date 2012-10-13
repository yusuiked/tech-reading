package org.yukung.practice.chap4;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

public class BookTest {

	Calendar calendar = null;

	@Before
	public void setUp() {
		calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(2012, 9 - 1, 14);
	}

	@Test
	public void testEquals() {
		// 等価判定
		Book book = new Book();
		book.setTitle("スッキリわかるJava入門実践編");
		book.setPublishDate(calendar.getTime());
		book.setComment("こめんと");

		Book another = new Book();
		another.setTitle("スッキリわかるJava入門実践編");
		another.setPublishDate(calendar.getTime());
		another.setComment("コメント"); // 書名と発行日が等価の対象フィールド

		Book failure1 = new Book();
		failure1.setTitle("スッキリわかるJava入門実践編");
		failure1.setPublishDate(new Date()); // 発行日が違う
		failure1.setComment("こめんと");

		Book failure2 = new Book();
		failure2.setTitle("スッキリわかるJAVA入門実践編"); // 書名が違う
		failure2.setPublishDate(calendar.getTime());
		failure2.setComment("こめんと");

		assertThat(book, is(notNullValue()));
		assertThat(another, is(notNullValue()));
		assertThat(failure1, is(notNullValue()));
		assertThat(book.equals(another), is(true));
		assertThat(book.equals(failure1), is(false));
		assertThat(book.equals(failure2), is(false));
	}

	@Test
	public void testHashCodeAndClone() throws Exception {
		Set<Book> set = new HashSet<Book>();
		Book book = new Book();
		book.setTitle("スッキリわかるJava入門実践編");
		book.setPublishDate(calendar.getTime());
		System.out.println(book.hashCode());
		Book book2 = new Book();
		book2.setTitle("aaa");
		book2.setPublishDate(new Date());
		System.out.println(book2.hashCode());
		Book book3 = book.clone();
		set.add(book);
		set.add(book2);
		set.add(book3);
		assertThat(set.size(), is(2));	// ディープコピーしているのでSetで重複して2
		set.remove(book);
		assertThat(set.isEmpty(), is(false));
		assertThat(set.size(), is(1));
		assertThat(set.contains(book2), is(true));
	}

	@Test
	public void testComparable() throws Exception {
		Set<Book> set = new TreeSet<Book>();
		for (int i = 0; i < 3; i++) {
			Book book = new Book();
			book.setTitle(String.valueOf(i));
			book.setPublishDate(new Date());
			set.add(book);
		}
		Iterator<Book> iterator = set.iterator();
		while (iterator.hasNext()) {
			Book book = iterator.next();
			System.out.println(book.getTitle());
		}
	}
}
