package com.agiles231.mokta.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import com.agiles231.mokta.user.UserDao;
import com.agiles231.mokta.user.UserSqlDao;

@Profile("prod")
@Configuration
public class PersistanceConfig {

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
//	@Bean
//	public UserDao userDao() {
//		return new UserSqlDao();
//	}
}
