package com.example.socketappclient.socket;

import com.example.socketappclient.util.DatabaseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
* DDL 정보 수신 => 로컬 DB에 테이블 생성
* */

@Slf4j
public class SocketServer {

    @Value("${socket.localPort}")
    private int localPort;

    private ServerSocket ss;
    private Socket s;

    private BufferedReader br;

    @Autowired
    private DatabaseUtil dbUtil;

    public void init() {
        try {
            ss = new ServerSocket(localPort);
            log.info("@socket server started on port {}", localPort);

            s = ss.accept();
            log.info("@socket server is connected to a client! {}", s.getRemoteSocketAddress());

            br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String ddl = br.readLine();
            System.out.println(ddl);
            dbUtil.createTable(ddl);

            br.close();

//            //서버에서 파일 수신하는거 테스트해봤어요
//            int DEFAULT_BUFFER_SIZE = 1024;
//            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
//            int readBytes;
//
//            String newFile = "C:\\Users\\pepup\\test22\\newFile.zip";
//            FileOutputStream fileOut = new FileOutputStream(newFile);
//
//            InputStream in = s.getInputStream();
//            while((readBytes=in.read(buffer))!=-1){ fileOut.write(buffer, 0, readBytes); }
//
//            log.info("@파일 수신 완료!");
//
//            in.close();
//            fileOut.close();


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
