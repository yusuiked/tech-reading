package org.yukung.practice.chap13;

import java.util.Calendar;
import java.util.Date;

public class Movie {

	private String title;
	private Date publishDate;

	public Movie() {
		this.title = "default";
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		this.publishDate = cal.getTime();
	}

	public Movie(String title) {
		this.title = title;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		this.publishDate = cal.getTime();
	}

	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException();
		}
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setPublishDate(Date date) {
		if (date == null) {
			throw new IllegalArgumentException();
		}
		Calendar cal = Calendar.getInstance();
		if (cal.getTime().compareTo(date) < 0) {
			throw new IllegalArgumentException();
		}
		this.publishDate = date;
	}

	public Date getPublishDate() {
		return publishDate;
	}

}
