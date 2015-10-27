import junit.tutorial.runner.suite.AllTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({CalcTest.class, AllTests.class})
public class AllProjectsTests {
}
