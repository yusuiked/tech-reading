package org.yukung.practice.chap13;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MovieTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructer() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		{
			Movie movie = new Movie();
			assertThat(movie, is(notNullValue()));
			assertThat(movie.getTitle(), is("default"));
			assertThat(movie.getPublishDate(),is(cal.getTime()));
		}

		{
			Movie movie = new Movie("タイトル");
			assertThat(movie,is(notNullValue()));
			assertThat(movie.getTitle(),is("タイトル"));
			assertThat(movie.getPublishDate(),is(cal.getTime()));
		}
	}

	@Test
	public void testProperty() throws Exception {
		Movie movie = new Movie();
		movie.setTitle("タイトル");
		assertThat(movie,is(notNullValue()));
		assertThat(movie.getTitle(),is("タイトル"));
		movie.setTitle("タイトル2");
		assertThat(movie.getTitle(),is("タイトル2"));

		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
		Date date = formater.parse("2013/04/01");
		movie.setPublishDate(date);
		assertThat(movie.getPublishDate(),is(formater.parse("2013/04/01")));
		movie.setPublishDate(formater.parse("2013/04/20"));
		assertThat(movie.getPublishDate(),is(formater.parse("2013/04/20")));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testInvalidTitle() throws Exception {
		Movie movie = new Movie();
		assertThat(movie,is(notNullValue()));
		movie.setTitle(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testInvalidPublishDate() throws Exception {
		Movie movie = new Movie();
		assertThat(movie,is(notNullValue()));
		movie.setPublishDate(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testFutureDate() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		Movie movie = new Movie();
		movie.setPublishDate(cal.getTime());
	}
}
