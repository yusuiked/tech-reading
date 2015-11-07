import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.rules.RuleChain;

public class RuleChainExampleTest {
    @Rule
    public RuleChain ruleChain = RuleChain.outerRule(new DBServer()).around(new AppServer());

    @Test
    public void テスト() throws Exception {
    }

    private class DBServer extends ExternalResource {
        @Override
        protected void before() throws Throwable {
            System.out.println("Start DB");
        }

        @Override
        protected void after() {
            System.out.println("Shutdown DB");
        }
    }

    private class AppServer extends ExternalResource {
        @Override
        protected void before() throws Throwable {
            System.out.println("Start App");
        }

        @Override
        protected void after() {
            System.out.println("Shutdown App");
        }
    }
}
