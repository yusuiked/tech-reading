import org.junit.rules.TemporaryFolder;

import java.io.File;

public class SpecificTemporaryFolder extends TemporaryFolder {
    public File srcFolder;
    public File binFolder;

    @Override
    protected void before() throws Throwable {
        super.before();
        srcFolder = newFolder("src");
        binFolder = newFolder("bin");
    }
}
