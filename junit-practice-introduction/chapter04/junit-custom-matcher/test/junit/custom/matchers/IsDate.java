package junit.custom.matchers;

import java.util.Calendar;
import java.util.Date;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class IsDate extends BaseMatcher<Date> {

	private final int yyyy;
	private final int mm;
	private final int dd;
	Object actual;

	public IsDate(int yyyy, int mm, int dd) {
		this.yyyy = yyyy;
		this.mm = mm;
		this.dd = dd;
	}

	public boolean matches(Object actual) {
		this.actual = actual;
		if (!(actual instanceof Date)) return false;
		Calendar cal = Calendar.getInstance();
		cal.setTime((Date) actual);
		if (yyyy != cal.get(Calendar.YEAR)) return false;
		if (mm != cal.get(Calendar.MONTH)) return false;
		if (dd != cal.get(Calendar.DATE)) return false;
		return true;
	}

	public void describeTo(Description desc) {

	}

	public static Matcher<Date> dateOf(int yyyy, int mm, int dd) {
		return new IsDate(yyyy, mm, dd);
	}
}
