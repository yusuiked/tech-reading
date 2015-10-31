package junit.tutorial.runner.suite;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FooTest {
    @Test
    public void testFoo() throws Exception {
        assertThat("Foo", is("Foo"));
    }
}
