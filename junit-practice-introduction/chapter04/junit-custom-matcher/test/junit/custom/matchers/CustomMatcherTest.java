package junit.custom.matchers;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static junit.custom.matchers.IsDate.*;

import java.util.Date;

import org.junit.Test;

public class CustomMatcherTest {

	@Test
	public void test() {
		assertThat(new Date(), is(dateOf(2012, 1, 12)));
	}

}
