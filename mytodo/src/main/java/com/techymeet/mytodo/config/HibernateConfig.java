package com.techymeet.mytodo.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableCaching
@EnableTransactionManagement
public class HibernateConfig {

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String user;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.hikari.schema}")
	private String schema;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String hbm2ddl;
	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String dialect;
	@Value("${spring.jpa.show-sql}")
	private String showSql;
	@Value("${spring.datasource.hikari.minimum-idle}")
	private int minPoolSize;
	@Value("${spring.datasource.hikari.maximum-pool-size}")
	private int maxPoolSize;
	@Value("${spring.datasource.hikari.connection-init-sql}")
	private String testQuery;

	@Bean
	public HikariDataSource dataSource() {
		HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class)
				.driverClassName(driverClassName).url(url).username(user).password(password).build();
			dataSource.setIdleTimeout(minPoolSize);
			dataSource.setMaximumPoolSize(maxPoolSize);
		dataSource.setConnectionTestQuery(testQuery);
		return dataSource;

	}

	@Autowired
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("com.techymeet.mytodo.vo");
		entityManagerFactoryBean.setJpaProperties(additionalProperties());
		return entityManagerFactoryBean;

	}

	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
		hibernateProperties.setProperty("hibernate.default_schema", schema);
		hibernateProperties.setProperty("hibernate.dialect", dialect);
		hibernateProperties.setProperty("hibernate.show_sql", showSql);
		// hibernateProperties.setProperty("hibernate.cache.use_second_level_cache","true"); 
		// hibernateProperties.setProperty("hibernate.cache.use_query_cache", "true"); 
		// hibernateProperties.setProperty("hibernate.cache.region.factory_class",
		// "org.hibernate.cache.ehcache.EhCacheRegionFactory");
		hibernateProperties.setProperty("hibernate.connection.CharSet", "utf8");
		hibernateProperties.setProperty("hibernate.connection.characterEncoding", "utf8");
		hibernateProperties.setProperty("hibernate.connection.useUnicode", "true");
		hibernateProperties.setProperty("hibernate.id.new_generator_mappings", "false");
		hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");
		return hibernateProperties;

	}

	@Autowired
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;

	}
}

