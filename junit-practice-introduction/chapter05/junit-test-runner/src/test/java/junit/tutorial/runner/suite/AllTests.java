package junit.tutorial.runner.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * テストスイートクラス
 */
@RunWith(Suite.class)
@SuiteClasses({FooTest.class, BarTest.class})
public class AllTests {
}
