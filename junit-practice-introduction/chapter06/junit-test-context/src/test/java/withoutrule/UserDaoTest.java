package withoutrule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {
    private UserDao sut;
    private InMemoryDB db;

    @Before
    public void setUp() throws Exception {
        db = new InMemoryDB();
        db.start();
        sut = new UserDao();
    }

    @After
    public void tearDown() throws Exception {
        db.shutdownNow();
    }

    @Test
    public void getListは0件を返す() throws Exception {
        // 省略
    }
}
