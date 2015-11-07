import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;

@RunWith(Enclosed.class)
public class ShopInfoTest {
    public static class インスタンス化テスト {
        @Rule
        public ErrorCollector errorCollector = new ErrorCollector();

        @Test
        public void デフォルトコンストラクタ() throws Exception {
            // Exercise
            ShopInfo instance = new ShopInfo();
            // Verify
            errorCollector.checkThat(instance, is(notNullValue()));
            errorCollector.checkThat(instance.id, is(nullValue()));
            errorCollector.checkThat(instance.name, is(""));    // fail
            errorCollector.checkThat(instance.address, is("")); // fail
            errorCollector.checkThat(instance.url, is(nullValue()));
        }
    }
}
