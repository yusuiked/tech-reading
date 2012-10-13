package org.yukung.practice.chap4;

import java.util.Date;

public class Book implements Comparable<Book>, Cloneable {
	private String title;
	private Date publishDate;
	private String comment;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Book)) return false;
		Book b = (Book) obj;
		if (!(publishDate.equals(b.publishDate))) return false;
		if (!(title.equals(b.title))) return false;
		return true;
	}

	@Override
	public int hashCode() {
		int r = 1;
		r = 31 * r + publishDate.hashCode();
		r = 31 * r + title.hashCode(); return r;
	}

	@Override
	public int compareTo(Book o) {
		return this.publishDate.compareTo(o.publishDate);
	}

	@Override
	public Book clone() {
		Book b = new Book();
		b.title = this.title;
		b.comment = this.comment;
		b.publishDate = (Date) this.publishDate.clone();
		return b;
	}
}
