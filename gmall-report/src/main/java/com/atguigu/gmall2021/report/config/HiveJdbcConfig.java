package com.atguigu.gmall2021.report.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/*** 
 * @description:hive配置数据源
 * @date: 2021/9/27 
 */ 

@Configuration
public class HiveJdbcConfig {

    private static Logger LOG = LoggerFactory.getLogger(HiveJdbcConfig.class);



    @Bean(name = "hiveDataSource")

    @ConfigurationProperties(prefix = "spring.datasource.hive")

    public DataSource hiveDataSource() {

        LOG.info("-------------------- presto init ---------------------");

        return DataSourceBuilder.create().build();

    }



    @Bean(name = "hiveTemplate")

    public JdbcTemplate hiveJdbcTemplate(@Qualifier("hiveDataSource") DataSource dataSource) {

        return new JdbcTemplate(dataSource);

    }


}