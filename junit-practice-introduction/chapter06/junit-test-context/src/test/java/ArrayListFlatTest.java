import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ArrayListFlatTest {

    private ArrayList<String> sut;

    @Before
    public void setUp() throws Exception {
        sut = new ArrayList<>();
    }

    @Test
    public void listに1件追加してある場合_sizeは1を返す() throws Exception {
        sut.add("A");
        int actual = sut.size();
        assertThat(actual, is(1));
    }

    @Test
    public void listに2件追加してある場合_sizeは2を返す() throws Exception {
        sut.add("A");
        sut.add("B");
        int actual = sut.size();
        assertThat(actual, is(2));
    }
}
