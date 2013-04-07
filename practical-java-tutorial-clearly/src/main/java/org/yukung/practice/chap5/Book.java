package org.yukung.practice.chap5;

import java.util.Date;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public int compareTo(Book o) {
		return CompareToBuilder.reflectionCompare(this, o);
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
