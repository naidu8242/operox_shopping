/*
 * Copyright (c) 2016, Business Intelli Solutions India Pvt. Ltd., and/or its affiliates. All rights reserved.
 * Business Intelli Solutions India Pvt. Ltd. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.bis.operox;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * This class is used to read the properties from properties file and configures
 * database connection pooling using c3p0 and hibernate session factory
 * creation.
 * 
 * 
 * @author: Srinivas Vemula
 */

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.bis.operox")
@PropertySource("${propertyLocation:operoxapp.properties}")
public class DatabaseConfig {

	private ComboPooledDataSource comboPooledDataSource;

	@Value("${jdbc.driverClass}")
	private String driverClass;

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${jdbc.username}")
	private String user;

	@Value("${jdbc.password}")
	private String password;

	@Bean
	public PlatformTransactionManager txManager() {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory());
		return hibernateTransactionManager;
	}

	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder sfb = new LocalSessionFactoryBuilder(comboPooledDataSource());

		sfb.scanPackages("com.bis.operox");

		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.connection.driver_class", driverClass);
		hibernateProperties.setProperty("hibernate.connection.username", user);
		hibernateProperties.setProperty("hibernate.connection.password", password);
		hibernateProperties.setProperty("hibernate.connection.url", jdbcUrl);

		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "validate");
		hibernateProperties.setProperty("hibernate.formate_sql", "false");
		hibernateProperties.setProperty("hibernate.user_sql_comments", "false");
		hibernateProperties.setProperty("hibernate.transaction.factory_class",
				"org.hibernate.transaction.JDBCTransactionFactory");
		hibernateProperties.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
		hibernateProperties.setProperty("hibernate.connection.provider_class",
				"org.hibernate.service.jdbc.connections.internal.C3P0ConnectionProvider");
		hibernateProperties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		hibernateProperties.setProperty("hibernate.c3p0.acquire_increment", "5");
		hibernateProperties.setProperty("hibernate.c3p0.min_size", "3");
		hibernateProperties.setProperty("hibernate.c3p0.max_size", "100");
		hibernateProperties.setProperty("hibernate.c3p0.timeout", "1800");
		hibernateProperties.setProperty("hibernate.c3p0.preferredTestQuery", "SELECT 1;");
		hibernateProperties.setProperty("hibernate.c3p0.testConnectionOnCheckout", "true");
		hibernateProperties.setProperty("hibernate.c3p0.idle_test_period", "100");
		hibernateProperties.setProperty("hibernate.c3p0.max_statements", "10000");
		hibernateProperties.setProperty("hibernate.c3p0.maxStatementsPerConnection", "0");
		hibernateProperties.setProperty("hibernate.c3p0.maxAdministrativeTaskTime", "500");
		hibernateProperties.setProperty("hibernate.c3p0.c3p0.numHelperThreads", "8");

		// TODO: No caching was configured as of now. But we can configure the
		// cache later.

		
		 hibernateProperties.setProperty("hibernate.cache.use_query_cache",
		 "true"); hibernateProperties.setProperty(
		 "hibernate.cache.use_second_level_cache", "true");
		 hibernateProperties.setProperty("hibernate.jdbc.batch_size", "1000");
		 hibernateProperties.setProperty(
		 "hibernate.cache.region.factory_class",
		 "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
		 hibernateProperties.setProperty(
		 "net.sf.ehcache.configurationResourceName", "hibernateCache.xml");
		  sfb.addProperties(hibernateProperties);

		return sfb.buildSessionFactory();
	}

	@Bean
	public DataSource comboPooledDataSource() {
		this.comboPooledDataSource = new ComboPooledDataSource();
		try {
			comboPooledDataSource.setDriverClass(driverClass);
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		comboPooledDataSource.setJdbcUrl(jdbcUrl);
		comboPooledDataSource.setUser(user);
		comboPooledDataSource.setPassword(password);
		return comboPooledDataSource;
	}

	@PreDestroy
	public void destroy() {
		this.comboPooledDataSource.close();
	}
}
