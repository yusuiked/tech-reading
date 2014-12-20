package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.app.AddCalculator;
import com.example.app.Calculator;

@Configuration
public class AppConfig {
	@Bean
	Calculator calculator() {
		return new AddCalculator();
	}
}
