package org.yukung.practice.chap6;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class CopyFile {

    public void copy(File from, File to) {
        BufferedInputStream in = null;
        GZIPOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(from));
            out = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(to)));

            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void copy7(File from, File to) {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(from));
                GZIPOutputStream out =
                        new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(to)))) {
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            out.flush();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        CopyFile file = new CopyFile();
        file.copy7(new File("pom.xml"), new File("pom.gzip"));
    }
}
