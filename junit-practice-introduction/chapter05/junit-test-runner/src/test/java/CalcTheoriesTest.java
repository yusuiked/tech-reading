import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * リスト5.6 Theories によるパラメータ化テスト
 */
@RunWith(Theories.class)
public class CalcTheoriesTest {
    @DataPoints
    public static int[][] VALUES = {
            {0, 0, 0},
            {0, 1, 1},
            {1, 0, 1},
            {3, 4, 7},
    };

    @Theory
    public void add(int[] values) throws Exception {
        CalcTest.Calc sut = new CalcTest.Calc();
        assertThat(sut.add(values[0], values[1]), is(values[2]));
    }
}
