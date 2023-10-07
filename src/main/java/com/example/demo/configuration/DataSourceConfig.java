package com.example.demo.configuration;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.example.demo.db"},
        entityManagerFactoryRef =  "db1EntityManagerFactory",
        transactionManagerRef = "db1TransactionManager"
)
public class DataSourceConfig {
    /**
     * 指定数据源1的DataSource
     * @return
     */
    @Primary
    @Bean(name = "db1DataSourceProperties")
    @ConfigurationProperties("spring.datasource1")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 可以选择不同的数据源，这里用HikariDataSource举例，创建数据源1
     */
    @Primary
    @Bean(name="db1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.db1")
    public HikariDataSource dataSource(@Qualifier("db1DataSourceProperties") DataSourceProperties db1DataSourceProperties){
        HikariDataSource dataSource = db1DataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        if(StringUtils.hasText(db1DataSourceProperties.getName())){
            dataSource.setPoolName(db1DataSourceProperties.getName());
        }
        return dataSource;
    }

    /**
     * 配置数据源1的entityManagerFactory 命名为db1EntityManagerFactory, 用来对实体进行一些操作
     */
    @Primary
    @Bean(name="db1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            EntityManagerFactoryBuilder builder, @Qualifier("db1DataSource") DataSource db1DataSource
    ){
        return builder.dataSource(db1DataSource).packages("com.example.demo.db").persistenceUnit("db1").build();
    }

    /**
     * 配置数据源1的事物管理，命名为db1TransactionManager依赖db1EntityManagerFactory
     */
    @Primary
    @Bean(name="db1TransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("db1EntityManagerFactory") EntityManagerFactory db1EntityManagerFactory){
        return new JpaTransactionManager(db1EntityManagerFactory);
    }
}
