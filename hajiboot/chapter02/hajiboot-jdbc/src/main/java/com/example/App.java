package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@EnableAutoConfiguration
public class App implements CommandLineRunner {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {
		String sql = "SELECT 1";
		MapSqlParameterSource param = new MapSqlParameterSource();
		Integer result = jdbcTemplate.queryForObject(sql, param, Integer.class);

		System.out.println("result = " + result);
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
