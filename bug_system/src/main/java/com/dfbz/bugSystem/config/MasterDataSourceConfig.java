package com.dfbz.bugSystem.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 默认数据源配置信息
 */
@Configuration
@MapperScan(basePackages = "com.dfbz.bugSystem.dao",sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

    @Value("${spring.master.datasource.url}")
    private String url;
    @Value("${spring.master.datasource.driverClassName}")
    private String driverClass;
    @Value("${spring.master.datasource.username}")
    private String username;
    @Value("${spring.master.datasource.password}")
    private String password;


    /**
     * 默认数据源对象 Primary提高优先级
     * @return
     */
    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.druid")
    public DruidDataSource masterDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }


    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        //mybatis page helper拦截器
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect","mysql");
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","false");
        interceptor.setProperties(properties);
        sqlSessionFactory.setPlugins(new Interceptor[]{interceptor});
        return sqlSessionFactory.getObject();
    }


    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager(){
        return new DataSourceTransactionManager(masterDataSource());
    }





}
