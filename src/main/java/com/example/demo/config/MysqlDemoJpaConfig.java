package com.example.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.demo.repository.mysql",
        entityManagerFactoryRef = "mysqlManager",
        transactionManagerRef = "mysqlTransactionManager")
public class MysqlDemoJpaConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.mysqldemo")
    @Primary
    DataSource dsMysqlDemo() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.jpa.mysql")
    @Primary
    JpaProperties jpaMysqlProperties() {
        return new JpaProperties();
    };


    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean mysqlManager(
            EntityManagerFactoryBuilder builder,
            @Qualifier("jpaMysqlProperties") JpaProperties jpaMysqlProperties,
            @Qualifier("dsMysqlDemo") DataSource dsMysqlDemo) {
        return builder.dataSource(dsMysqlDemo)
                .packages("com.example.demo.model.mysql")
                .properties(jpaMysqlProperties.getProperties())
                .persistenceUnit("pu1")
                .build();
    }

    @Bean
    @Primary
    PlatformTransactionManager mysqlTransactionManager(
            @Qualifier("mysqlManager") LocalContainerEntityManagerFactoryBean mysqlManager) {
        return new JpaTransactionManager(mysqlManager.getObject());
    }
}
