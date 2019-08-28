package com.example.demo.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.demo.repository.oracle",
        entityManagerFactoryRef = "oracleManager")
@ConfigurationProperties(prefix = "spring.datasource.oracledemo")
@Data
public class OracleDemoJpaConfig {

    private String url;
    private String username;
    private String password;

    @Bean
    DataSource dsOracleDemo() {
        DruidXADataSource xaDataSource = new DruidXADataSource();
        xaDataSource.setUrl(url);
        xaDataSource.setUsername(username);
        xaDataSource.setPassword(password);
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSource(xaDataSource);
        ds.setUniqueResourceName("oracledemo");
        return ds;
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

//    @Bean
//    PlatformTransactionManager oracleTransactionManager(
//            @Qualifier("oracleManager") LocalContainerEntityManagerFactoryBean oracleManager) {
//        return new JpaTransactionManager(oracleManager.getObject());
//    }
}
