import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FooTest {
    @Category(FastTests.class)
    @Test
    public void fastTest() throws Exception {
        System.out.println("FooTest#fastTest");

    }

    @Category(SlowTests.class)
    @Test
    public void slowTest() throws Exception {
        System.out.println("FooTest#slowTest");
    }
}
