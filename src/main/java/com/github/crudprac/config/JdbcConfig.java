package com.github.crudprac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("4865");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/super_coding_7?useUnicode=true&characterEncoding=UTF-8");
        return dataSource;
    }


    @Bean
    public JdbcTemplate jdbcTemplate1() { return new JdbcTemplate(dataSource()); }

    @Bean(name = "tm1")
    public PlatformTransactionManager transactionManager1() { return new DataSourceTransactionManager(dataSource()); }

}