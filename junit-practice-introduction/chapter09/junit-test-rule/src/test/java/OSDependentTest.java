import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class OSDependentTest {
    @Rule
    public OSDependent osDependent = new OSDependent();

    @Test
    @RunOn(RunOn.OS.WINDOWS)
    public void onlyWindows() throws Exception {
        System.out.println("test: onlyWindows");
        assertThat(File.separator, is("\\"));
    }

    @Test
    @RunOn(RunOn.OS.MAC)
    public void onlyMac() throws Exception {
        System.out.println("test: onlyMac");
        assertThat(File.separator, is("/"));
    }
}
