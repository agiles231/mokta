package com.agiles231.mokta.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class PersistanceConfig {

	Logger logger = LoggerFactory.getLogger(PersistanceConfig.class);

	@Value("${datasource.driver}")
	String driver;
	@Value("${datasource.url}")
	String url;
	@Value("${datasource.username}")
	String username;
	@Value("${datasource.password}")
	String password;

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	@Bean
	public DataSource dataSource() {
		DataSourceBuilder builder = DataSourceBuilder.create();
		builder.driverClassName(driver);
		builder.url(url);
		builder.username(username);
		builder.password(password);

		logger.debug("Database url: " + url);
		return builder.build();
	}
}
