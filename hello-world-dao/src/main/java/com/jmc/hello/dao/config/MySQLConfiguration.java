package com.jmc.hello.dao.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class MySQLConfiguration {

    @Bean(name = "mysqlJdbcTemplate")
    public NamedParameterJdbcTemplate mysqlJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }
    
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

}
