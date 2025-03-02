package com.example.blog_system.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
//    @Bean(name = "dataSourcePostgreSql")
//    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.datasourcepostgresql")
//    public DataSource dataSourcePostgreSql() {
//        return DataSourceBuilder.create().build();
//    }
}
