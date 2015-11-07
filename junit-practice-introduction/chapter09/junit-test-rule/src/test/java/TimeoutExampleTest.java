import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class TimeoutExampleTest {
    @Rule
    public Timeout timeout = new Timeout(100);

    @Test
    public void 長い時間がかかるかもしれないテスト() throws Exception {
        doLongTask();
    }

    private void doLongTask() throws InterruptedException {
        Thread.sleep(1000L);
    }
}
