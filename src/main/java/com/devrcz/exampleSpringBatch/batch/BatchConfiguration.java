/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devrcz.exampleSpringBatch.batch;

import com.devrcz.exampleSpringBatch.bean.AcuerdoFinanciamientoBean;
import com.devrcz.exampleSpringBatch.bean.DatosBean;
import com.devrcz.exampleSpringBatch.config.AcuerdoFinanciamientoRowMapper;
import org.slf4j.Logger;
import javax.sql.DataSource;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author renzo
 */
@Configuration
@EnableBatchProcessing(modular = true)
@EnableAutoConfiguration
public class BatchConfiguration {

    private static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${idAcuerdoMenor}")
    public String idAcuerdoMenor;

    @Value("${idAcuerdoMayor}")
    public String idAcuerdoMayor;

    @Value("${chunk}")
    public Integer chunk;

    @Value("${idDatoInicio}")
    public String idDatoInicio;

    @Autowired(required = true)
    @Qualifier("oracleAcuerdo")
    public DataSource oracleAcuerdo;

    @Autowired(required = true)
    @Qualifier("oracleComision")
    public DataSource oracleComision;

    @Bean
    public Job migrateDataToDataBase() {
        return jobBuilderFactory
                .get("migrateDataToDataBase")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory
                .get("step")
                .<AcuerdoFinanciamientoBean, DatosBean>chunk(chunk)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    
    @Bean
    public JdbcCursorItemReader<AcuerdoFinanciamientoBean> reader() {
        JdbcCursorItemReader<AcuerdoFinanciamientoBean> reader = new JdbcCursorItemReader<AcuerdoFinanciamientoBean>();
        reader.setDataSource(oracleAcuerdo);
        reader.setSql("SELECT acuerdo.ACFI_ID \"id_acuerdo\", \n"
                + "acuerdo.ACFI_CTIPO \"tipo\", \n"
                + "acuerdo.ACFI_CSTIP \"subtipo\" \n"
                + "FROM CFMACUFINA acuerdo \n"
                + "LEFT JOIN CFMREQHWSA request \n"
                + "ON request.ACFI_ID = acuerdo.ACFI_ID \n"
                + "WHERE acuerdo.ACFI_ID >= " + idAcuerdoMenor
                + "\n AND acuerdo.ACFI_ID <= " + idAcuerdoMayor
        );
        reader.setRowMapper(new AcuerdoFinanciamientoRowMapper());
        return reader;
    }

    @Bean
    public ItemProcessor<AcuerdoFinanciamientoBean, DatosBean> processor() {
        return new BatchProcessor();
    }

    @Bean
    public ItemWriter<DatosBean> writer() {
        JdbcBatchItemWriter<DatosBean> writer = new JdbcBatchItemWriter<DatosBean>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<DatosBean>());

        //En este caso se obtiene el valor de la secuencia
        writer.setSql("INSERT INTO CFM_DATOS (CFM_ID,CFM_TIPO, CFM_SUB_TIPO) VALUES (CFM_DATOS_SEQ.nextval,:dato_tipo, :dato_sub_tipo)");

        //Para el update debe venir el id en el read
        //writer.setSql("UPDATE CFM_DATOS SET CFM_TIPO = :dato_tipo, CFM_SUB_TIPO = :dato_sub_tipo WHERE CFM_ID = :id");
        writer.setDataSource(oracleComision);
        return writer;
    }

//    @Bean
//    public JpaItemWriter<Datos> writer() {
//        JpaItemWriter<Datos> itemWriter = new JpaItemWriter<Datos>();
//        itemWriter.setEntityManagerFactory(entityManagerFactory().getObject());
//        return itemWriter;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        localContainerEntityManagerFactoryBean.setPackagesToScan("com.cfmbyte.updateCliente.modelComision");
//        localContainerEntityManagerFactoryBean.setDataSource(ComisionDataSource());
//        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
//        localContainerEntityManagerFactoryBean.setJpaProperties(new Properties());
//        return localContainerEntityManagerFactoryBean;
//    }
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//        jpaVendorAdapter.setDatabase(Database.ORACLE);
//        jpaVendorAdapter.setGenerateDdl(true);
//        jpaVendorAdapter.setShowSql(false);
//        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.Oracle10gDialect");
//        return jpaVendorAdapter;
//    }
}
