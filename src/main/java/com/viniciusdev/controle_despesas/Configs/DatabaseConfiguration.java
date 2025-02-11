package com.viniciusdev.controle_despesas.Configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DatabaseConfiguration{

        @Bean
        public DataSource hikariDataSource(@Value("${DATASOURCE_URL}") String url,
        @Value("${DATASOURCE_USER}") String username,
        @Value("${DATASOURCE_PASSWORD}") String password,
        @Value("${DATASOURCE_DRIVER}") String driver) {

        log.info("Starting connection to the database at URL: {}", url);

    HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(1);
        config.setPoolName("controle-despesas-pool");
        config.setMaxLifetime(600000);
        config.setConnectionTimeout(100000);
        config.setConnectionTestQuery("SELECT 1");

        return new HikariDataSource(config);
}
}
