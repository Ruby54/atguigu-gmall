package com.atguigu.gmall2021.report;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication

public class GmallReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallReportApplication.class, args);

    }

}
