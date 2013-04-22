package org.yukung.practice.chap6;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.GZIPInputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CopyFileTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCopy() throws Exception {
        File from = new File("from.txt");
        File to = new File("to.gz");
        CopyFile copyFile = new CopyFile();
        copyFile.copy7(from, to);

        int length = (int) from.length();

        BufferedInputStream originFile = new BufferedInputStream(new FileInputStream(from));
        GZIPInputStream decompFile =
                new GZIPInputStream(new BufferedInputStream(new FileInputStream(to)));

        byte[] originBuffer = new byte[length];
        int originLength = originFile.read(originBuffer, 0, length);
        System.out.printf("Original file size: %s bytes.%n", originLength);
        originFile.close();

        byte[] decompBuffer = new byte[length];
        byte b;
        for (int i = 0;;i++) {
            if ((b = (byte) decompFile.read()) == -1) {
                break;
            }
            decompBuffer[i] = b;
        }
        decompFile.close();

        assertArrayEquals(originBuffer, decompBuffer);
//        System.out.println(new String(decompBuffer, "UTF-8"));
    }

}
