/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devrcz.exampleSpringBatch.config;

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

    @ConfigurationProperties(prefix = "acuerdo.datasource")
    @Bean(name = "oracleAcuerdo")
    @Primary
    public DataSource oracleAcuerdoDataSource() {
        return DataSourceBuilder.create().build();
    }

    @ConfigurationProperties(prefix = "comision.datasource")
    @Bean(name = "oracleComision")
    public DataSource oracleComisionDataSource() {
        return DataSourceBuilder.create().build();
    }

}
