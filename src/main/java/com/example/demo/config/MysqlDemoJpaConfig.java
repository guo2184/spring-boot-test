package com.example.demo.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.demo.repository.mysql",
        entityManagerFactoryRef = "mysqlManager")
@ConfigurationProperties(prefix = "spring.datasource.mysqldemo")
@Data
public class MysqlDemoJpaConfig {

    private String url;
    private String username;
    private String password;

    @Bean
    @Primary
    DataSource dsMysqlDemo() {
        MysqlXADataSource xaDataSource = new MysqlXADataSource();
        xaDataSource.setUrl(url);
        xaDataSource.setUser(username);
        xaDataSource.setPassword(password);
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSource(xaDataSource);
        ds.setUniqueResourceName("mysqldemo");
        return ds;
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

//    @Bean
//    @Primary
//    PlatformTransactionManager mysqlTransactionManager(
//            @Qualifier("mysqlManager") LocalContainerEntityManagerFactoryBean mysqlManager) {
//        return new JpaTransactionManager(mysqlManager.getObject());
//    }
}
