package com.example.socketappclient.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@Component
public class FileUtil {

    /* 파일들을 압축
    @param files 파일의전체경로를 입력
    * */
    public void zipFiles(String... files) {
        String zippedFile = "";
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
    }//zipFiles()


}
