package com.example.socketappclient.socket;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

@Slf4j
public class SocketServerTest {

    /* ddl 전송*/
    @Test
    public void connectToServer(){
        int port = 5555;
        String ip = "127.0.0.1";

        PrintWriter pw;

        String ddl = "CREATE TABLE employee (id INTEGER NOT NULL AUTO_INCREMENT, name VARCHAR(10) NOT NULL, PRIMARY KEY(id))";

        try {
            Socket s = new Socket(ip, port);
            log.info("@connected to the server");

            pw = new PrintWriter(s.getOutputStream());
            pw.println(ddl);
            pw.flush();

            pw.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }//connectToServer()

    /* 압축파일 전송 */
    @Test
    public void sendZippedFile(){
        int port = 5555;
        String ip = "127.0.0.1";

        OutputStream out;
        DataInputStream dataIn;
        DataOutputStream dataOut;

        FileInputStream fileIn;
        FileOutputStream fileOut;

        String zippedFile="C:\\Users\\pepup\\test33\\zippedFile.zip";

        int DEFAULT_BUFFER_SIZE = 1024;

        try {
            Socket socket = new Socket(ip, port);
            log.info("@connected to the server");

            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int readBytes;

            File file = new File(zippedFile);
            fileIn = new FileInputStream(file);

            out = socket.getOutputStream();
            while((readBytes=fileIn.read(buffer))>0){
                out.write(buffer, 0, readBytes);
            }
            log.info("@파일 전송 성공!");

            fileIn.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }//sendZippedFile()
}
