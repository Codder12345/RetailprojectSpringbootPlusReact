package com.example.demo.config;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.context.annotation.Bean;

public class DBConfig {
	@Bean(name="jdbctemplate")
	public   JdbcTemplate jdbctemplate(DataSource datasource)
	{
		return new JdbcTemplate(datasource);
	}

}
