package com.example.socketappclient.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
public class FileUtilTest {

    @Test
    public void zipFilesTest() {
        String[]  files = {"C:\\Users\\pepup\\test00\\test00.txt", "C:\\Users\\pepup\\test11\\test11.txt"};

        String zippedFile = "C:\\Users\\pepup\\test33\\zippedFile.zip";
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        try {
            fos = new FileOutputStream(zippedFile);
            zos = new ZipOutputStream(fos);

            for(String file : files){
                Path path = Paths.get(file);
                FileInputStream fis = new FileInputStream(path.toFile());
                ZipEntry zn = new ZipEntry(path.getFileName().toString());
                zos.putNextEntry(zn);

                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }

                fis.close();
            }//for

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                zos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//zipFilesTest()


}
