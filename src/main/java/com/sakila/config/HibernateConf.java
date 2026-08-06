package com.sakila.config;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author bc887d
 *
 */
@Slf4j
@Configuration
@EnableTransactionManagement
@Lazy
public class HibernateConf {

	@Value("${jdbc.driverClassName}")
	private String DB_DRIVER;

	@Value("${jdbc.url}")
	private String DB_URL;

	@Value("${jdbc.user}")
	private String DB_USERNAME;

	@Value("${jdbc.pass}")
	private String DB_PASSWORD;

	@Value("${hibernate.dialect}")
	private String HIBERNATE_DIALECT;

	@Value("${hibernate.show_sql}")
	private String HIBERNATE_SHOW_SQL;

	@Value("${hibernate.hbm2ddl.auto}")
	private String HIBERNATE_HBM2DDL_AUTO;

	@Value("${entitymanager.packagesToScan}")
	private String ENTITYMANAGER_PACKAGES_TO_SCAN;

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		log.info("... Entered into dataSource() of HibernateConf ...");
		log.info(" Class : {}", env.getProperty("jdbc.driverClassName"));
		log.info(" URL: :  {}", env.getProperty("jdbc.url"));
		log.info(" User :  {}", env.getProperty("jdbc.user"));
		log.info(" Pass :  {}", env.getProperty("jdbc.pass"));

		final HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("jdbc.driverClassName")));
		dataSource.setJdbcUrl(Objects.requireNonNull(env.getProperty("jdbc.url")));
		dataSource.setUsername(Objects.requireNonNull(env.getProperty("jdbc.user")));
		dataSource.setPassword(Objects.requireNonNull(env.getProperty("jdbc.pass")));
		return dataSource;
	}

	@Bean
	public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean factory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(factory.getObject());
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
