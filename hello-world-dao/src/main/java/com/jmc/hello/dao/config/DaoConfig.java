package com.jmc.hello.dao.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class DaoConfig {

 	@Bean(name = "daoSql")
    public PropertiesFactoryBean daoSql() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("dao-sql.xml"));
        return bean;
    }
    
}
