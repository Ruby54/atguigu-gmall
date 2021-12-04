package com.atguigu.gmall2021.webapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
@MapperScan(basePackages = "com.atguigu.gmall2021.webapi.mapper")
public class GmallStatsWebapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallStatsWebapiApplication.class, args);
        try {
//            String url = "jdbc:presto://hadoop102:8881/hive/defaul";
//            Connection connection = DriverManager.getConnection(url, "root", null);
            Connection conn = DriverManager.getConnection("jdbc:presto://192.168.69.4:8881/hive/test", "root", null);
            Statement stmt = conn.createStatement();
            try {
                ResultSet rs = stmt.executeQuery("SELECT id, name, age from student limit 5");
                while(rs.next()) {
                    long time = rs.getLong(1);
                    String method = rs.getString(2);
                    String path = rs.getString(3);
                    System.out.println(String.format("time=%s, method=%s, path=%s", time, method, path));
                }
            }
            finally {
                stmt.close();
                conn.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
