package com.example.socketappclient.socket;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class SocketServerTest {

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

}
