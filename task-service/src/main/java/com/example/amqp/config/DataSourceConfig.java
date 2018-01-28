package com.example.amqp.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by ksb on 2018. 1. 7..
 */
@Configuration
public class DataSourceConfig {

    @Bean("testDataSource")
    @ConfigurationProperties("jdbc-datasource.test")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate testJdbcTemplate(@Qualifier("testDataSource") DataSource testDataSource) {
        return new JdbcTemplate(testDataSource);
    }

}
