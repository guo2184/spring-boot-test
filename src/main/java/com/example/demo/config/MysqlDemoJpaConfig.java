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

    @Autowired
    JpaProperties jpaProperties;


    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean mysqlManager(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dsMysqlDemo") DataSource dsMysqlDemo) {
        Map<String, String> prop = jpaProperties.getProperties();
        prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        return builder.dataSource(dsMysqlDemo)
                .packages("com.example.demo.model.mysql")
                .properties(prop)
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
