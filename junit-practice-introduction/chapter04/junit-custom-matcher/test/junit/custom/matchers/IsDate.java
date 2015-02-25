package junit.custom.matchers;

import java.util.Date;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class IsDate extends BaseMatcher<Date> {

	public boolean matches(Object actual) {
		return false;
	}

	public void describeTo(Description desc) {

	}

	public static Matcher<Date> dateOf(int yyyy, int mm, int dd) {
		return new IsDate();
	}
}
