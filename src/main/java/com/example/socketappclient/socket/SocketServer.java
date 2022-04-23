package com.example.socketappclient.socket;

import com.example.socketappclient.util.DatabaseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/*
* DDL 정보 수신 => 로컬 DB에 테이블 생성
* */

@Slf4j
public class SocketServer {

    @Value("${socket.myPort}")
    private int myPort;

    private ServerSocket ss;
    private Socket s;

    private BufferedReader br;

    @Autowired
    private DatabaseUtil dbUtil;

    public void init() {
        try {
            ss = new ServerSocket(myPort);
            log.info("@socket server started on port {}", myPort);

            s = ss.accept();
            log.info("@socket server is connected to a client!");

            br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String ddl = br.readLine();
            System.out.println(ddl);
            dbUtil.createTable(ddl);

            br.close();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                s.close();
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }//init()



}
