import org.junit.Test;
import org.junit.runner.JUnitCore;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * 実行可能なテストクラス
 */
public class CalcTest {
    @Test
    public void addに3と4を与えると7を返す() throws Exception {
        Calc sut = new Calc();
        assertThat(sut.add(3, 4), is(7));
    }

    public static void main(String[] args) {
        JUnitCore.main(CalcTest.class.getName());
    }

    public static class Calc {
        public int add(int x, int y) {
            return x + y;
        }
    }
}
