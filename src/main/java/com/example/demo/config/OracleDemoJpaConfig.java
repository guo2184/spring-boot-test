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
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.demo.repository.oracle",
        entityManagerFactoryRef = "oracleManager",
        transactionManagerRef = "oracleTransactionManager")
public class OracleDemoJpaConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.oracledemo")
    DataSource dsOracleDemo() {
        return DruidDataSourceBuilder.create().build();
    }

    @Autowired
    JpaProperties jpaProperties;

    @Bean
    LocalContainerEntityManagerFactoryBean oracleManager(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dsOracleDemo") DataSource dsOracleDemo) {
        Map<String, String> prop = jpaProperties.getProperties();
        prop.put("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
        return builder.dataSource(dsOracleDemo)
                .packages("com.example.demo.model.oracle")
                .properties(prop)
                .persistenceUnit("pu2")
                .build();
    }

    @Bean
    PlatformTransactionManager oracleTransactionManager(
            @Qualifier("oracleManager") LocalContainerEntityManagerFactoryBean oracleManager) {
        return new JpaTransactionManager(oracleManager.getObject());
    }
}
