package com.example.socketappclient.config;

import com.example.socketappclient.socket.SocketServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean(initMethod="init")
    public SocketServer socketServer(){
        return new SocketServer();
    }

}
