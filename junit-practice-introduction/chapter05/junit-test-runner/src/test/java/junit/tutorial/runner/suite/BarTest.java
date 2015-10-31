package junit.tutorial.runner.suite;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BarTest {
    @Test
    public void testBar() throws Exception {
        assertThat("Bar", is("Bar"));
    }
}
