/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devrcz.exampleSpringBatch.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 *
 * @author renzo
 */
@Configuration
public class DatabaseConfig {

    @Bean(name = "ACUERDO_DB")
    //@Primary
    @ConfigurationProperties(prefix = "acuerdo.datasource")
    public HikariDataSource destinationDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

//    @ConfigurationProperties(prefix = "comision.datasource")
//    @Bean(name = "COMISION_DB")
//    public HikariDataSource sourceDataSource() {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
}
