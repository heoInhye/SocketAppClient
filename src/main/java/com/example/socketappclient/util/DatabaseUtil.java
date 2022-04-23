package com.example.socketappclient.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
@Component
public class DatabaseUtil {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public void createTable(String ddl) {
        log.info("@@ {}", ddl);
        log.info("@@ {}, {}, {}, {}", url, driver, username, password);

        Connection con = null;
        Statement stmt = null;

        try {
            con = DriverManager.getConnection(url,username,password);
            stmt = con.createStatement();
            stmt.execute(ddl);

            log.info("@@ 테이블 생성완료!");
            stmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
