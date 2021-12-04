package com.atguigu.gmall2021.report.config;
import javax.sql.DataSource;



import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.boot.jdbc.DataSourceBuilder;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.core.JdbcTemplate;


/**
 * @program: gmall
 * @description:presto配置数据源
 */
@Configuration

public class PrestoJdbcConfig {

    private static Logger LOG = LoggerFactory.getLogger(PrestoJdbcConfig.class);



    @Bean(name = "prestoDataSource")

    @ConfigurationProperties(prefix = "spring.datasource.presto")

    public DataSource prestoDataSource() {

        LOG.info("-------------------- presto init ---------------------");

        return DataSourceBuilder.create().build();

    }



    @Bean(name = "prestoTemplate")

    public JdbcTemplate prestoJdbcTemplate(@Qualifier("prestoDataSource") DataSource dataSource) {

        return new JdbcTemplate(dataSource);

    }

}

