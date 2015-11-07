import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(FastTests.class)
@Categories.ExcludeCategory(SlowTests.class)
@Suite.SuiteClasses({FooTest.class, BarTest.class})
public class CategorizedTest {
}
