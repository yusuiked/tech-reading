import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class EnclosedParameterizedTypeTest {
    @RunWith(Theories.class)
    public static class intのパラメータ化テスト {
        @DataPoint
        public static int INT_PARAM_1 = 3;
        @DataPoint
        public static int INT_PARAM_2 = 4;

        @Theory
        public void 引数がint型のテストメソッド(int param) throws Exception {
            System.out.println("引数がint型のテストメソッド(" + param + ")");
        }
    }

    @RunWith(Theories.class)
    public static class Stringのパラメータ化テスト {
        @DataPoint
        public static String STRING_PARAM_1 = "Hello";
        @DataPoint
        public static String STRING_PARAM_2 = "World";

        @Theory
        public void 引数がString型のテストメソッド(String param) throws Exception {
            System.out.println("引数がStringのテストメソッド(" + param + ")");
        }
    }
}
