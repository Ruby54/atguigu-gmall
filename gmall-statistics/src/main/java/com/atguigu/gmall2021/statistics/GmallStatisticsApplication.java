package com.atguigu.gmall2021.statistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.atguigu.gmall2021.statistics.mapper")
public class GmallStatisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallStatisticsApplication.class, args);
    }

}
