package org.yukung.practice.chap8;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DownloaderTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDownload() throws Exception {
        Downloader downloader = new Downloader("http://dokojava.jp/favicon.ico");
        downloader.download("dj.ico");

        File file = new File("dj.ico");
        assertThat(file,is(notNullValue()));
        assertThat(file.exists(),is(true));
        assertThat(file.isFile(),is(true));
        assertThat(file.getPath(),is("dj.ico"));
        assertThat(file.getName(),is("dj.ico"));
        assertThat(file.length(),is(22638L));
    }

}
