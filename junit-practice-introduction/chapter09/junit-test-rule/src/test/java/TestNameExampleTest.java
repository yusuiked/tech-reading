import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.junit.Assert.fail;

public class TestNameExampleTest {
    @Rule
    public TestName testName = new TestName();

    @Test
    public void テスト名() throws Exception {
        fail(testName.getMethodName() + " is unimplements yet.");
    }
}
