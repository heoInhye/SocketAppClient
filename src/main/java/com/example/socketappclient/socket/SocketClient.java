package com.example.socketappclient.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.Socket;

/*
* */

@Slf4j
public class SocketClient {

    @Value("${socket.remotePort}")
    private int port;

    @Value("${socket.remoteIp}")
    private String ip;

    private Socket socket;

    public void sendFileToServer(String filePath){
        FileInputStream fileIn;

        OutputStream out;

        int DEFAULT_BUFFER_SIZE = 1024;

        try {
            socket = new Socket(ip, port);
            log.info("@connected to the server.. {}", socket.getRemoteSocketAddress());

            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int readBytes;

            File file = new File(filePath);
            fileIn = new FileInputStream(file);

            out = socket.getOutputStream();
            while((readBytes=fileIn.read(buffer))>0){
                out.write(buffer, 0, readBytes);
            }
            log.info("@succeed at the file sent!");

            out.close();
            fileIn.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    } //sendFileToServer()



}
